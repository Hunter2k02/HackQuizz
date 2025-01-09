package HackerQuizz.controller;

import HackerQuizz.dto.QuestionDTO;
import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.model.Question;
import HackerQuizz.model.Quiz;
import HackerQuizz.service.ProgressService;
import HackerQuizz.service.QuestionService;
import HackerQuizz.service.QuizService;
import HackerQuizz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/HackQuizz")
@Controller
public class PageController {
    private final UserService userService;
    private final QuizService quizService;
    private final ProgressService progressService;
    private final QuestionService questionService;

    public PageController(UserService userService, QuizService quizService, ProgressService progressService, QuestionService questionService) {
        this.userService = userService;
        this.quizService = quizService;
        this.progressService = progressService;
        this.questionService = questionService;
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
    // User related
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
            AppUser user = userService.findByUsername(userDto.getUsername()).get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());



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


    // Quiz / Question related
    @GetMapping("/quizManagement")
    public String quizManagement(Model model) {
        return "quizManagement";
    }

    @GetMapping("/newQuestion")
    public String newQuestion(@RequestParam(name = "success", required = false) String success, Model model) {
        model.addAttribute("success", false);
        model.addAttribute("user", new UserRegisterDTO());
        if (success != null) {
            model.addAttribute("success", true);
        }
        List<Quiz> options = quizService.getAllQuiz();
        model.addAttribute("question", new QuestionDTO());
        List<String> answers = List.of("a", "b", "c", "d");
        model.addAttribute("options", options);
        model.addAttribute("answers", answers);
        model.addAttribute("selectedOption", "");
        model.addAttribute("selectedAnswer", "");

        return "newQuestion";
    }
    @PostMapping("/newQuestion")
    public String newQuestion(@ModelAttribute("question") QuestionDTO question, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("message", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return "newQuestion";
        }
        if (questionService.getByQuestion(question.getQuestion()) != null){
            result.rejectValue("question", null, "An question already exists with this content");
        }

        Question q = new Question();
        q.setQuiz(quizService.getQuiz(question.getSelectedQuiz()));
        q.setQuestion(question.getQuestion());
        q.setAnswerA(question.getAnswerA());
        q.setAnswerB(question.getAnswerB());
        q.setAnswerC(question.getAnswerC());
        q.setAnswerD(question.getAnswerD());
        switch (question.getAnswerCorrect()){
            case "a":q.setCorrectAnswer(question.getAnswerA());
            break;
            case "b":q.setCorrectAnswer(question.getAnswerB());
            break;
            case "c":q.setCorrectAnswer(question.getAnswerC());
            break;
            case "d":q.setCorrectAnswer(question.getAnswerD());
            break;
        }
        questionService.save(q);

        return "redirect:newQuestion?success=true";
    }
    @GetMapping("/deleteQuestion")
    public String deleteQuestion(){
        return "deleteQuestion";
    }
    @GetMapping("/deleteQuestion/{response}")
    public String deleteQuestion(@PathVariable String response, Model model) {
        model.addAttribute("message", response);
        return "deleteQuestion";
    }
    @PostMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam("id") Integer id, Model model)
    {
        if (questionService.findById(id).isPresent()){
            Question q = questionService.findById(id).get();

            questionService.deleteById(q.getId().intValue());
            String message = "Question deleted successfully";
            return "redirect:/HackQuizz/deleteQuestion/" + message;

        }else{
            String message = "There is no question with given id";
            return "redirect:/HackQuizz/deleteQuestion/" + message;
        }
    }

    @GetMapping("/viewAllQuestions")
    public String viewAllQuestions(Model model) {
        List<Question> questions = questionService.getAll();
        Collections.sort(questions);
        model.addAttribute("questions", questions);
        return "viewAllQuestions";
    }






}
