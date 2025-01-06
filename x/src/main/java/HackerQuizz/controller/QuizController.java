package HackerQuizz.controller;

import HackerQuizz.model.Question;
import HackerQuizz.model.Quiz;
import HackerQuizz.service.QuestionService;
import HackerQuizz.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
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

    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
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
        List<Question> questions = questionService.getQuestionsForQuiz(quizId);
        session.setAttribute("currentQuestion", questions.get((Integer) session.getAttribute("currentQuestionNumber")));

        return "quiz";
    }
    @PostMapping("/answer")
    public String submitAnswer(
            @RequestParam String answer,
            HttpSession session,
            Model model){
        System.out.println(session.getId());
        int quizId = (int) session.getAttribute("quizId");
        int currentQuestionNumber = (int) session.getAttribute("currentQuestionNumber");
        int score = (int) session.getAttribute("score");

        if(questionService.isAnswerCorrect(quizId, currentQuestionNumber, answer)){
            score++;
        }
        System.out.println("Local score: " + score);
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
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            System.out.println("Attribute: " + attributeName);
            if (attributeName.equals("SPRING_SECURITY_CONTEXT")) {
                continue;
            }
            session.removeAttribute(attributeName);
        }

        model.addAttribute("finalScore", finalScore);
        return "finalScore";
    }

}
