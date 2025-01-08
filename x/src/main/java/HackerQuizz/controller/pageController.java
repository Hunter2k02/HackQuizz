package HackerQuizz.controller;

import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.service.ProgressService;
import HackerQuizz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/HackQuizz")
@Controller
public class PageController {
    private final UserService userService;

    private final ProgressService progressService;

    public PageController(UserService userService, ProgressService progressService) {
        this.userService = userService;

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
    @GetMapping("/updateUser/{response}")
    public String updateUser(@PathVariable String response, Model model) {
        model.addAttribute("message", response);
        return "updateUser";
    }
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") @Valid UserRegisterDTO userDto) {
        if (userService.findByUsername(userDto.getUsername()).isPresent()) {
            AppUser user = new AppUser();
            userService.save(user);
            String message = "User updated successfully";
            return "redirect:/HackQuizz/updateUser/" + message;
        }else{
            String message = "There is no user with given username";
            return "redirect:/HackQuizz/updateUser/" + message;
        }

    }
    @GetMapping("/deleteUser")
    public String deleteUser(Model model) {
        return "deleteUser";
    }
    @GetMapping("/deleteUser/{response}")
    public String deleteUser(@PathVariable String response, Model model) {
        model.addAttribute("message", response);
        return "deleteUser";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("user") UserRegisterDTO userDto, Model model)
    {
        if (userService.findByUsername(userDto.getUsername()).isPresent()){
            AppUser user = userService.getByUsername(userDto.getUsername());
            if(!Objects.equals(user.getRole(), "ROLE_ADMIN")){
                userService.delete(user);
                String message = "User deleted successfully";
                return "redirect:/HackQuizz/deleteUser/" + message;
            }
            else{
                String message = "Admin user cannot be deleted";
                return "redirect:/HackQuizz/deleteUser/" + message;
            }
        }else{
            String message = "There is no user with given username";
            return "redirect:/HackQuizz/deleteUser/" + message;
        }
    }



    @GetMapping("/register")
    public String register(@RequestParam(value = "success", required = false) String success, Model model) {

        model.addAttribute("success", false);
        model.addAttribute("user", new UserRegisterDTO());
        if (success != null) {
            model.addAttribute("success", true);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegisterDTO userDto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("message", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
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
