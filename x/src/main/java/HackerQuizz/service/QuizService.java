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
    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }



}
