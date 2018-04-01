package com.example.caffae.studentsc.Class;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.MainActivity;
import com.example.caffae.studentsc.StudentMainActivity;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;


public class BroadcastQuestionTiming {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String classroomID = ClassroomIDActivity.getClassroomID();
    private final DatabaseReference broadcastnode = mDatabase.child(classroomID).child("BroadcastQuestion");


    public void pushFastestTiming(final long endTime) {
        broadcastnode.child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final long startTime = 0;
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void checkOngoing() {
        final boolean[] ongoing = new boolean[1];
        broadcastnode.child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().toString().equals("-1")){
                    ongoing[0]=false;
                }
                else ongoing[0] = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}


