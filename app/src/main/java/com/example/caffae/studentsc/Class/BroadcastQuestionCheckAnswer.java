package com.example.caffae.studentsc.Class;

import java.util.ArrayList;

/**
 * Created by dorette_ong on 20/2/2018.
 */

public class BroadcastQuestionCheckAnswer {
    private ArrayList<String> questionsanswer;
    private int answerid;

    public void setquestionanswer(ArrayList<String> questionans){
        questionsanswer = questionans;
    }
    public ArrayList<String> getquestionanswer(){
        return questionsanswer;
    }
    public void setanswer(int i){answerid =i;}
    public int getanswer(){
        return answerid;
    }
}
