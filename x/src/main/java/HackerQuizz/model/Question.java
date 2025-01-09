package HackerQuizz.model;

import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String correctAnswer;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    @ManyToOne
    @JoinColumn(name="quiz_id",referencedColumnName = "id")
    private Quiz quiz;

    public Question(){}

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", quiz=" + quiz +
                '}';
    }
}
