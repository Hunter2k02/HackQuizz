package HackerQuizz.service;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.model.Quiz;
import HackerQuizz.repository.ProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {
    private final ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<Progress> getProgresses(AppUser user) {
        List<Progress> progressList = progressRepository.findByUser(user);
        System.out.println(progressList);

        return progressList;
    }
    public Progress getProgressByName(AppUser user, String name ) {
        return progressRepository.findByUserAndGeneralQuizTopic(user, name);
    }
    public void updateCompletedModules(AppUser user, Quiz quiz) {
        String progessName = quiz.getQuizName().split(" ")[0];
        String quizLevel = quiz.getQuizName().split(" ")[1];
        Progress progress = progressRepository.findByUserAndGeneralQuizTopic(user, progessName);
        if(progress.getCompletedNumberOfModules() == 0 && quizLevel.equals("Basics")){
            progress.setCompletedNumberOfModules(1);
            progressRepository.save(progress);
        }else if(progress.getCompletedNumberOfModules() == 1 && quizLevel.equals("Intermediate")){
            progress.setCompletedNumberOfModules(2);
            progressRepository.save(progress);
        }else if(progress.getCompletedNumberOfModules() == 2 && quizLevel.equals("Advanced")){
            progress.setCompletedNumberOfModules(3);
            progressRepository.save(progress);
        }

    }
    public void save(Progress progress) {
        progressRepository.save(progress);
    }
    public void deleteByUser(AppUser user) {
        progressRepository.deleteByUser(user);
    }
}
