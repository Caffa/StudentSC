package com.example.caffae.studentsc;

/**
 * Created by Caffae on 21/2/18.
 */

public class ForumQuestion {
    private String question, answer;
    private boolean answered;


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

    }

    public ForumQuestion(String q, String a){
        this.answered = true;
        this.question = q;
        this.answer = a;
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
        if(answer.equals("")){
            answered = false;
        }else{
            answered = true;
        }
    }
}
