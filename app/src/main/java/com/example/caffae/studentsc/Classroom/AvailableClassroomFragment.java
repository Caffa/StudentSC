package com.example.caffae.studentsc.Classroom;

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


public class AvailableClassroomFragment extends Fragment {
    private Button quizButton;
    private Button questionButton;
    private DatabaseClassroom quizDatabaseClassroom;
    private DatabaseClassroom questionDatabaseClassroom;
    protected static JSONArray quizjsonArray = new JSONArray();
    protected static JSONArray questionjsonArray = new JSONArray();


    public AvailableClassroomFragment() {
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
        quizDatabaseClassroom = new DatabaseClassroom(getContext());
        questionDatabaseClassroom = new DatabaseClassroom(getContext());
        quizDatabaseClassroom.fetchQuizInfo(StudentMainActivity.ongoingQuiz);
        questionDatabaseClassroom.fetchBroadCastInfo(StudentMainActivity.ongoingBroadcast);
        addListenerOnButton(view);

        return view;
    }

    //addListener for quiz and question button
    private void addListenerOnButton(View view) {

        //Replaces current fragment with BroadcastQuestionFragment and pulls jsonarray for broadcast question
        questionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.availableclassroomcontainer, new BroadcastQuestionFragment()).commit();
                ft.addToBackStack(null);
                questionjsonArray = questionDatabaseClassroom.getJsonarray()[0];
            }

        });

        //Replaces current fragment with QuizFragment and pulls jsonarray for quiz
        quizButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.availableclassroomcontainer, new QuizFragment()).commit();
                ft.addToBackStack(null);
                quizjsonArray = quizDatabaseClassroom.getJsonarray()[0];
            }

        });


    }
}
