package com.example.Notes_App_Backend.entities;


import com.example.Notes_App_Backend.DTO.NoteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String content;

    private String color;
    @ManyToOne
    @JoinColumn(name = "parent_note_id")
    private Note parentNote;

    private String link;
    @JsonIgnore
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @ManyToMany
    @JoinTable(
            name = "note_tags",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();


    public Note(long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }
    public Note(long id, String title, String content, Note parent, Set<Tag> tags){
        this.id = id;
        this.title = title;
        this.content = content;
        this.parentNote = parent;
        this.tags = tags;
    }



}
