package com.example.caffae.studentsc.Feedback.RatingLecturer;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.caffae.studentsc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by dorette_ong on 29/3/2018.
 */

public class DatabaseRatingLecturer {
    private DatabaseReference mDatabase;
    Context mContext;
    private String classroomID;
    public DatabaseRatingLecturer(Context c){
        this.mContext = c;
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.classroomID), Context.MODE_PRIVATE);
        classroomID = sharedPref.getString(mContext.getString(R.string.classroomID),"Classroom1");
    }


    //Push lecturer rating to database with main node: LecturerID, key: criteria, value: rating
    public void pushLecturerRating(final String criteria, final float rating){

        FirebaseDatabase.getInstance().getReference().child(classroomID).child("CurrentLecture").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String lectureID= dataSnapshot.getValue().toString();
                FirebaseDatabase.getInstance().getReference().child(classroomID).child("Lecturer").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String lecturerID = dataSnapshot.getValue().toString();
                        mDatabase = FirebaseDatabase.getInstance().getReference().child(classroomID).child("Rating").child(lectureID).child(lecturerID);
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

