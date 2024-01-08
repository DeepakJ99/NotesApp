package com.example.Notes_App_Backend.services;

import com.example.Notes_App_Backend.entities.Tag;
import com.example.Notes_App_Backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagsService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag saveOrUpdate(Tag tag) {
        return tagRepository.save(tag);
    }

    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }
}