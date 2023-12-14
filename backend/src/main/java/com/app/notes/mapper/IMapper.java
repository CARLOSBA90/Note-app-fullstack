package com.app.notes.mapper;

import com.app.notes.persistence.entity.Note;
import com.app.notes.service.dto.NoteDTO;

public interface IMapper <I, O>{
    public O map(I in);
    public O update(I in, O on);
}