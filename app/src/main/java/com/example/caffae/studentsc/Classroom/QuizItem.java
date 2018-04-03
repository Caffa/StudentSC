package com.example.caffae.studentsc.Classroom;

/**
 * Created by dorette_ong on 20/2/2018.
 */

public class QuizItem {

    private String Question;
    private String Answer;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    private String Type;

    public String getAnswer() {
        return Answer;
    }
    public String getOption1() {return Option1;}
    public String getOption2() {
        return Option2;
    }
    public String getQuestion() {
        return Question;
    }
    public String getOption3() {
        return Option3;
    }
    public String getOption4(){
        return Option4;
    }
    public String getType(){
        return Type;
    }

    public void setAnswer(String answer){
        Answer = answer;
    }
    public void setOption1(String option1){
       Option1 = option1;
    }
    public void setOption2(String option2){
        Option2 = option2;
    }
    public void setOption3(String option3){Option3= option3;}
    public void setOption4(String option4){Option4= option4;}
    public void setQuestion(String question) {
        Question = question;
    }
}