package com.example.caffae.studentsc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassroomMainActivity extends AppCompatActivity {
    public static String classroomID = "Classroom1";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDatabaseClassroomcount();
        getClassnames();
        setContentView(R.layout.activity_classroom_main);
        getDatabaseClassroomcount();
        getClassnames();
        final Context mContext = this;

        final GridView gridview = findViewById(R.id.gridview);
        ClassAdapter classAdapter = null;
        try {
            classAdapter = new ClassAdapter(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gridview.setAdapter(classAdapter);
    }


    public void getDatabaseClassroomcount(){
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.getChildrenCount()-2;
                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.classroomcount), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.classroomcount),count);
                editor.apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getClassnames(){
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> classes = new ArrayList<>();
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    System.out.println("DSP HERE " + dsp.toString());
                    String classname = dsp.getKey();
                    if (!classname.equals("Instructor List")&& !classname.equals("Student List")){
                        classes.add(classname);
                    }
                }
                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.classnames), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.classnames),classes.toString());
                System.out.println("CLASSES TO STRING" + classes.toString());
                editor.apply();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
