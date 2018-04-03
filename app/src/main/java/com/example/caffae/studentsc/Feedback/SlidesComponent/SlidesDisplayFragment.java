package com.example.caffae.studentsc.Feedback.SlidesComponent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.example.caffae.studentsc.R;

/**
 * Created by dorette_ong on 20/3/2018.
 */


public class SlidesDisplayFragment extends Fragment {

    public SlidesDisplayFragment() {}
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slides, container, false);
        WebView webView = view.findViewById(R.id.webview);
        SlidesURLUpload slidesURLUpload = new SlidesURLUpload();
        slidesURLUpload.loadSlides(webView);
        return view;
    }

}
