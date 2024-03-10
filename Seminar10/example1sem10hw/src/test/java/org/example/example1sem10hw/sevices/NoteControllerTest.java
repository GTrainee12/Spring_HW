package org.example.example1sem10hw.sevices;

import config.NoteRepository;
import controller.NoteController;
import model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteControllerTest {

    @MockBean
    private NoteRepository noteRepository;

    private NoteController noteController;

    @BeforeEach
    public void setUp() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1L, "Test Note 1", "Content 1", LocalDateTime.now()));
        notes.add(new Note(2L, "Test Note 2", "Content 2", LocalDateTime.now()));

        when(noteRepository.findAll()).thenReturn(notes);
        when(noteRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(notes.get(0)));
    }

    @Test
    public void testGetAllNotes() {
        List<Note> notes = noteController.getAllNotes().getBody();
        assertEquals(2, notes.size());
    }

    @Test
    public void testGetNoteById() {
        ResponseEntity<Note> responseEntity = noteController.getNoteById(1L);
        Note note = responseEntity.getBody();
        assertEquals("Test Note 1", note.getTitle());
    }
}
