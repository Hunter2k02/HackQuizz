package HackerQuizz.controller;

import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.repository.UserRepository;
import HackerQuizz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
