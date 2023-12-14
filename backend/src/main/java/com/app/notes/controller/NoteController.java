package com.app.notes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.notes.persistence.entity.Note;
import com.app.notes.service.NoteService;
import com.app.notes.service.dto.NoteDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/notes")
public class NoteController {
	
	private final NoteService noteService;
	
	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}
	
  @PostMapping("/create")
  public Note createNote(@RequestBody NoteDTO noteDTO) {
       return this.noteService.createNote(noteDTO);
  }
  
  @GetMapping("/list")
  public List<Note> findAll(){
	  return this.noteService.findAll();
  }
  
  @GetMapping("/{id}")
  public Note findById(@PathVariable Long id){
	  return this.noteService.findById(id);
  }
  
  
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {      
      this.noteService.updateById(id,noteDTO);
      return ResponseEntity.noContent().build();
  }
  
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
      this.noteService.deleteById(id);
      return ResponseEntity.noContent().build();
  }
  
 
 

}
