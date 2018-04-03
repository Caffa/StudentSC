package com.example.caffae.studentsc.Classroom;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dorette_ong on 20/2/2018.
 */

public class QuizAndBroadcastMain {

    private ArrayList<QuizItem> questionsanswer;
    private int sum =0;

    // Convert JSON from jsonstring to ArrayList of QuizItems
    public ArrayList<QuizItem> convertJSON(String jsonstring){
        Gson gson = new Gson();
        @SuppressWarnings("serial")
        Type collectionType = new TypeToken<ArrayList<QuizItem>>() {}.getType();
        questionsanswer = gson.fromJson(jsonstring, collectionType);
        return questionsanswer;
    }

    public ArrayList<QuizItem>   getquestionanswer(){
        return questionsanswer;
    }

    //Calculate score for Quiz
    public double getScore(){
        return sum;
    }

    //Calculate score for Quiz
    public void multipleChoiceScore(ArrayList<Integer> buttonnumber, ArrayList<QuizItem> quizitemlist){
        for (int i= 0; i<buttonnumber.size();i++){
            if (buttonnumber.get(i)==Integer.parseInt(quizitemlist.get(i).getAnswer())){
                sum+=1;
            }
        }

    }
    public void shortAnswerScore(String answer, String input){
        if (answer.equals(input)){
            sum+=1;
        }

    }




}
