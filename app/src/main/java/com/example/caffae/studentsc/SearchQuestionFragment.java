package com.example.caffae.studentsc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class SearchQuestionFragment extends Fragment {
    private Button btnAskNewQuestion;
    public SearchQuestionFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_question, container, false);
        btnAskNewQuestion = new Button(getContext());
        btnAskNewQuestion = view.findViewById(R.id.btnasknewquestion);
        return view;
    }
    public void addListenerOnButton(View view) {
        btnAskNewQuestion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft =  manager.beginTransaction();
                ft.replace(R.id.feedbackpagecontainer, new SubmitQuestionFragment()).commit();
                ft.addToBackStack(null);

            }

        });


    }
}