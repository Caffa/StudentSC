package com.example.caffae.studentsc.Feedback.SlidesComponent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caffae.studentsc.R;

/**
 * Created by dorette_ong on 5/3/2018.
 */


public class SlidesMainFragment extends Fragment {
    //Main fragment that holds the SlideFeedbackFragment and SlidesDisplayFragment side by side
    public SlidesMainFragment() {}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slides_main, container, false);
    }
}