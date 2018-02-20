package com.example.caffae.studentsc.Feedback;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.caffae.studentsc.R;

public class FeedbackPageFragment extends Fragment {
    private Button lectureButton;
    public FeedbackPageFragment() {
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback_page, container, false);
        lectureButton = new Button(getContext());
        lectureButton = view.findViewById(R.id.lectureButton);
        addListenerOnButton(view);
        return view;
    }
    public void addListenerOnButton(View view) {

        lectureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft =  manager.beginTransaction();
                ft.replace(R.id.feedbackpagecontainer, new RatingFragment()).commit();
                ft.addToBackStack(null);
            }
        });

    }
}
