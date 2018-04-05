package com.example.caffae.studentsc.Feedback.SlidesComponent;
import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.caffae.studentsc.ClassroomIDActivity;
import com.example.caffae.studentsc.R;
import com.example.caffae.studentsc.StudentMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.app.PendingIntent.getActivity;


/**
 * Created by dorette_ong on 6/3/2018.
 */

public class SlidesURLUpload {
    Context mContext;
    private String classroomID;
    public SlidesURLUpload(Context context){
        this.mContext = context;
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.classroomID), Context.MODE_PRIVATE);
        classroomID = sharedPref.getString(mContext.getString(R.string.classroomID),"Classroom1");

    }

    //URL to retrive slides
    //private String url = "https://www.nobelprize.org/nobel_prizes/economic-sciences/laureates/2011/sargent-lecture_slides.pdf";

    // Load slides from URL into Webview


    public void loadSlides(final WebView webView) {
        DatabaseReference slideURL = FirebaseDatabase.getInstance().getReference().child(classroomID).child("Rating").child("0").child("Slides").child("SlidesURL");
        slideURL.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://docs.google.com/viewer?embedded=true&url=" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
