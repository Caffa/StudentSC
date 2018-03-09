package com.example.caffae.studentsc.Feedback.SlidesComponent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.example.caffae.studentsc.R;

public class SlidesFragment extends Fragment {
    private Button lectureButton;
    String url = "https://www.nobelprize.org/nobel_prizes/economic-sciences/laureates/2011/sargent-lecture_slides.pdf";
    public SlidesFragment() {
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slides, container, false);
        WebView webView = (WebView) view.findViewById(R.id.webview);
        SlidesURLUpload slidesURLUpload = new SlidesURLUpload();
        slidesURLUpload.loadslides(webView,url);
        return view;
    }

}
