package HackerQuizz.controller;

import HackerQuizz.repository.UserRepository;
import HackerQuizz.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HackQuizz/api/v1")
public class APIController {
    private final UserService userService;
    private final UserRepository userRepository;
    public APIController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

}
