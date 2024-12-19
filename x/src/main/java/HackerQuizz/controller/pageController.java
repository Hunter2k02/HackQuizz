package HackerQuizz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/HackQuizz")
@Controller
public class pageController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }
    @GetMapping("/passwordreminder")
    public String passwordreminder() {
        return "passwordreminder";
    }
}
