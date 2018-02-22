package com.example.caffae.studentsc.Class;

import android.content.Context;

import com.example.caffae.studentsc.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dorette_ong on 20/2/2018.
 */

public class QuizCheckAnswer {

    private List<QuizItem> questionsanswer;
    private ArrayList<String> answerid;
    private String jsonString;

    public String getjsonstring(){
        return jsonString;
    }

    public void getStringFromJson(Context context) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.quizsample)));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        jsonString = sb.toString()  ;
    }

    public void convertJSON(Context context){
        getStringFromJson(context);
        Gson gson = new Gson();
        @SuppressWarnings("serial")
        Type collectionType = new TypeToken<List<QuizItem>>() {}.getType();
        questionsanswer = gson.fromJson(jsonString, collectionType);
    }
    public void setquestionanswer( List<QuizItem> questionans){
        questionsanswer = questionans;
    }
    public List<QuizItem>   getquestionanswer(){
        return questionsanswer;
    }
    public int printScore(ArrayList<Integer> buttonnumber, List<QuizItem> quizitemlist){
        int sum = 0;
        for (int i= 0; i<buttonnumber.size();i++){
            if (buttonnumber.get(i)==Integer.parseInt(quizitemlist.get(i).getAnswer())){
                sum+=1;
            }
        }
        return sum;
    }

}
