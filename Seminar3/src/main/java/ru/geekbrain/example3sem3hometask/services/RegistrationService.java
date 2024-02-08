package ru.geekbrain.example3sem3hometask.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrain.example3sem3hometask.domain.User;

/**
 * В класс RegistrationService добавить поля UserService, NotificationService(добавить в IOC контейнер аннотацией @Autowired или через конструктор класса)
 * Разработать метод processRegistration в котором:
 * - создается пользователь из параметров метода
 * - созданный пользователь добавляется в репозиторий
 * - через notificationService выводится сообщение в консоль
 */
@Getter
@Service
public class RegistrationService {

    @Autowired
    private DataProcessingService dataProcessingService;

    //Поля UserService, NotificationService

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;


    @Autowired
    public RegistrationService(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }


    //Метод processRegistration
    public void processRegistration(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        userService.addUser(user);
        notificationService.sendNotification("New user registered: " + user.getName());
    }
}





