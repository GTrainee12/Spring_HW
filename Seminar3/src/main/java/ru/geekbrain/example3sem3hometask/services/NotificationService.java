package ru.geekbrain.example3sem3hometask.services;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(String s) {
        System.out.println(s);
    }
}
