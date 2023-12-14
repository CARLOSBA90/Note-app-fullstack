package com.app.notes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.notes.persistence.entity.Tag;
import com.app.notes.service.TagService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tags")
public class TagController {
	
	private final TagService tagService;
	
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	  @GetMapping("/list")
	  public List<Tag> findAll(){
		  return this.tagService.findAll();
	  }

}
