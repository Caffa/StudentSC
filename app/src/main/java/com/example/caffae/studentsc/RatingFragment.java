package com.example.caffae.studentsc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


public class RatingFragment extends Fragment {
    private RatingBar ratingBar;
    private RatingBar ratingBarexplanation;
    private Button btnSubmit;

    public RatingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        ratingBar = new RatingBar(getContext());
        ratingBarexplanation = new RatingBar(getContext());
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBarexplanation = (RatingBar) view.findViewById(R.id.explanationratingbar);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        addListenerOnRatingBar(view);
        addListenerOnButton(view);
        return view ;
    }

    public void addListenerOnRatingBar(View view) {

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
            }
        });
        ratingBarexplanation.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBarexplanation, float rating,
                                        boolean fromUser) {
            }
        });
    }

    public void addListenerOnButton(View view) {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clarity: "+ String.valueOf(ratingBar.getRating()) + " Explanation: " + String.valueOf(ratingBarexplanation.getRating()), Toast.LENGTH_SHORT).show();
            }

        });

    }
}
