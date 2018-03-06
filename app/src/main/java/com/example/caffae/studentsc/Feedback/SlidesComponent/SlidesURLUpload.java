package com.example.caffae.studentsc.Feedback.SlidesComponent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


/**
 * Created by dorette_ong on 6/3/2018.
 */

public class SlidesURLUpload {
    public void loadslides(WebView webView, String url) {
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("https://docs.google.com/viewer?embedded=true&url=" + url);
    }
}
