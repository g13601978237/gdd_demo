package com.star.controller;

import com.star.common.JsonResult;
import com.star.entity.Customer;
import com.star.entity.Note;
import com.star.service.CustomerService;
import com.star.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
        @RequestMapping("login")
        public String login(HttpServletRequest request){
          return "customer/login";
        }

        @RequestMapping("getCode")
        @ResponseBody
        public String sendSMS(String phone){
            //手机验证码
            //String code = GetMessageCode.getCode(phone);
            String code="1";
            System.out.println("code:"+code);
            Map<String,Object> data = new HashMap<>();
            data.put("code",code);
            return code;
        }

        //手机登录
    @RequestMapping("loginByPhone")
    @ResponseBody
    public  JsonResult loginByPhone(Long telno, HttpServletRequest request){
        //1. 查看该用户是否存在过，若存在过，查询出来绑定在session
        System.out.println("loginByPhone========telno:"+telno);
        Customer customer =  customerService.getByTelno(telno);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+customer);
        if(customer!=null){
            request.getSession().setAttribute("customerId",customer.getCustId());
            request.getSession().setAttribute("customerTelno",customer.getCustTelno());

            System.out.println("================存在过=====================");
            return JsonResult.createSuccessJsonResult(null);

        }else{ //2. 若用户不存在，注册一个（添加一个新账户），绑定在session
            System.out.println("================没有存在过过=====================");
            Customer newCustomer = new Customer();
            System.out.println("newCustomer"+newCustomer);
            newCustomer.setCustTelno(telno);
            newCustomer.setCustRegistTime(new Date());
            System.out.println();
            boolean flag = customerService.save(newCustomer);
            if(flag){
                //添加成功，登录成功,查询出来绑定在session
                customer =  customerService.getByTelno(telno);
                request.getSession().setAttribute("customerId",customer.getCustId());
                request.getSession().setAttribute("customerTelno",customer.getCustTelno());

                System.out.println("Customer========="+customer);
                System.out.println("==========="+JsonResult.createSuccessJsonResult(null));
                return JsonResult.createSuccessJsonResult(null);

            }else{
                //添加失败
                return JsonResult.createFailJsonResult(null);

            }

        }

    }
    //获得登录用户的手机信息
    @RequestMapping("getLoginTelno")
    @ResponseBody
    public JsonResult getLoginTelno( HttpServletRequest request){
        System.out.println("getLoginTelno.....................................");
        Object result =  request.getSession().getAttribute("customerTelno");
        System.out.println("===================="+result);
        if(result!=null) { //已登录
            long customerTelno = (long) result;
            Map<String, Object> data = new HashMap<>();
            data.put("customerTelno", customerTelno);
            return JsonResult.hasLoginJsonResult(data);
        }else{
            return JsonResult.needLoginJsonResult(null);//未登录
        }
    }
    @RequestMapping("logout")
    @ResponseBody
    public JsonResult loginout(HttpServletRequest request){
            request.getSession().invalidate();
            return JsonResult.createSuccessJsonResult(null);
    }
    @RequestMapping("gotoInfo")
    public String gotoInfo(){
            return "customer/info";
    }

    @RequestMapping("info")
    @ResponseBody
    public JsonResult getInfoByTelno(long telno){
        Customer customer =customerService.getByTelno(telno);
        Map<String,Object> data = new HashMap<>();
        data.put("customer",customer);
        return JsonResult.createSuccessJsonResult(data);
    }

    @RequestMapping("gotoAdd")
    public String gotoAdd(){
        return "note/add";
    }

    @RequestMapping("uploadMyPic")
    @ResponseBody
    public JsonResult uploadMyPic(HttpServletRequest request, MultipartFile uploadpic){
         String path = request.getServletContext().getRealPath("/upload");
        System.out.println("path="+path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = uploadpic.getOriginalFilename();
        String uuid= UUID.randomUUID().toString();
        filename = uuid +"_"+filename;
        System.out.println("filename"+filename);
        File uploadFile = new File(file,filename);
        try {
            //把内存图片写入磁盘中
            uploadpic.transferTo(uploadFile);
            Map<String,Object> data = new HashMap<>();
            data.put("filePath","upload/"+filename);
            return JsonResult.createSuccessJsonResult(data);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.createFailJsonResult(null);
        }

    }


}

