package com.example.caffae.studentsc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.Arrays;

/**
 * Created by dorette_ong on 4/4/2018.
 */

public class ClassAdapter extends BaseAdapter {
    private Context mContext;

    public ClassAdapter(Context c) throws InterruptedException {
        mContext = c;
    }



    public int getCount() {
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.classroomcount), Context.MODE_PRIVATE);
        int classroomcount = sharedPref.getInt(mContext.getString(R.string.classroomcount),0);
        return classroomcount;

    }



    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public String[] getSharedPreferenceClassNames(){
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.classnames), Context.MODE_PRIVATE);
        String classname = sharedPref.getString(mContext.getString(R.string.classnames),"Classroom1");
        String[] classes = classname.substring(1,classname.length()-1).split(",");
        System.out.println("CLASSESHERE" +Arrays.toString(classes));
        return classes;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {

        Button button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
           button = new Button(mContext);
           button.setLayoutParams(new ViewGroup.LayoutParams(800, 800));
           button.setPadding(8, 8, 8, 8);
           button.setText(getSharedPreferenceClassNames()[position]);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   setClassroomID(getSharedPreferenceClassNames()[position]);
                   Intent intent = new Intent(mContext, StudentMainActivity.class);
                   mContext.startActivity(intent);
               }
           });

        } else {
            button = (Button) convertView;
        }
        return button;
    }
    public void setClassroomID(String classroom){
        String classroomID = classroom.trim();
        System.out.println("ClassroomID: " + classroomID);

        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.classroomID), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(mContext.getString(R.string.classroomID), classroomID);
        editor.commit();

    }

}

