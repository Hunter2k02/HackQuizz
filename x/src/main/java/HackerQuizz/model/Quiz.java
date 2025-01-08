package HackerQuizz.model;

import jakarta.persistence.*;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quizName;
    @ManyToOne
    @JoinColumn(name = "progress_id", referencedColumnName = "id")
    private Progress progress;
    private boolean completed;

    public Quiz(String quizName,Progress progress) {
        this.quizName = quizName;
        this.progress = progress;
        this.completed = false;
    }
    public Quiz() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizName() {
        return quizName;
    }
    public void setProgress(Progress progress) {
        this.progress = progress;
    }
    public Progress getProgress() {
        return progress;
    }


}
