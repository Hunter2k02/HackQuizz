package HackerQuizz.controller;

import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.service.ProgressService;
import HackerQuizz.service.QuizService;
import HackerQuizz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/HackQuizz")
@Controller
public class PageController {
    private final UserService userService;
    private final QuizService quizService;
    private final ProgressService progressService;

    public PageController(UserService userService, QuizService quizService, ProgressService progressService) {
        this.userService = userService;
        this.quizService = quizService;
        this.progressService = progressService;
    }
    // All users permited
    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }
    // All users permited default user
    @GetMapping("/home")
    public String home(Model model) {
        AppUser currentUser = userService.getCurrentUser();
        List<Progress> listOfTopics = progressService.getProgresses(currentUser);
        model.addAttribute("listOfTopics", listOfTopics);
        model.addAttribute("currentUser", currentUser);
        return "home";
    }
    // Admin only permited endpoints
    @GetMapping("/admin-home")
    public String adminHome() {
        return "admin-home";
    }
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/showUsers")
    public String showUsers(Model model) {
        List<AppUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }
    @GetMapping("/updateUser")
    public String updateUser(Model model) {
        return "updateUser";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(Model model) {
        return "deleteUser";
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

        progressService.save(new Progress("Python", userService.getCurrentUser()));
        progressService.save(new Progress("Java", userService.getCurrentUser()));
        userService.save(userDto);
        return "redirect:/HackQuizz/register?success=true";
    }






}
