package org.example.example1sem7hw.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/private-data")
    public String getPrivateData() {
        // Проверит роль пользователя
        // Если роль не ADMIN, выбросит исключение или вернет ошибку доступа
        return "Private data";
    }

    @GetMapping("/public-data")
    public String getPublicData() {
        return "Public data";
    }
}