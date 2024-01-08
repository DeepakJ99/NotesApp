package com.example.Notes_App_Backend.DTO;

import com.example.Notes_App_Backend.entities.Note;
import com.example.Notes_App_Backend.entities.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class NoteDTO {
    private Long id;
    private String title;
    private String content;
    long parentNoteId;
    private String color;
    private String link;
    private Set<String> tags = new HashSet<>();
    private List<NoteDTO> children;
}
