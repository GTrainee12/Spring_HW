package org.example.example1sem6hw.service;

import org.example.example1sem6hw.model.Note;
import org.example.example1sem6hw.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void addNewNote(Note note) {
        noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public void updateNote(Note updatedNote) {
        noteRepository.save(updatedNote);
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}