package HackerQuizz.controller;

import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.repository.UserRepository;
import HackerQuizz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/HackQuizz")
@Controller
public class PageController {
    private final UserService userService;
    public PageController(UserService userService) {
        this.userService = userService;
    }

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
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegisterDTO userDto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("errors", result.getAllErrors());
            return "register";
        }
        if (userService.findByUsername(userDto.getUsername()).isPresent()) {
            result.rejectValue("username", null, "An account already exists with this username");
        }
        userService.save(userDto);
        return "redirect:/login?registered";
    }
}
