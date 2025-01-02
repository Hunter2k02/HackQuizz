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

    public Quiz(String quizName,Progress progress) {
        this.quizName = quizName;
        this.progress = progress;
    }
    public Quiz() {}

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
