package org.example.exam1sem11hw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;

@Entity
@Data
public class Note {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // Getters and Setters

    // Constructor to set createdAt
    public Note() {
        this.createdAt = LocalDateTime.now();
    }
}
