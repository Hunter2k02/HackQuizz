package HackerQuizz.model;

import jakarta.persistence.*;

@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String generalQuizTopic;
    private int totalNumberOfModules;
    private int completedNumberOfModules;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    public Progress(String generalQuizTopic, AppUser user) {
        this.generalQuizTopic = generalQuizTopic;
        this.totalNumberOfModules = 3;
        this.completedNumberOfModules = 0;
        this.user = user;

    }
    public Progress() {}

    public Long getId() {
        return id;
    }

    public String getGeneralQuizTopic() {
        return generalQuizTopic;
    }

    public void setGeneralQuizTopic(String generalQuizTopic) {
        this.generalQuizTopic = generalQuizTopic;
    }

    public int getTotalNumberOfModules() {
        return totalNumberOfModules;
    }

    public void setTotalNumberOfModules(int totalNumberOfModules) {
        this.totalNumberOfModules = totalNumberOfModules;
    }

    public int getCompletedNumberOfModules() {
        return completedNumberOfModules;
    }

    public void setCompletedNumberOfModules(int completedNumberOfModules) {
        this.completedNumberOfModules = completedNumberOfModules;
    }

    public AppUser getUser() {
        return user;
    }
}
