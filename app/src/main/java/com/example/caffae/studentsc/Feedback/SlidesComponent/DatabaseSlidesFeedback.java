package com.example.caffae.studentsc.Feedback.SlidesComponent;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.MainActivity;
import com.example.caffae.studentsc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by dorette_ong on 31/3/2018.
 */

class DatabaseSlidesFeedback {
    private DatabaseReference mDatabase;
    Context mContext;
    public DatabaseSlidesFeedback(Context c){
        this.mContext = c;
    }

    SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.classroomID), Context.MODE_PRIVATE);
    private String classroomID = sharedPref.getString(mContext.getString(R.string.classroomID),"Classroom1");

    // Push slides feedback into database with key: slideNumber , value: feedback
    void pushSlidesFeedback(final String slideNumber, final String feedback){

        FirebaseDatabase.getInstance().getReference().child(classroomID).child("CurrentLecture").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String lectureID= dataSnapshot.getValue().toString();
                mDatabase = FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Rating").child(lectureID).child("Slides").child("Feedback").child(MainActivity.studentID);
                mDatabase.child(slideNumber).setValue(feedback);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
