package com.example.caffae.studentsc.Class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.caffae.studentsc.R;

import org.json.JSONArray;


public class AvailableClassroomFragment extends Fragment {
    Button quizButton;
    Button questionButton;
    DatabaseJSON quizDatabaseJSON = new DatabaseJSON();
    DatabaseJSON questionDatabaseJSON = new DatabaseJSON();
    static JSONArray quizjsonArray = new JSONArray();
    static JSONArray questionjsonArray = new JSONArray();



    public AvailableClassroomFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_classroom, container, false);

        String QuizURL = "https://escproject-4871b.firebaseio.com/Quiz.json";
        String BroadcastURL = "https://escproject-4871b.firebaseio.com/BroadcastQuestion.json";
        quizButton = new Button(getContext());
        quizButton = view.findViewById(R.id.quizButton);
        questionButton = new Button(getContext());
        questionButton = view.findViewById(R.id.questionButton);
        quizDatabaseJSON.fetchDatabaseInfo(QuizURL);
        questionDatabaseJSON.fetchDatabaseInfo(BroadcastURL);
        addListenerOnButton(view);
        return view;
    }

    public void addListenerOnButton(View view) {

        questionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft =  manager.beginTransaction();
                ft.replace(R.id.availableclassroomcontainer, new BroadcastQuestionFragment()).commit();
                ft.addToBackStack(null);
                System.out.println(questionDatabaseJSON.jsonarray[0]);
                questionjsonArray = questionDatabaseJSON.jsonarray[0];


            }

        });

        quizButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft =  manager.beginTransaction();
                ft.replace(R.id.availableclassroomcontainer, new QuizFragment()).commit();
                ft.addToBackStack(null);
                System.out.println(quizDatabaseJSON.jsonarray[0]);
                quizjsonArray = quizDatabaseJSON.jsonarray[0];

            }

        });


    }
}
