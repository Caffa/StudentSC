package com.example.caffae.studentsc.Class;

import android.provider.ContactsContract;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class BroadcastQuestionTiming {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String classroomID = "";
    private final Query query = mDatabase.child(classroomID).child("BroadcastQuestion");
    private final DatabaseReference broadcastnode = mDatabase.child(classroomID).child("BroadcastQuestion");
    final int[] ongoing  = new int[]{-1};
    final Long[] timing = new Long[]{(long) 0, (long) 0, (long) 0};

    public long getTiming(){
        return timing[2];
    }
    public int getOngoing(){
       return ongoing[0];
    }

    public void inputTiming() {
    }

    public void generateTiming() {
        broadcastnode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.getValue().equals("None")) {
                    long startTime = System.nanoTime();
                    timing[0] = startTime;
                }
                if (dataSnapshot.child("Ongoing").getValue().equals("0")) {
                    long timetaken = System.nanoTime() - timing[0];
                    timing[1] = System.nanoTime();
                    timing[2] = timetaken;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void checkOngoingQuestion() {
        broadcastnode.child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Ongoing").getValue().equals("None")) {
                    ongoing[0] = -1;
                }
                else ongoing[0] = (int)dataSnapshot.getValue();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void checkFastest() {
        broadcastnode.child("timing").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
