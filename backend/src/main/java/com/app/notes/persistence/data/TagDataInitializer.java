package com.app.notes.persistence.data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.notes.persistence.entity.Tag;
import com.app.notes.persistence.repository.TagRepository;

@Component
public class TagDataInitializer {

    @Autowired
    private TagRepository tagRepository;

    @PostConstruct
    public void init() {
        if (tagRepository.count() == 0) {
            List<Tag> tags = new ArrayList<>();
            tags.add(new Tag("#urgent", 1)); 
            tags.add(new Tag("#to-do", 2)); 
            tags.add(new Tag("#meeting", 3)); 
            tags.add(new Tag("#call", 4)); 
            tags.add(new Tag("#email", 5)); 
            tags.add(new Tag("#delegated", 6));
            tags.add(new Tag("#waiting", 7)); 
            tags.add(new Tag("#done", 8));
            tagRepository.saveAll(tags);
        }
    }
}
