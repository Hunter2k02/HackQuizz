package HackerQuizz.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    String selectedQuiz;
    String question;
    String answerA;
    String answerB;
    String answerC;
    String answerD;
    String answerCorrect;
    public QuestionDTO(){}

    public void setSelectedQuiz(String selectedQuiz) {
        this.selectedQuiz = selectedQuiz;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public String getSelectedQuiz() {
        return selectedQuiz;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "selectedQuiz='" + selectedQuiz + '\'' +
                ", question='" + question + '\'' +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", answerCorrect='" + answerCorrect + '\'' +
                '}';
    }
}
