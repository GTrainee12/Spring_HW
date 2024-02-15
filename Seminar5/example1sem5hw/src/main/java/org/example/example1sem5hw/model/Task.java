package org.example.example1sem5hw.model;


import jakarta.persistence.*;
import lombok.Data;
import org.example.example1sem5hw.interfaceBD.TaskStatus;


import java.time.LocalDateTime;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime creationDate = LocalDateTime.now();


}