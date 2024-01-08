package com.example.Notes_App_Backend.repositories;

import com.example.Notes_App_Backend.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByParentNoteId(Long parentNoteId);
    Note findByTitle(String title);
}

