package ru.geekbrain.example3sem3hometask.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.geekbrain.example3sem3hometask.domain.User;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

}
