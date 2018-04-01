package com.example.caffae.studentsc.Class;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.Forum.MyApplication;
import com.example.caffae.studentsc.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;


/**
 * Created by dorette_ong on 5/3/2018.
 */

public class DatabaseClassroom {
    public JSONArray[] jsonarray = new JSONArray[1];
    public String[] ongoing = new String[2];

    public void fetchDatabaseInfo(String URL) {
        final JsonArrayRequest request = new JsonArrayRequest(URL,
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

    public void fetchOngoing(String URL, final int number) {
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

    public void fetchQuizInfo(String ongoing) {
        String QuizURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/Quiz/Saved/" + ongoing + ".json";
        this.fetchDatabaseInfo(QuizURL);
    }

    public void fetchBroadCastInfo(String ongoing) {
        String BroadcastURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/BroadcastQuestion/" + ongoing + ".json";
        //String BroadcastURL = "https://softwareconstruct-forum.firebaseio.com/"+ ClassroomIDActivity.getClassroomID()+"/BroadcastQuestion.json";

        this.fetchDatabaseInfo(BroadcastURL);
    }

    public void fetchOngoingBroadcast() {
        String OngoingBroadcastURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/BroadcastQuestion/Ongoing.json";
        this.fetchOngoing(OngoingBroadcastURL, 1);
    }

    public void fetchOngoingQuiz() {
        String OngoingQuizURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/Quiz/Ongoing.json";
        this.fetchOngoing(OngoingQuizURL, 0);
    }

    public void pushQuizScores(final int QuizScore) {
        FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Quiz").child("Ongoing").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String QuizID = dataSnapshot.getValue().toString();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Grades").child("PerStudent").child(MainActivity.studentID);
                mDatabase.child(QuizID).setValue(Integer.toString(QuizScore));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });


    }


}

