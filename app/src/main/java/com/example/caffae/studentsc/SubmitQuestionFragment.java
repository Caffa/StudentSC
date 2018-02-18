package com.example.caffae.studentsc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


public class SubmitQuestionFragment extends Fragment {
    private Button btnSubmit;
    private EditText edittextsubmitquestion;
    public SubmitQuestionFragment() {
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_question, container, false);
        btnSubmit = new Button(getContext());
        btnSubmit = view.findViewById(R.id.buttonsubmitquestion);
        edittextsubmitquestion = new EditText(getContext());
        edittextsubmitquestion = view.findViewById(R.id.edittextsubmitquestion);

        addListenerOnButton(view);
        return view;
    }
    public void addListenerOnButton(View view) {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Question Submitted", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
