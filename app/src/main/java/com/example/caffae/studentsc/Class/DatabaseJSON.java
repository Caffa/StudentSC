package com.example.caffae.studentsc.Class;

import android.provider.ContactsContract;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.caffae.studentsc.Forum.MyApplication;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static java.lang.Thread.sleep;

/**
 * Created by dorette_ong on 5/3/2018.
 */

public class DatabaseJSON {
    public JSONArray[] jsonarray = new JSONArray[1];
    public String[] ongoing = new String[2];
    public void fetchDatabaseInfo(String URL) {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jsonarray[0] = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }
    public void fetchOngoing(String URL,final int number) {
        StringRequest request = new StringRequest(URL,
                new Response.Listener<String>() {
            @Override
                    public void onResponse(String response) {
                        ongoing[number] = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MyApplication.getInstance().addToRequestQueue(request);
    }

    public void fetchQuizInfo(String ongoing) throws IOException, JSONException, InterruptedException {
       String QuizURL = "https://softwareconstruct-forum.firebaseio.com/Classroom1/Quiz/Saved/" + ongoing+ ".json";
       this.fetchDatabaseInfo(QuizURL);
    }

    public void fetchBroadCastInfo(String ongoing){
        String BroadcastURL = "https://softwareconstruct-forum.firebaseio.com/Classroom1/BroadcastQuestion/" + ongoing+".json";
        this.fetchDatabaseInfo(BroadcastURL);
    }
    public void fetchOngoingBroadcast() throws IOException, JSONException {
        String OngoingBroadcastURL = "https://softwareconstruct-forum.firebaseio.com/Classroom1/BroadcastQuestion/Ongoing.json";
        this.fetchOngoing(OngoingBroadcastURL,1);
    }
    public void fetchOngoingQuiz() throws IOException, JSONException {
        String OngoingQuizURL = "https://softwareconstruct-forum.firebaseio.com/Classroom1/Quiz/Ongoing.json";
        this.fetchOngoing(OngoingQuizURL,0);
    }
}