package HackerQuizz.service;

import HackerQuizz.model.Question;
import HackerQuizz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public List<Question> getQuestionsForQuiz(int quizId){
        return questionRepository.findAllByUserId((long) quizId);
    }
    public boolean isAnswerCorrect(int quizId, int questionNumber, String answer){
        List<Question> questions = getQuestionsForQuiz(quizId);
        System.out.println("Given: " + answer);
        System.out.println("Correct Answer: " + questions.get(questionNumber).getCorrectAnswer());
        return answer.equals(questions.get(questionNumber).getCorrectAnswer());
    }
    public int getTotalNumberOfQuestionsForQuiz(int quizId){
        return this.getQuestionsForQuiz(quizId).size();
    }
}
