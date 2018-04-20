package com.example.caffae.studentsc.Feedback.RatingLecturer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.caffae.studentsc.Classroom.AvailableClassroomFragment;
import com.example.caffae.studentsc.Classroom.BroadcastQuestionFragment;
import com.example.caffae.studentsc.R;

/**
 * Created by dorette_ong on 28/3/2018.
 */


public class RatingFragment extends Fragment {
    private RatingBar ratingBar1;
    private RatingBar ratingBar2;
    private RatingBar ratingBar3;
    private Button btnSubmit;

    public RatingFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        ratingBar1 = new RatingBar(getContext());
        ratingBar2 = new RatingBar(getContext());
        ratingBar3 = new RatingBar(getContext());
        ratingBar1 =  view.findViewById(R.id.ratingBar);
        ratingBar2 =  view.findViewById(R.id.explanationratingbar);
        ratingBar3 = view.findViewById(R.id.relevantcontentratingbar);
        btnSubmit =  view.findViewById(R.id.btnSubmit);

        addListenerOnRatingBar();
        addListenerOnButton();
        return view ;
    }
    //Add listener for rating bar
    private void addListenerOnRatingBar() {

        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
            }
        });
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBarexplanation, float rating,
                                        boolean fromUser) {
            }
        });
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBarexplanation, float rating,
                                        boolean fromUser) {
            }
        });
    }
    //Add listener for submit button
    private void addListenerOnButton() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                DatabaseRatingLecturer databaseRatingLecturer = new DatabaseRatingLecturer(getContext());

                databaseRatingLecturer.pushLecturerRating("Clarity", ratingBar1.getRating());
                databaseRatingLecturer.pushLecturerRating("Meaningful", ratingBar2.getRating());
                databaseRatingLecturer.pushLecturerRating("Relevant", ratingBar3.getRating());
                getActivity().onBackPressed();
            }

        });

    }
}
