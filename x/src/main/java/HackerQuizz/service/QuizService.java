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
    public void updateCompleted(long quizId, boolean completed) {
        quizRepository.updateById(quizId, completed);
    }
    public List<Quiz> getAllQuizWithProgressId(long progressId) {
        return quizRepository.findByProgress(progressId);
    }




}
