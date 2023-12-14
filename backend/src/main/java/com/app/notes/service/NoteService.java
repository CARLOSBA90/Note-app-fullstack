package com.app.notes.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.notes.exceptions.NoteExceptions;
import com.app.notes.mapper.DTOToNote;
import com.app.notes.persistence.entity.Note;
import com.app.notes.persistence.repository.NoteRepository;
import com.app.notes.service.dto.NoteDTO;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

	private final NoteRepository repository;
	private final DTOToNote mapper;
	private final TagService tagService;

	public NoteService(NoteRepository repository,
			           DTOToNote mapper, 
			           TagService tagService) {
        this.repository = repository;
        this.mapper = mapper;
        this.tagService = tagService;
    }

	
	/**
	 * Create an Entity
	 */
	@Transactional
	public Note createNote(NoteDTO noteDTO) {
		// Auto-fill data of Entity Tag
		noteDTO.setTag(tagService.findById(noteDTO.getTag()));
		Note note = mapper.map(noteDTO);
	    return this.repository.save(note);
	}
	
	
	/**
	 * List of Entity
	 */
	public List<Note> findAll(){
		return this.repository.findAll();
	}
	
	/**
	 * Entity by Id
	 */
	public Note findById(Long id){
        Optional<Note> optionalNote = this.repository.findById(id);
        
        if (optionalNote.isEmpty()) {
            throw new NoteExceptions("Note not found", HttpStatus.NOT_FOUND);
        }
        
		return optionalNote.get();
	}
	
	
	/**
	 * UPDATE entity
	 * @param id
	 * @param noteDTO
	 */
    @Transactional
    public void updateById(Long id, NoteDTO noteDTO) {
        Optional<Note> optionalNote = this.repository.findById(id);
        
        if (optionalNote.isEmpty()) {
            throw new NoteExceptions("Note not found", HttpStatus.NOT_FOUND);
        }

		noteDTO.setTag(tagService.findById(noteDTO.getTag()));
        
     /** perfom by mapper due to tag ID **/
		Note updatedNote  = mapper.update(noteDTO,optionalNote.get());
		repository.save(updatedNote);
    }

	
	/**
	 * Delete Entity
	 *  @param id
	 */
    public void deleteById(Long id) {
        Optional<Note> optionalNote = this.repository.findById(id);
        if (optionalNote.isEmpty()) {
            throw new NoteExceptions("Note not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
    }
}
