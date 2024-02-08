package ru.geekbrain.example3sem3hometask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrain.example3sem3hometask.domain.User;
import ru.geekbrain.example3sem3hometask.services.RegistrationService;

import java.util.List;
/**
 * В классе UserController добавить обработчик userAddFromParam извлекающий данные для создания пользователя из параметров HTTP запроса
 * */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getRepository().getUsers().add(user);
        return "User added from body!";
    }
    @PostMapping("/param")
    public String userAddFromParam(@RequestParam("name") String name, @RequestParam("age") int age,@RequestParam("email")String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        service.getDataProcessingService().getRepository().getUsers().add(user);
        return "User added from parameters!";
    }
}

