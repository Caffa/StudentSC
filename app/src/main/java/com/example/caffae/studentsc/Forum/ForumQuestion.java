package com.example.caffae.studentsc.Forum;

/**
 * Created by Caffae on 21/2/18.
 */

public class ForumQuestion {
    private String question, answer;
    private boolean answered;
    public boolean show;


    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public ForumQuestion(String q){
        this.answered = false;
        this.question = q;
        this.answer= "";
        this.show = true;

    }

    public ForumQuestion(String q, String a){
        this.answered = true;
        this.question = q;
        this.answer = a;
        this.show = true;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        if(answer == null){
            this.answer = "";
        }
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        answered = !answer.equals("");
    }

    public void setDontShow() {
        this.show = false;
    }
}
