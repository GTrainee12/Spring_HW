package org.example.example1sem6hw.controller;

import org.example.example1sem6hw.model.Note;
import org.example.example1sem6hw.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        if (note.getTitle() == null || note.getTitle().isEmpty() || note.getContent() == null || note.getContent().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        note.setCreatedAt(LocalDateTime.now());
        Note savedNote = noteRepository.save(note);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNoteById(@PathVariable Long id, @RequestBody Note updatedNote) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            return ResponseEntity.notFound().build();
        }

        if (updatedNote.getTitle() == null || updatedNote.getTitle().isEmpty() || updatedNote.getContent() == null || updatedNote.getContent().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        Note savedNote = noteRepository.save(note);

        return ResponseEntity.ok(savedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            return ResponseEntity.notFound().build();
        }

        noteRepository.delete(note);
        return ResponseEntity.noContent().build();
    }
}