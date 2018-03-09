package com.example.caffae.studentsc.Class;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.caffae.studentsc.Forum.MyApplication;

import org.json.JSONArray;

/**
 * Created by dorette_ong on 5/3/2018.
 */

public class DatabaseJSON {
    JSONArray[] jsonarray = new JSONArray[1];
    public void fetchDatabaseInfo(String URL) {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jsonarray[0] = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }
}