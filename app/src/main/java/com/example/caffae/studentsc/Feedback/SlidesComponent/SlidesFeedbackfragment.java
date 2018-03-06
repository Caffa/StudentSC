package com.example.caffae.studentsc.Feedback.SlidesComponent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.caffae.studentsc.R;

public class SlidesFeedbackfragment extends Fragment {
    LinearLayout linearLayout;
    Button submitbutton;

    public SlidesFeedbackfragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slides_feedbackfragment, container, false);
        linearLayout = new LinearLayout(getContext());
        linearLayout = view.findViewById(R.id.linearlayoutslidesfeedback);
        submitbutton = new Button(getContext());
        submitbutton = view.findViewById(R.id.slidesfeedbacksubmitbutton);
        addSlideFeedbackItems();
        addSlideFeedbackItems();
        addListenerOnButton(view);

        return view;
    }

    public void addSlideFeedbackItems() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slidefeedbackitem = inflater.inflate(R.layout.slides_feedback_item_layout, null);
        linearLayout.addView(slidefeedbackitem);
    }
    public void addListenerOnButton(View view) {

        submitbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                // Toast.makeText(getContext(), "Clarity: "+ String.valueOf(ratingBar.getRating()) + " Explanation: " + String.valueOf(ratingBarexplanation.getRating()), Toast.LENGTH_SHORT).show();
            }

        });

    }
}

