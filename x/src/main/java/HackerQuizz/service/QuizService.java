package HackerQuizz.service;


import HackerQuizz.model.Quiz;
import HackerQuizz.repository.QuizRepository;
import HackerQuizz.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(UserRepository userRepository, QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz getQuiz(int quizId) {
        return quizRepository.findById(quizId);
    }





}
