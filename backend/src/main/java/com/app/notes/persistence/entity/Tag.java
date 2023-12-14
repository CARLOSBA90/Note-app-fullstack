package com.app.notes.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique=true)
    private String name;
    
    @Column(unique=true)
    private int priority;

    public Tag() {}
    public Tag(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    public Tag(Long id) {
        this.id = id;
    }

}
