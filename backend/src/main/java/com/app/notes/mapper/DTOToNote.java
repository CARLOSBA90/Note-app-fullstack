package com.app.notes.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.app.notes.persistence.entity.Note;
import com.app.notes.persistence.entity.Tag;
import com.app.notes.service.dto.NoteDTO;

@Component
public class DTOToNote implements IMapper<NoteDTO,Note> {

	@Override
	public Note map(NoteDTO in) {
		Note note = new Note();
		note.setTitle(in.getTitle());
		note.setContent(in.getContent());
		note.setCreatedDate(LocalDateTime.now());
		note.setFinished(in.isFinished());
		note.setTag(in.getTag());
		return note;
	}

	@Override
	public Note update(NoteDTO in, Note on) {
		on.setTitle(in.getTitle());
		on.setContent(in.getContent());
		on.setFinished(in.isFinished());
		on.setTag(in.getTag());
		return on;
	}



}
