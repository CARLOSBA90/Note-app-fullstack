package com.app.notes.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.notes.persistence.entity.Note;

import java.util.List;
public interface NoteRepository extends  JpaRepository<Note, Long> {

}
