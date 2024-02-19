package org.example.example1sem6hw.repository;

import org.example.example1sem6hw.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
