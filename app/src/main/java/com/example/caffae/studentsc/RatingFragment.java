package com.example.caffae.studentsc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class RatingFragment extends Fragment {
    private RatingBar ratingBar;
    private TextView txtRatingValue;
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
        txtRatingValue = new TextView(getContext());
        addListenerOnRatingBar(view);
        addListenerOnButton(view);
        return view ;
    }

    public void addListenerOnRatingBar(View view) {

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) view.findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void addListenerOnButton(View view) {

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();

            }

        });

    }
}
