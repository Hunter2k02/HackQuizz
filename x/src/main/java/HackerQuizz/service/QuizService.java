package HackerQuizz.service;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Quiz;
import HackerQuizz.repository.QuizRepository;
import HackerQuizz.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;


    public QuizService(UserRepository userRepository, QuizRepository quizRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;

    }
    @PreAuthorize("#user.username == authentication.name")
    public List<Quiz> getQuizzes(AppUser user) {
        return null;
    }




}
