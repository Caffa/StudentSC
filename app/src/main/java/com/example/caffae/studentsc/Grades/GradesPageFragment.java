package com.example.caffae.studentsc.Grades;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caffae.studentsc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class GradesPageFragment extends Fragment {
    private DatabaseReference mDatabase;
    Integer studentId;
    String overallGrade;
    int score;


    private Unbinder unbinder;
    @BindView(R.id.studentIDDisplay)
    TextView studentIDDisplay;

    @BindView(R.id.gradeDisplay)
    TextView gradeDisplay;

    @BindView(R.id.scoreDisplay)
    TextView scoreDisplay;


    public GradesPageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grades_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        //student id
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.studentId), Context.MODE_PRIVATE);
        studentId = sharedPref.getInt(getString(R.string.studentId), 1000030);
        studentIDDisplay.setText(String.valueOf(studentId));

        //Firebase fetch for this student id
        fetchDatabaseInfo();
//        gradeDisplay.setText(String.valueOf(overallGrade));
//        scoreDisplay.setText(String.valueOf(score));

        return view;
    }

    private void fetchDatabaseInfo(){


        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query mQueryRef = mDatabase.child("Classroom1").child("Grades").child("PerStudent");
        mQueryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+ dataSnapshot.getChildrenCount());
                for (DataSnapshot i: dataSnapshot.getChildren()){
                        System.out.println("Key " + i.getKey());
                        String currentKey = i.getKey().toString();

                        if(Integer.parseInt(currentKey) == studentId){
                            overallGrade = i.child("Overall Grade").getValue().toString();
                            System.out.println("Overall Grade "+ overallGrade);
                            score = Integer.parseInt(i.child("score").getValue().toString());
                            System.out.println("Overall Score "+ score);


                            gradeDisplay.setText(String.valueOf(overallGrade));
                            scoreDisplay.setText(String.valueOf(score));
                        }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " , databaseError.getMessage());
            }
        });

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
