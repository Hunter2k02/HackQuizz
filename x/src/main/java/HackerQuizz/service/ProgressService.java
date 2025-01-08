package HackerQuizz.service;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.model.Quiz;
import HackerQuizz.repository.ProgressRepository;
import HackerQuizz.repository.QuizRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProgressService {
    private ProgressRepository progressRepository;
    private QuizService quizService;
    public ProgressService(ProgressRepository progressRepository, QuizService quizService) {
        this.progressRepository = progressRepository;
        this.quizService = quizService;
    }

    public List<Progress> getProgresses(AppUser user) {
        List<Progress> progressList = progressRepository.findByUser(user);
        System.out.println(progressList);

        return progressList;
    }
    public Progress getProgressByName(AppUser user, String name ) {
        return progressRepository.findByUserAndGeneralQuizTopic(user, name);
    }
    public void updateCompletedModules(Quiz quiz) {
        System.out.println(quiz.isCompleted());
        System.out.println(!(quiz.isCompleted()));
        if(!(quiz.isCompleted())){
            int completed = 0;
            quizService.updateCompleted(quiz.getId(), true);
            long progressId = quiz.getProgress().getId();
            List<Quiz> quizzes = quizService.getAllQuizWithProgressId(progressId);
            for(Quiz qu : quizzes){
                if(qu.isCompleted()){
                    completed++;
                }
            }
            progressRepository.updateNumberOfCompletedModulesById(progressId, completed+1);

        }


    }
}
