package com.example.caffae.studentsc.Feedback;

import android.os.Handler;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import javax.security.auth.callback.Callback;

/**
 * Created by dorette_ong on 29/3/2018.
 */

public class DatabaseRatingLecturer {
    private DatabaseReference mDatabase;


    public void pushLecturerRating(final String criteria, final float rating){

        FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("CurrentLecture").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String lectureID= dataSnapshot.getValue().toString();
                FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Lecturer").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String lecturerID = dataSnapshot.getValue().toString();
                        mDatabase = FirebaseDatabase.getInstance().getReference().child(ClassroomIDActivity.getClassroomID()).child("Rating").child(lectureID).child(lecturerID);
                        mDatabase.child(criteria).setValue(rating);
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

}

