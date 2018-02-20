package com.example.caffae.studentsc.Class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.caffae.studentsc.R;

public class BroadcastQuestionFragment extends Fragment {
    public BroadcastQuestionFragment(){};
    Button submitButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_broadcast_question, container, false);
        submitButton = new Button(getContext());
        submitButton = view.findViewById(R.id.broadcastquestionsubmit);
        addListenerOnButton(view);
        return view;}

    public void addListenerOnButton(View view) {
       submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Question Submitted", Toast.LENGTH_SHORT).show();
            }

        });


    }
}
