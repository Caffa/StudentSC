package com.example.caffae.studentsc.Feedback.SlidesComponent;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.MainActivity;
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

    // Push slides feedback into database with key: slideNumber , value: feedback
    void pushSlidesFeedback(final String slideNumber, final String feedback){

        FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("CurrentLecture").addListenerForSingleValueEvent(new ValueEventListener() {
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
