package com.example.caffae.studentsc.Class;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.R;
import com.example.caffae.studentsc.StudentMainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BroadcastQuestionFragment extends Fragment {
    public BroadcastQuestionFragment() {
    }  ;
    Button submitButton;
    TextView question;
    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    RadioButton button4;
    BroadcastQuestionCheckAnswer newbroadcast = new BroadcastQuestionCheckAnswer();
    ;
    RadioGroup radiogroup;
    BroadcastQuestionTiming broadcastQuestionTiming = new BroadcastQuestionTiming();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String classroomID = ClassroomIDActivity.getClassroomID();
    private final DatabaseReference broadcastnode = mDatabase.child(classroomID).child("BroadcastQuestion");

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View noQuestionView =  inflater.inflate(R.layout.fragment_no_ongoing, container, false);
        if (StudentMainActivity.ongoingBroadcast.equals("-1")){
            return noQuestionView;
        }
        else {
            View view = inflater.inflate(R.layout.fragment_broadcast_question, container, false);
            //initialisation of the different components;
            submitButton = new Button(getContext());
            submitButton = view.findViewById(R.id.broadcastquestionsubmit);
            question = new TextView(getContext());
            question = view.findViewById(R.id.broadcastquestion);
            button1 = new RadioButton(getContext());
            button1 = view.findViewById(R.id.simpleRadioButton1);
            button2 = new RadioButton(getContext());
            button2 = view.findViewById(R.id.simpleRadioButton2);
            button3 = new RadioButton(getContext());
            button3 = view.findViewById(R.id.simpleRadioButton3);
            button4 = new RadioButton(getContext());
            button4 = view.findViewById(R.id.simpleRadioButton4);
            radiogroup = new RadioGroup(getContext());
            radiogroup = view.findViewById(R.id.broadcastradiogroup);

            addListenerOnButton(view);
            setQuestion(view);
            setAnswers(view);

            return view;
        }
    }

    public void setQuestion(View view) {
        newbroadcast.convertJSON(AvailableClassroomFragment.questionjsonArray.toString());
        question.setText(newbroadcast.getquestionanswer().get(0).getQuestion());
    }

    public void setAnswers(View view) {

        button1.setText(newbroadcast.getquestionanswer().get(0).getOption1());
        button2.setText(newbroadcast.getquestionanswer().get(0).getOption2());
        button3.setText(newbroadcast.getquestionanswer().get(0).getOption3());
        button4.setText(newbroadcast.getquestionanswer().get(0).getOption4());

    }

    public void addListenerOnButton(View view) {

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (radiogroup.getCheckedRadioButtonId() == radiogroup.getChildAt(Integer.parseInt(newbroadcast.getquestionanswer().get(0).getAnswer())-1).getId()){
                    Toast.makeText(getContext(), "Well Done!", Toast.LENGTH_SHORT).show();
                    changeFragment();
                    BroadcastQuestionTiming broadcastQuestionTiming = new BroadcastQuestionTiming();
                    broadcastQuestionTiming.pushFastestTiming(System.nanoTime());
                }
                else if (radiogroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(), "Please Select Answer", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getContext(), "Try Again Next Time! \n The right answer was Option " + newbroadcast.getquestionanswer().get(0).getAnswer(), Toast.LENGTH_SHORT).show();
                    changeFragment();
                }

            }

        });


    }
    public void changeFragment(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft =  manager.beginTransaction();
        ft.replace(R.id.broadcastquestioncontainer, new AvailableClassroomFragment()).commit();
        ft.addToBackStack(null);
    }
}
