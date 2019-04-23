package com.star.service;

import com.star.entity.Note;

import java.util.List;

public interface NoteService {

    boolean save(Note note);

    Note getMyLastNote(int id);

    List<Note> selectAll(int pageNum);

    List<Note> getTenNote(int custId);

    Note getById(int noteId);

    int getCount();
}
