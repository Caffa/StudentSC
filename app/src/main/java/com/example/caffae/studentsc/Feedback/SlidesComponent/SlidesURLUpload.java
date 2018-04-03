package com.example.caffae.studentsc.Feedback.SlidesComponent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


/**
 * Created by dorette_ong on 6/3/2018.
 */

public class SlidesURLUpload {

    //URL to retrive slides
    private String url = "https://www.nobelprize.org/nobel_prizes/economic-sciences/laureates/2011/sargent-lecture_slides.pdf";

    // Load slides from URL into Webview
    public void loadSlides(WebView webView) {
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://docs.google.com/viewer?embedded=true&url=" + url);
    }
}
