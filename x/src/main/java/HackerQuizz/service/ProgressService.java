package HackerQuizz.service;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.repository.ProgressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressService {
    private ProgressRepository progressRepository;
    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<Progress> getProgresses(AppUser user) {
        List<Progress> progressList = new ArrayList<>();

        return new ArrayList<>();
    }
}