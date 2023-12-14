package com.app.notes.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.notes.exceptions.NoteExceptions;
import com.app.notes.persistence.data.TagDataInitializer;
import com.app.notes.persistence.entity.Note;
import com.app.notes.persistence.entity.Tag;
import com.app.notes.persistence.repository.TagRepository;
import java.util.Optional;

@Service
public class TagService {

	private final TagRepository repository;
	private final TagDataInitializer initializer;
	
	public TagService(TagRepository repository, TagDataInitializer initializer) {
        this.repository = repository;
        this.initializer = initializer;
        this.initializer.init();
    }
	
	/**
	 * List of Entity
	 */
	public List<Tag> findAll(){
		return this.repository.findAll();
	}
	
	/**
	 * Check Existence of Entity
	 */
	public boolean checkTagExists(Tag tag) {
	    Optional<Tag> optionalTag = repository.findById(tag.getId());
	    return optionalTag.isPresent();
	}
	
	/**
	 * Entity of Tag
	 */
	public Tag findById(Tag tag) {
        Optional<Tag> optionalTag = this.repository.findById(tag.getId());
        
        if (optionalTag.isEmpty()) {
            throw new NoteExceptions("Tag not found", HttpStatus.NOT_FOUND);
        }
        
		return optionalTag.get();
	}
	
}
