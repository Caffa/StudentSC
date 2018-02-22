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

import com.example.caffae.studentsc.R;
import com.example.caffae.studentsc.SubmitQuestionFragment;

import java.util.ArrayList;

public class BroadcastQuestionFragment extends Fragment {
    public BroadcastQuestionFragment() {
    }  ;
    Button submitButton;
    TextView question;
    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    RadioButton button4;
    BroadcastQuestionCheckAnswer newbroadcast;
    RadioGroup radiogroup;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        newbroadcast = new BroadcastQuestionCheckAnswer();

        //to be deleted later on
        ArrayList<String> questions = new ArrayList<>();
        questions.add("2+3=?");
        questions.add("3");
        questions.add("5");
        questions.add("6");
        questions.add("7");
        newbroadcast.setquestionanswer(questions);
        newbroadcast.setanswer(2);
        //

        setQuestion(view);
        setAnswers(view);
        return view;
    }

    public void setQuestion(View view) {
        question.setText(newbroadcast.getquestionanswer().get(0));
    }

    public void setAnswers(View view) {
        button1.setText(newbroadcast.getquestionanswer().get(1));
        button2.setText(newbroadcast.getquestionanswer().get(2));
        button3.setText(newbroadcast.getquestionanswer().get(3));
        button4.setText(newbroadcast.getquestionanswer().get(4));
    }

    public void addListenerOnButton(View view) {

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (radiogroup.getCheckedRadioButtonId() == radiogroup.getChildAt(newbroadcast.getanswer()-1).getId()){
                    Toast.makeText(getContext(), "Well Done!", Toast.LENGTH_SHORT).show();
                    changeFragment();
                }
                else if (radiogroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(), "Please Select Answer", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getContext(), "Try Again Next Time! \n The right answer was Option " + newbroadcast.getanswer(), Toast.LENGTH_SHORT).show();
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
