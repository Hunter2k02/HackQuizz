package HackerQuizz.service;

import HackerQuizz.model.Question;
import HackerQuizz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return answer.equals(questions.get(questionNumber).getCorrectAnswer());
    }
    public int getTotalNumberOfQuestionsForQuiz(int quizId){
        return this.getQuestionsForQuiz(quizId).size();
    }
    public Question getByQuestion(String question){
        return questionRepository.findByQuestion(question);
    }
    public void save(Question question){
        questionRepository.save(question);
    }
    public List<Question> getAll(){
        return questionRepository.findAll();
    }
    public Optional<Question> findById(int id){
        return questionRepository.findById(id);
    }
    public void deleteById(int id){
        questionRepository.deleteById(id);
    }
}
