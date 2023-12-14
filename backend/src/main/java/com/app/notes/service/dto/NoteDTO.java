package com.app.notes.service.dto;

import com.app.notes.persistence.entity.Tag;

import lombok.Data;

@Data
public class NoteDTO {
	private String title;
    private String content;
    private Tag tag;
    private boolean finished;
}
