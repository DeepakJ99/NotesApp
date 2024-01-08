package com.example.Notes_App_Backend.controllers;

import com.example.Notes_App_Backend.DTO.NoteDTO;
import com.example.Notes_App_Backend.entities.Note;
import com.example.Notes_App_Backend.services.NotesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/notes")
public class NotesController {
    @Autowired
    private NotesService noteService;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllRootNotes() {
        return new ResponseEntity<>(noteService.getAllRootNotes(), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<NoteDTO> getRootNoteByTitle(@PathVariable String title){
        return new ResponseEntity<>(noteService.getRootNoteByTitle(title), HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<Note> createOrUpdateNote(@RequestBody NoteDTO note) {
        Note savedNote = noteService.saveOrUpdate(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}