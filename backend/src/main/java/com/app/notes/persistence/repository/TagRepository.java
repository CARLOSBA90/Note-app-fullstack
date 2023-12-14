package com.app.notes.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.notes.persistence.entity.Tag;

public interface TagRepository extends  JpaRepository<Tag, Long> {

}
