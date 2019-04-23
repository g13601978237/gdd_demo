package com.star.service.impl;

import com.github.pagehelper.PageHelper;
import com.star.common.JsonResult;
import com.star.dao.NoteDao;
import com.star.entity.Note;
import com.star.entity.NoteExample;
import com.star.service.NoteService;
import com.star.util.PageHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteServiceImpl implements NoteService {


    @Autowired
    private NoteDao noteDao;
    @Override
    public boolean save(Note note) {
       return noteDao.insert(note)>0;
    }

    @Override
    public Note getMyLastNote(int custId) {
        return noteDao.getMyLastNote(custId);
    }

    @Override
    public List<Note> selectAll(int pageNum) {
        PageHelper.startPage(pageNum,PageHelperUtil.PAGE_SIZE);
        List<Note>  noteList  = noteDao.selectAll(); // 只有当前的3条记录
        System.out.println("Impl,noteList"+noteList);
        return this.noteDao.selectByExample(null);
    }

    @Override
    public List<Note> getTenNote(int custId) {
        List<Note> notes = noteDao.selectTenNote(custId);
        return notes;
    }

    @Override
    public Note getById(int noteId) {
        Note note = noteDao.selectByPrimaryKey(noteId);
        return note;
    }

    @Override
    public int getCount() {
            int count = noteDao.selectCount();
        return count;
    }


}
