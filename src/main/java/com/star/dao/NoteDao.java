package com.star.dao;

import com.star.entity.Note;
import com.star.entity.NoteExample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NoteMapper继承基类
 */
@Repository
public interface NoteDao extends MyBatisBaseDao<Note, Integer, NoteExample> {

    Note getMyLastNote(int custId);

    List<Note> selectAll();

    List<Note> selectTenNote(int custId);

    int selectCount();
}