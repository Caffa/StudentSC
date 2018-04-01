package com.example.caffae.studentsc.Class;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.caffae.studentsc.R;
import com.example.caffae.studentsc.StudentMainActivity;

import org.json.JSONArray;

import static java.lang.Thread.sleep;


public class AvailableClassroomFragment extends Fragment {
    Button quizButton;
    Button questionButton;
    DatabaseClassroom quizDatabaseClassroom = new DatabaseClassroom();
    DatabaseClassroom questionDatabaseClassroom = new DatabaseClassroom();
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
        quizButton = new Button(getContext());
        quizButton = view.findViewById(R.id.quizButton);
        questionButton = new Button(getContext());
        questionButton = view.findViewById(R.id.questionButton);
        quizDatabaseClassroom.fetchQuizInfo(StudentMainActivity.ongoingQuiz);
        questionDatabaseClassroom.fetchBroadCastInfo(StudentMainActivity.ongoingBroadcast);
        addListenerOnButton(view);
        return view;
    }


    public void addListenerOnButton(View view) {

        questionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.availableclassroomcontainer, new BroadcastQuestionFragment()).commit();
                ft.addToBackStack(null);
                questionjsonArray = questionDatabaseClassroom.jsonarray[0];
                System.out.println(questionjsonArray);
            }

        });

        quizButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.availableclassroomcontainer, new QuizFragment()).commit();
                ft.addToBackStack(null);
                System.out.println(quizDatabaseClassroom.jsonarray[0]);
                quizjsonArray = quizDatabaseClassroom.jsonarray[0];
            }

        });


    }
}
