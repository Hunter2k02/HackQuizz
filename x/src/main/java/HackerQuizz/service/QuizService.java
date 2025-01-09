package HackerQuizz.service;


import HackerQuizz.model.Quiz;
import HackerQuizz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizService {

    private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz getQuiz(int quizId) {
        return quizRepository.findById(quizId);
    }
    public Quiz getQuiz(String quizName) {
        return quizRepository.findByName(quizName);
    }
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }



}
