package com.star.controller;

import com.star.common.JsonResult;
import com.star.entity.Customer;
import com.star.entity.Note;
import com.star.service.CustomerService;
import com.star.service.NoteService;
import com.star.util.PageHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private CustomerService customerService;
    //游记头图上窜
    @RequestMapping("uploadHeadImg")
    @ResponseBody
    public JsonResult uploadHeadImg(HttpServletRequest request, MultipartFile upload) {
        String path = request.getServletContext().getRealPath("/upload");
        System.out.println("path" + path);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = upload.getOriginalFilename();
        System.out.println("fileName" + fileName);
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid" + uuid);
        fileName = uuid + "_" + fileName;
        File uploadFile = new File(file, fileName);
        try {
            upload.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.createFailJsonResult(null);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("filePath", "upload/" + fileName);
        System.out.println(data.entrySet());
        return JsonResult.createSuccessJsonResult(data);
    }

    @RequestMapping("addNote")
    @ResponseBody
    public JsonResult addNote(HttpServletRequest request, Note note) {
        //注意添加游记要关联作者id,即当前登录用户的id,还有当前游记创建的时间
        int custId = (int) request.getSession().getAttribute("customerId");
        note.setNoteCreateTime(new Date());
        note.setCustId(custId);
        boolean flag = noteService.save(note);
        System.out.println(flag);
        if (!flag) {
            Map<String, Object> data = new HashMap<>();
            data.put("reason", "上传失败");
            return JsonResult.createFailJsonResult(data);
        } else {
            return JsonResult.createSuccessJsonResult(null);
        }

    }

    @RequestMapping("goToDetail")
    public String goToDetail() {
        return "note/detail";
    }

    //查看我最近的游记（刚写的游记）
    @RequestMapping("myLastNote")
    @ResponseBody
    public JsonResult getMyLastNote(HttpServletRequest request) {
        System.out.println("=========================");
        int custId = (int) request.getSession().getAttribute("customerId");
        Note myLastNote = noteService.getMyLastNote(custId);
        System.out.println("myLastNote:" + myLastNote);
        Map<String, Object> data = new HashMap<>();
        data.put("myLastNote", myLastNote);
        return JsonResult.createSuccessJsonResult(data);

    }

    @RequestMapping("gotoMylist")
    public String gotoMylist(){
        return "note/mylist";
    }

    @RequestMapping("getTen")
    @ResponseBody
    public JsonResult getTen(HttpServletRequest request){
        System.out.println("进入getTen");
        int custId =(int) request.getSession().getAttribute("customerId");
        System.out.println(custId);
        List<Note> notes = noteService.getTenNote(custId);
        System.out.println("============================");
        System.out.println(notes.size());
        Customer customer = customerService.getByCustId(custId);
        Map<String,Object> data = new HashMap<>();
        data.put("notes",notes);
        data.put("customer",customer);
        System.out.println(data.entrySet());
        return JsonResult.createSuccessJsonResult(data);
    }

    @RequestMapping("goToDetail/{noteId}")
    public String go(@PathVariable("noteId") int noteId){
        System.out.println(noteId);
        return "note/details.html?noteid="+noteId;
    }
    @RequestMapping("goTo/{noteId}")
    @ResponseBody
    public JsonResult getById(@PathVariable("noteId") int noteId){
        System.out.println("进入getById");
        Note note = noteService.getById(noteId);
        Map<String,Object> data = new HashMap<>();
        data.put("note",note);
        System.out.println(data.entrySet());
        return JsonResult.createSuccessJsonResult(data);
    }

    @RequestMapping("gotoMylistF")
    public String gotoMylistF()
    {
        return "note/mylistF";
    }

    @RequestMapping("getall/{pageNum}")
    @ResponseBody
    public JsonResult getByPage(@PathVariable("pageNum") int pageNum) {
        List<Note> noteList = noteService.selectAll(pageNum);
        int totalCount = this.noteService.getCount();//游记总记录数
        int totalPage =  (int)  Math.ceil(totalCount% PageHelperUtil.PAGE_SIZE==0?totalCount/ PageHelperUtil.PAGE_SIZE:totalCount/ PageHelperUtil.PAGE_SIZE+1);//共几页
        for(Note note:noteList){
            Customer customer =  customerService.getByCustId(note.getCustId());
            System.out.println("controller,customer"+customer);
            note.setOthers1(customer.getCustName());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("noteList", noteList);
        data.put("totalCount",totalCount);    //游记总记录数
        data.put("totalPage",totalPage);      //共几页
        return JsonResult.createSuccessJsonResult(data);
    }
}
