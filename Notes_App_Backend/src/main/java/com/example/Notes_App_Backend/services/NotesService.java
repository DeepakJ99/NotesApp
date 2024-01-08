package com.example.Notes_App_Backend.services;


import com.example.Notes_App_Backend.DTO.NoteDTO;
import com.example.Notes_App_Backend.constants.Colors;
import com.example.Notes_App_Backend.entities.Note;
import com.example.Notes_App_Backend.entities.Tag;
import com.example.Notes_App_Backend.repositories.NoteRepository;
import com.example.Notes_App_Backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class NotesService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private TagRepository tagsRepository;
    public NoteDTO convert(Note note){
        return new NoteDTO(note.getId(), note.getTitle(), note.getContent(),
                (note.getParentNote()!=null)?note.getParentNote().getId():0,
                note.getColor(),
                note.getLink(),
                note.getTags().stream().map((tag)->tag.getName()).collect(Collectors.toSet()),
                noteRepository.findByParentNoteId(note.getId()).stream().map(childNote -> convert(childNote)).collect(Collectors.toList()));
    }
    public List<NoteDTO> getAllRootNotes() {
        return noteRepository.findByParentNoteId(null).stream().map((note)-> convert(note)).collect(Collectors.toList());
    }

    public List<Note> getSubNotes(Long parentId) {
        return noteRepository.findByParentNoteId(parentId);
    }


    public Note saveOrUpdate(NoteDTO noteDTO) {
        Note newNote = new Note(
                noteDTO.getTitle(),
                removeHashtags(noteDTO.getContent())
                );

        if(noteDTO.getParentNoteId() != 0) {
            Note parentNote = noteRepository.findById((long) noteDTO.getParentNoteId()).get();
            newNote.setParentNote(parentNote);
            newNote.setColor(parentNote.getColor());
        }
        else{
            newNote.setColor(Colors.generateRandomColor());
        }
        newNote.setLink(processedName(noteDTO.getTitle()));
        List<String> tags = extractTags(noteDTO.getContent());
        if(!tags.isEmpty()){
            newNote.setTags(tags.stream().map(
                    (tagName)->{
                        Optional<Tag> tagInsideRepo = tagsRepository.findByName(tagName);
                        if(tagInsideRepo.isEmpty()){
                            Tag tag = new Tag(tagName);
                            tag.addNote(newNote);
                            tagsRepository.save(tag);
                            return tag;
                        }
                        tagInsideRepo.get().addNote(newNote);
                        return tagInsideRepo.get();
                    }
            ).collect(Collectors.toSet()));
        }
        return noteRepository.save(newNote);
    }
    public static  String removeHashtags(String content){
        content.replaceAll("#\\w+", "");
        return content;
    }
    public static List<String> extractTags(String input) {
        List<String> tags = new ArrayList<>();

        // Regular expression pattern to find hashtags
        Pattern pattern = Pattern.compile("#\\w+");

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(input);

        // Find all matches
        while (matcher.find()) {
            // Add the matched tag (group) to the list
            tags.add(matcher.group().replace("#",""));
        }

        return tags;
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    String processedName(String actualName){
        return actualName.replaceAll("[^a-zA-Z0-9\\s]", "").replaceAll("\\s","-");
    }

    public NoteDTO getRootNoteByTitle(String title) {
        Note note = noteRepository.findByTitle(title);
        return (note!=null)?convert(note):null;
    }
}
