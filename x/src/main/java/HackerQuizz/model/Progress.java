package HackerQuizz.model;

import jakarta.persistence.*;

@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizName;
    private int quizNumber;
    private int completedModules;
    private int totalModules;

    @OneToOne
    @JoinColumn(name="id")
    private AppUser user;

    public Progress(String quizName, int quizNumber, int completedModules, int totalModules, AppUser user) {
        this.quizName = quizName;
        this.quizNumber = quizNumber;
        this.completedModules = completedModules;
        this.totalModules = totalModules;
        this.user = user;
    }
    public Progress() {}


    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public void setCompletedModules(int completedModules) {
        this.completedModules = completedModules;
    }

    public void setTotalModules(int totalModules) {
        this.totalModules = totalModules;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public int getQuizNumber() {
        return quizNumber;
    }

    public int getCompletedModules() {
        return completedModules;
    }

    public int getTotalModules() {
        return totalModules;
    }

    public AppUser getUser() {
        return user;
    }
}
