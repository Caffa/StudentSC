package com.example.caffae.studentsc.Classroom;

import android.provider.ContactsContract;

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

import java.nio.DoubleBuffer;


/**
 * Created by dorette_ong on 5/3/2018.
 */

public class DatabaseClassroom {
    private JSONArray[] jsonarray = new JSONArray[1];
    private String[] ongoing = new String[2];
    //get data from URL as a jsonarray
    private void fetchDatabaseInfo(String URL) {
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

    //Get String value from URL
    private void fetchOngoing(String URL, final int number) {
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

    void fetchQuizInfo(String ongoing) {
        String QuizURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/Quiz/Saved/" + ongoing + ".json";
        this.fetchDatabaseInfo(QuizURL);
    }

    void fetchBroadCastInfo(String ongoing) {
        String BroadcastURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/BroadcastQuestion/" + ongoing  +".json";
        this.fetchDatabaseInfo(BroadcastURL);
    }
    // Save ongoing broadcast question number in ongoing[1]
    public void fetchOngoingBroadcast() {
        String OngoingBroadcastURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/BroadcastQuestion/Ongoing.json";
        this.fetchOngoing(OngoingBroadcastURL, 1);
    }
    // Save ongoing quiz number in ongoing[0]
    public void fetchOngoingQuiz() {
        String OngoingQuizURL = "https://softwareconstruct-forum.firebaseio.com/" + ClassroomIDActivity.getClassroomID() + "/Quiz/Ongoing.json";
        this.fetchOngoing(OngoingQuizURL, 0);
    }
    // Save quiz scores into database with key: QuizID, value: QuizScore into student's grades
    void pushQuizScores(final double QuizScore) {
        FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Quiz").child("Ongoing").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String QuizID = dataSnapshot.getValue().toString();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Grades").child("PerStudent").child(MainActivity.studentID);
                mDatabase.child(QuizID).setValue(Double.toString(QuizScore));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }
    void updateOverallScore(final double newQuizScore){
        final DatabaseReference gradesPerStudent =  FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Grades").child("PerStudent").child(MainActivity.studentID);
        gradesPerStudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
               long childrencount = dataSnapshot.getChildrenCount();
               final long quizCount = childrencount-2;
               System.out.println("QuizCount" + quizCount);
               gradesPerStudent.child("score").addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       double cumulatedgrades = Double.parseDouble(dataSnapshot.getValue().toString())*quizCount/100;
                       System.out.println("Cumulatedgrades"+cumulatedgrades);
                       System.out.println("newScore"+(cumulatedgrades+newQuizScore)*100/(quizCount+1));
                       double newOverallScore= (cumulatedgrades+newQuizScore)*100/(quizCount+1);
                       gradesPerStudent.child("score").setValue(newOverallScore);
                       if (newOverallScore<50){
                           gradesPerStudent.child("Overall Grade").setValue("F");
                       }
                       else if (newOverallScore<60){
                           gradesPerStudent.child("Overall Grade").setValue("E");
                       }
                       else if (newOverallScore<70){
                           gradesPerStudent.child("Overall Grade").setValue("D");

                       }
                       else if (newOverallScore<75){
                           gradesPerStudent.child("Overall Grade").setValue("C");

                       }
                       else if (newOverallScore<80){
                           gradesPerStudent.child("Overall Grade").setValue("B");

                       }
                       else {
                           gradesPerStudent.child("Overall Grade").setValue("A");
                       }

                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }

    protected JSONArray[] getJsonarray() {
        return jsonarray;
    }

    public String[] getOngoing() {
        return ongoing;
    }


}

