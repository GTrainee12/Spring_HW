package org.example.example1sem10hw.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllNotes() {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost8080:" + port + "/notes", Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetNoteById() {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost8080:" + port + "/notes/1", Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}