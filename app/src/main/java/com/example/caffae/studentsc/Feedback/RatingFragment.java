package com.example.caffae.studentsc.Feedback;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RatingFragment extends Fragment {
    private RatingBar ratingBar;
    private RatingBar ratingBarexplanation;
    private RatingBar ratingBarRelevantContent;
    private Button btnSubmit;
    private DatabaseRatingLecturer databaseRatingLecturer = new DatabaseRatingLecturer();


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
        ratingBarRelevantContent = new RatingBar(getContext());
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBarexplanation = (RatingBar) view.findViewById(R.id.explanationratingbar);
        ratingBarRelevantContent = view.findViewById(R.id.relevantcontentratingbar);
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
        ratingBarRelevantContent.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBarexplanation, float rating,
                                        boolean fromUser) {
            }
        });
    }

    public void addListenerOnButton(View view) {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                DatabaseRatingLecturer databaseRatingLecturer = new DatabaseRatingLecturer();

                databaseRatingLecturer.pushLecturerRating("Clarity",ratingBar.getRating());
                databaseRatingLecturer.pushLecturerRating("Meaningful",ratingBarexplanation.getRating());
                databaseRatingLecturer.pushLecturerRating("Relevant",ratingBarRelevantContent.getRating());

                // Toast.makeText(getContext(), "Clarity: "+ String.valueOf(ratingBar.getRating()) + " Explanation: " + String.valueOf(ratingBarexplanation.getRating()), Toast.LENGTH_SHORT).show();
            }

        });

    }
}
