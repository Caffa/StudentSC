package com.example.caffae.studentsc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.caffae.studentsc.Forum.MyApplication;
import com.example.caffae.studentsc.Forum.StudentMainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Caffae on 3/2/18.
 */

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://softwareconstruct-forum.firebaseio.com/Student%20List.json";
    private Map<String, String> idsList;

    @BindView(R.id.loginScreenID)
    EditText loginScreenID;
    @BindView(R.id.loginScreenPW)
    EditText loginScreenPW;
    @BindView(R.id.loginCardView)
    CardView loginCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginCardView)
    public void onViewClicked() {

        //DONE: Check against set values
        String id = loginScreenID.getText().toString();
        String password = loginScreenPW.getText().toString();

        //DONE:Check against the Database
        boolean shouldLogin = CheckIfIdCorrect(id,password);

        //DONE: this is a substitute so remove later

        if (shouldLogin){

                //add studentId to counter for forumQadding
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.studentId), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.studentId), Integer.parseInt(id));
            editor.commit();

                //DONE: toasts not working
                FancyToast.makeText(this,"Successful Login!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                //DONE: go to next activity
                Intent intent = new Intent(this, StudentMainActivity.class);
                startActivity(intent);

        }else{


        //alternative
        //TODO: wrong 'pwd/id' toast -- it isn't working
        FancyToast.makeText(this,"Wrong Id or Password!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

    }}

    private boolean CheckIfIdCorrect(String id, String password) {
        fetchDatabaseInfo();
        boolean match = false;
        if(idsList.get(id).equals(password)){
            match = true;
        }

        return match;
    }







    private void fetchDatabaseInfo() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the StudentIds! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }



                        Map<String, String> items = new Gson().fromJson(response.toString(), new TypeToken<Map<String, String>>() {
                        }.getType());

                        idsList.clear();
                        idsList = items;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e("Fetch S-Ids - Database", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);

    }



}
