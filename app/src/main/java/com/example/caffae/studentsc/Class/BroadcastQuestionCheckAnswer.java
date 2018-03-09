package com.example.caffae.studentsc.Class;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dorette_ong on 20/2/2018.
 */

public class BroadcastQuestionCheckAnswer {
    private ArrayList<QuizItem> questionsanswer;

    public void convertJSON(String jsonstring){
        // getStringFromJson(context);
        Gson gson = new Gson();
        @SuppressWarnings("serial")
        Type collectionType = new TypeToken<List<QuizItem>>() {}.getType();
        questionsanswer = gson.fromJson(jsonstring, collectionType);
    }

    public ArrayList<QuizItem> getquestionanswer(){
        return questionsanswer;
    }

}
