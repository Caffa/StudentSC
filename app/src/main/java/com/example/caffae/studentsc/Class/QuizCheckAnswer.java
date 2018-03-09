package com.example.caffae.studentsc.Class;

import android.content.Context;

import com.example.caffae.studentsc.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by dorette_ong on 20/2/2018.
 */

public class QuizCheckAnswer {

    private ArrayList<QuizItem> questionsanswer;
    private ArrayList<String> answerid;

    public ArrayList<QuizItem> convertJSON(String jsonstring){
       // getStringFromJson(context);
        Gson gson = new Gson();
        @SuppressWarnings("serial")
        Type collectionType = new TypeToken<ArrayList<QuizItem>>() {}.getType();
        questionsanswer = gson.fromJson(jsonstring, collectionType);
        return questionsanswer;
    }

    public ArrayList<QuizItem>   getquestionanswer(){
        return questionsanswer;
    }
    public int printScore(ArrayList<Integer> buttonnumber, ArrayList<QuizItem> quizitemlist){
        int sum = 0;
        for (int i= 0; i<buttonnumber.size();i++){
            if (buttonnumber.get(i)==Integer.parseInt(quizitemlist.get(i).getAnswer())){
                sum+=1;
            }
        }
        return sum;
    }

}
