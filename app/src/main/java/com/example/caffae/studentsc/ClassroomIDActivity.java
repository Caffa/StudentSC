package com.example.caffae.studentsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ClassroomIDActivity extends AppCompatActivity {
    Button buttonEnterClassroom;
    static String classroomID;
    EditText editTextclassroomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_id);
        buttonEnterClassroom = new Button(this);
        editTextclassroomID = new EditText(this);
        buttonEnterClassroom = findViewById(R.id.buttonEnterClassroom);
        editTextclassroomID = findViewById(R.id.editTextClassroomID);
        addButton();
    }

    public void setClassroomID(){
        classroomID = "Classroom"+editTextclassroomID.getText().toString();
        System.out.println("ClassroomIDEditText: " + classroomID);
    }


    public static String getClassroomID(){
        return classroomID;
    }

    public void checkClassroomExists(String classroom,final View view){
        ValueEventListener responseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Intent intent = new Intent(view.getContext(), StudentMainActivity.class);
                    startActivity(intent);
                } else {
                    FancyToast.makeText(view.getContext(),"No Classroom with this ID!",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        FirebaseDatabase.getInstance().getReference().child(classroom).addValueEventListener(responseListener);
    }

    public void addButton(){
        buttonEnterClassroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClassroomID();
                checkClassroomExists(classroomID,view);
            }
        });
    }

}
