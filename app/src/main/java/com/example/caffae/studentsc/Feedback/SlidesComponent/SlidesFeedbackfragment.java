package com.example.caffae.studentsc.Feedback.SlidesComponent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.caffae.studentsc.Classroom.AvailableClassroomFragment;
import com.example.caffae.studentsc.Classroom.BroadcastQuestionFragment;
import com.example.caffae.studentsc.R;
import java.util.ArrayList;

/**
 * Created by dorette_ong on 23/2/2018.
 */


public class SlidesFeedbackfragment extends Fragment {
    LinearLayout linearLayout;
    Button submitbutton;
    ArrayList<ArrayList<EditText>> overallFeedback = new ArrayList<>();

    public SlidesFeedbackfragment() {
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
        overallFeedback.add(addSlideFeedbackItems());
        overallFeedback.add(addSlideFeedbackItems());
        addListenerOnButton();

        return view;
    }

    //Inflates each feedback shortanweritem_quiz
    private ArrayList<EditText> addSlideFeedbackItems() {
        ArrayList<EditText> addedBox = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View slidefeedbackitem = inflater.inflate(R.layout.slides_feedback_item_layout, null);
        linearLayout.addView(slidefeedbackitem);
        EditText slideNumber = slidefeedbackitem.findViewById(R.id.editTextSlideNumber);
        EditText slideFeedback = slidefeedbackitem.findViewById(R.id.editTextSlideFeedback);
        addedBox.add(slideNumber);
        addedBox.add(slideFeedback);
        return addedBox;

    }
    //Add listener for submit button
    //Toasts "Feedback Submitted" and pushes feedback into database
    private void addListenerOnButton() {
        submitbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                for (ArrayList<EditText> feedbackitem : overallFeedback) {
                    System.out.println("SlideNumber" + feedbackitem.get(0).getText().toString());
                    System.out.println("SlideFeedback" + feedbackitem.get(1).getText().toString());

                    if (feedbackitem.get(0).getText().toString().equals("")|| feedbackitem.get(1).getText().toString().equals("")){}
                    else{
                        String slideNumber = feedbackitem.get(0).getText().toString();
                        String slideFeedback = feedbackitem.get(1).getText().toString();
                        DatabaseSlidesFeedback databaseSlidesFeedback = new DatabaseSlidesFeedback(getContext());
                        databaseSlidesFeedback.pushSlidesFeedback(slideNumber, slideFeedback);

                    }

                }
                getActivity().onBackPressed();

            }

        });

    }
}

