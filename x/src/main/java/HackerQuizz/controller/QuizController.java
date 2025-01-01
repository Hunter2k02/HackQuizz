package HackerQuizz.controller;

import HackerQuizz.service.ProgressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/HackerQuizz/Quiz")
public class QuizController {
    private ProgressService progressService;
}
