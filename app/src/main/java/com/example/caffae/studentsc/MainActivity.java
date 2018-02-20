package com.example.caffae.studentsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Caffae on 3/2/18.
 */

public class MainActivity extends AppCompatActivity {


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

        //TODO:Check against the Database

        //TODO: this is a substitute so remove later
        if (id.equals("")){
            if(password.equals("")){
                //TODO: toasts not working
                FancyToast.makeText(this,"Successful Login!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                //TODO: go to next activity
                Intent intent = new Intent(this, StudentMainActivity.class);
                startActivity(intent);

            }
        }


        //alternative
        //TODO: wrong 'pwd/id' toast -- it isn't working
        FancyToast.makeText(this,"Wrong Id/Password !",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

    }
}
