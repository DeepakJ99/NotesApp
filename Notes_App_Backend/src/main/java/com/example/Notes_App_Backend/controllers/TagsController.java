package com.example.Notes_App_Backend.controllers;

import com.example.Notes_App_Backend.entities.Tag;
import com.example.Notes_App_Backend.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagsController {
    @Autowired
    private TagsService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping
    public ResponseEntity<Tag> createOrUpdateTag(@RequestBody Tag tag) {
        Tag savedTag = tagService.saveOrUpdate(tag);
        return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id) {
        tagService.deleteTagById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}