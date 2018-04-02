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

<<<<<<< HEAD
import com.example.caffae.studentsc.Forum.StudentMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
=======
>>>>>>> master
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Caffae on 3/2/18.
 */

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://softwareconstruct-forum.firebaseio.com/Student%20List.json";
    private Map<String, String> idsList = new HashMap<String, String>();
    private DatabaseReference mDatabase;
    private boolean loginCorrect = false;
    String enteredid;
    String enteredpassword;

    @BindView(R.id.loginScreenID)
    EditText loginScreenID;
    @BindView(R.id.loginScreenPW)
    EditText loginScreenPW;
    @BindView(R.id.loginCardView)
    CardView loginCardView;
    public static String studentID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginCardView)
    public void onViewClicked() {

        //DONE: Check against set values
         enteredid = loginScreenID.getText().toString();
         enteredpassword = loginScreenPW.getText().toString();



        //DONE:Check against the Database
        fetchDatabaseInfo();
//        boolean shouldLogin;
//        shouldLogin = CheckIfIdCorrect(id,password);

//        if (idsList != null){
//             shouldLogin = CheckIfIdCorrect(id,password);
//        }else{
//            FancyToast.makeText(this,"Not connected to Internet",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
//             shouldLogin = false;
//        }
        //DONE: this is a substitute so remove later

        if (loginCorrect){

                //add studentId to counter for forumQadding
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.studentId), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.studentId), Integer.parseInt(enteredid));
            editor.commit();

                //DONE: toasts not working
                FancyToast.makeText(this,"Successful Login!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

<<<<<<< HEAD
                //DONE: go to next activity
                Intent intent = new Intent(this, StudentMainActivity.class);
=======
                //TODO: go to next activity
                Intent intent = new Intent(this, ClassroomIDActivity.class);
>>>>>>> master
                startActivity(intent);
                studentID = id;

        }else{


        //alternative
        //TODO: wrong 'pwd/id' toast -- it isn't working
        FancyToast.makeText(this,"Wrong Id or Password!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

    }}

//    private boolean CheckIfIdCorrect(String id, String password) {
//        fetchDatabaseInfo();
//        if (idsList == null){
//            System.out.println("IdList not inits");
//        }
//        boolean match = false;
//        System.out.println("Id is " + id + " pwd is "+ password);
//
//        for (Map.Entry<String, String> pair : idsList.entrySet()) {
//            System.out.println("Other V: Id is " + pair.getKey() + " pwd is "+ pair.getValue());
//
//        }
//
//        if(idsList.containsKey(id)){
//            idsList.get(id).equals(password);
//
//            match = true;
//            System.out.println("Correct");
//        }
//
//        return match;
//    }


    private void fetchDatabaseInfo(){

        if (idsList != null){
            idsList.clear();
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query mQueryRef = mDatabase.child("Student List");
        mQueryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+ dataSnapshot.getChildrenCount());
                for (DataSnapshot i: dataSnapshot.getChildren()){
                    idsList.put(i.getKey(), (String) i.getValue());
//                    System.out.println("Key " + i.getKey() + " Value " + i.getValue(String.class));
                }

                for (Map.Entry<String, String> pair : idsList.entrySet()) {
//                    System.out.println("Verify! Id is " + pair.getKey() + " pwd is "+ pair.getValue());
                    if(pair.getKey().equals(enteredid) && pair.getValue().equals(enteredpassword)){
                        loginCorrect = true;
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " , databaseError.getMessage());
            }
        });




//        Map<String, String> idsList = (Map<String, String>) dataSnapshot.getValue();
//        Log.i("dataSnapshot", "dataSnapshot" + new JSONObject(idsList));
    }




//    private void fetchDatabaseInfo() {
//        JsonArrayRequest request = new JsonArrayRequest(URL,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if (response == null) {
//                            Toast.makeText(getApplicationContext(), "Couldn't fetch the StudentIds! Pleas try again.", Toast.LENGTH_LONG).show();
//                            return;
//                        }
//
//                        JSONObject object = new JSONObject(result);
//                        JSONArray Jarray  = object.getJSONArray("contacts");
//
//                        for (int i = 0; i < Jarray.length(); i++)
//                        {
//                            JSONObject Jasonobject = Jarray.getJSONObject(i);
//                        }
//
//                        //DEbug
//
//                        Map<String, String> items = new Gson().fromJson(response.toString(), new TypeToken<Map<String, String>>() {
//                        }.getType());
//
////                        Map<String, String> items = new Gson().fromJson(response.toString(), new TypeToken<Map>())
//
//
//
////                        System.out.println(items);
//
////                        idsList.clear();
//                        idsList = items;
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // error in getting json
//                Log.e("Fetch S-Ids - Database", "Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        MyApplication.getInstance().addToRequestQueue(request);
//
//    }



}
