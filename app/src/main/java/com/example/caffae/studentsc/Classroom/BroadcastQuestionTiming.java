package com.example.caffae.studentsc.Classroom;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.caffae.studentsc.MainActivity;
import com.example.caffae.studentsc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BroadcastQuestionTiming {
    private String classroomID ;
    Context mContext;
    private DatabaseReference mDatabase;
    private final DatabaseReference broadcastnode;
    public BroadcastQuestionTiming(Context c){
        this.mContext = c;
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.classroomID), Context.MODE_PRIVATE);
        classroomID = sharedPref.getString(mContext.getString(R.string.classroomID),"Classroom1");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        broadcastnode = mDatabase.child(classroomID).child("BroadcastQuestion");
    }



    // Compares timing with current timing in database
    // Pushes key: studentID and value:timing to Fastest node if it is smaller than timing in database.
    public void pushFastestTiming(final long endTime) {
        broadcastnode.child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final long startTime = 0;
                if (dataSnapshot.exists()) {
                    final DatabaseReference questionnode = broadcastnode.child(dataSnapshot.getValue().toString()).child("0");
                    if (!dataSnapshot.getValue().equals("-1")) {
                        questionnode.child("Fastest").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                         @Override
                                                                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                             if (dataSnapshot.exists()) {
                                                                                                 if (Long.parseLong(dataSnapshot.getValue().toString().substring(9, dataSnapshot.getValue().toString().length() - 1)) > (endTime - startTime)) {
                                                                                                     questionnode.child("Fastest").child(MainActivity.studentID).setValue(endTime - startTime);
                                                                                                 }
                                                                                             } else {
                                                                                                 questionnode.child("Fastest").child(MainActivity.studentID).setValue(endTime - startTime);
                                                                                             }
                                                                                             System.out.println("newtiming" + (endTime - startTime));

                                                                                         }

                                                                                         @Override
                                                                                         public void onCancelled(DatabaseError databaseError) {

                                                                                         }
                                                                                     }
                        );

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}


