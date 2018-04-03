package com.example.caffae.studentsc.Classroom;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


class BroadcastQuestionTiming {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String classroomID = ClassroomIDActivity.getClassroomID();
    private final DatabaseReference broadcastnode = mDatabase.child(classroomID).child("BroadcastQuestion");

    // Compares timing with current timing in database
    // Pushes key: studentID and value:timing to Fastest node if it is smaller than timing in database.
    void pushFastestTiming(final long endTime) {
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


