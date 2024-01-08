package com.example.Notes_App_Backend;

import com.example.Notes_App_Backend.DTO.NoteDTO;
import com.example.Notes_App_Backend.entities.Note;
import com.example.Notes_App_Backend.entities.Tag;
import com.example.Notes_App_Backend.services.NotesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class NotesAppBackendApplicationTests {

	NotesService notesService;
	@Test
	void contextLoads() {
	}

}
