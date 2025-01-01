package HackerQuizz.service;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import HackerQuizz.repository.ProgressRepository;
import HackerQuizz.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {
    private final UserRepository userRepository;
    private final ProgressRepository progressRepository;

    public ProgressService(UserRepository userRepository, ProgressRepository progressRepository) {
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;

    }
    @PreAuthorize("#user.username == authentication.name")
    public List<Progress> getProgresses(AppUser user) {
        return progressRepository.findAllByUserId(user.getId());
    }

    @PreAuthorize("#user.username == authentication.name")
    public Progress getProgress(AppUser user) {
        Progress progress = progressRepository.findByUser(user);
        if (progress == null) {
            progress = new Progress();
            progress.setUser(user);
            progress.setCompletedModules(0);
            progress.setTotalModules(10);
            progressRepository.save(progress);
        }
        return progress;
    }
    @PreAuthorize("#user.username == authentication.name")
    public void updateProgressForUser(AppUser user, int score) {
        Progress progress = getProgress(user);
        progress.setCompletedModules(progress.getCompletedModules() + score);
        progressRepository.save(progress);
    }
    public AppUser getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof AppUser){
            return (AppUser) principal;
        }else if (principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            // Fetch the AppUser from the database
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with given username: " + username + "not found"));

        }
        return null;
    }
}
