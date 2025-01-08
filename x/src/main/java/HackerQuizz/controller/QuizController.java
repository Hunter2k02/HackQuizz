package HackerQuizz.controller;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.model.Question;
import HackerQuizz.model.Quiz;
import HackerQuizz.service.ProgressService;
import HackerQuizz.service.QuestionService;
import HackerQuizz.service.QuizService;
import HackerQuizz.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;


@RequestMapping("/HackQuizz/Quiz")
@Controller
@SessionAttributes("quizSession")
public class QuizController {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final ProgressService progressService;
    private final UserService userService;

    public QuizController(QuizService quizService, QuestionService questionService, ProgressService progressService, UserService userService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.progressService = progressService;
        this.userService = userService;
    }

    @GetMapping("/leave-quiz")
    public String leaveQuiz(HttpSession session, Model model) {
        model.addAttribute("message", "Your session is about to expire. Do you want to continue?");
        return "sessionWarningPage"; // Show a warning message
    }
    @GetMapping("/start/{quizId}")
    public String startQuiz(@PathVariable int quizId, HttpSession session) {
        if(session.getAttribute("currentQuestionNumber") == null) {
            session.setAttribute("currentQuestionNumber", 0);
            session.setAttribute("score", 0);
            session.setAttribute("quizId", quizId);
            Quiz quiz = quizService.getQuiz(quizId);
            session.setAttribute("quiz", quiz);
        }
        else if((Integer) session.getAttribute("currentQuestionNumber") == 0){
            session.invalidate();
            return "redirect:/HackQuizz/Quiz/leave-quiz";
        }
        List<Question> questions = questionService.getQuestionsForQuiz(quizId);
        session.setAttribute("currentQuestion", questions.get((Integer) session.getAttribute("currentQuestionNumber")));

        return "quiz";
    }
    @PostMapping("/answer")
    public String submitAnswer(
            @RequestParam String answer,
            HttpSession session,
            Model model){

        int quizId = (int) session.getAttribute("quizId");
        int currentQuestionNumber = (int) session.getAttribute("currentQuestionNumber");
        int score = (int) session.getAttribute("score");

        if(questionService.isAnswerCorrect(quizId, currentQuestionNumber, answer)){
            score++;
        }
        session.setAttribute("score", score);
        session.setAttribute("currentQuestionNumber", currentQuestionNumber + 1);
        if(currentQuestionNumber + 1 < questionService.getTotalNumberOfQuestionsForQuiz(quizId)){
            return "redirect:/HackQuizz/Quiz/start/" + quizId;
        }else{

            return "redirect:/HackQuizz/Quiz/finalScore/" + score;
        }

    }
    @GetMapping("/finalScore/{finalScore}")
    public String finalScore(@PathVariable int finalScore, Model model, HttpSession session){

        List<Question> questions = questionService.getQuestionsForQuiz((Integer) session.getAttribute("quizId"));
        model.addAttribute("message", "You haven't completed this quiz. You need to score at least 80% to pass.");
        // Checking if user had passed the quiz; User has to score above 80% to score; If condition is met, the progress is updated;
        if(finalScore > questions.size()*0.8){
            progressService.updateCompletedModules(userService.getCurrentUser(), quizService.getQuiz((Integer) session.getAttribute("quizId")));
            model.addAttribute("message", "You have completed this quiz");
        }

        // Delete user attributes before next quiz;
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            if (attributeName.equals("SPRING_SECURITY_CONTEXT")) {
                continue;
            }
            session.removeAttribute(attributeName);
        }

        model.addAttribute("finalScore", finalScore);
        return "finalScore";
    }

    @GetMapping("/Python")
    public String python(Model model) {
        AppUser currentUser = userService.getCurrentUser();
        Progress progress = progressService.getProgressByName(currentUser, "Python");
        model.addAttribute("progress", progress);
        return "python";
    }
    @GetMapping("/Java")
    public String java() {
        return "java";
    }

}
