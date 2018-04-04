package com.example.caffae.studentsc.Classroom;


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
import com.example.caffae.studentsc.StudentMainActivity;


public class BroadcastQuestionFragment extends Fragment {
    public BroadcastQuestionFragment() {
    }

    private Button submitButton;
    private TextView question;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private QuizAndBroadcastMain newbroadcast = new QuizAndBroadcastMain();
    private RadioGroup radiogroup;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // If no ongoing broadcast, inflate and return shortansweritem_quiz for no ongoing broadcast
        if (StudentMainActivity.ongoingBroadcast.equals("-1")) {
            return inflater.inflate(R.layout.fragment_no_ongoing, container, false);
        }
        // Else if there is an ongoing broadcast, inflate shortansweritem_quiz with broadcast question
        else {
            View view = inflater.inflate(R.layout.fragment_broadcast_question, container, false);
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

            addListenerOnButton();
            setQuestion();
            setAnswers();

            return view;
        }
    }

    //Set text for question
    private void setQuestion() {
        newbroadcast.convertJSON(AvailableClassroomFragment.questionjsonArray.toString());
        question.setText(newbroadcast.getquestionanswer().get(0).getQuestion());
    }

    //Set text for options
    private void setAnswers() {
        button1.setText(newbroadcast.getquestionanswer().get(0).getOption1());
        button2.setText(newbroadcast.getquestionanswer().get(0).getOption2());
        button3.setText(newbroadcast.getquestionanswer().get(0).getOption3());
        button4.setText(newbroadcast.getquestionanswer().get(0).getOption4());
    }

    //Submit button toasts whether answer is correct and pushes timing to database if it is correct and the fastest
    //Ensures options are selected before submitting.
    private void addListenerOnButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (radiogroup.getCheckedRadioButtonId() == radiogroup.getChildAt(Integer.parseInt(newbroadcast.getquestionanswer().get(0).getAnswer()) - 1).getId()) {
                    Toast.makeText(getContext(), "Well Done!", Toast.LENGTH_SHORT).show();
                    changeFragment();
                    BroadcastQuestionTiming broadcastQuestionTiming = new BroadcastQuestionTiming(getContext());
                    broadcastQuestionTiming.pushFastestTiming(System.nanoTime());
                } else if (radiogroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(), "Please Select Answer", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Try Again Next Time! \n The right answer was Option " + newbroadcast.getquestionanswer().get(0).getAnswer(), Toast.LENGTH_SHORT).show();
                    changeFragment();
                }
            }

        });
    }

    // Swaps back to the AvailableClassroomFragment
    private void changeFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.broadcastquestioncontainer, new AvailableClassroomFragment()).commit();
        ft.addToBackStack(null);
    }
}
