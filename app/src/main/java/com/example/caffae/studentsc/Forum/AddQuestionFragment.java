package com.example.caffae.studentsc.Forum;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caffae.studentsc.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class AddQuestionFragment extends Fragment {
    private Button btnSubmit;
    private EditText edittextsubmitquestion;
    private DatabaseReference mDatabase;
    public AddQuestionFragment() {
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_question, container, false);
        btnSubmit = new Button(getContext());
        btnSubmit = view.findViewById(R.id.buttonsubmitquestion);
        edittextsubmitquestion = (EditText) view.findViewById(R.id.QuestionTxtSubmit);
//        edittextsubmitquestion = new EditText(getContext());
//        edittextsubmitquestion = view.findViewById(R.id.QuestionTxtSubmit);

        addListenerOnButton(view);
        return view;
    }
    public void addListenerOnButton(View view) {
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String questionTxt = edittextsubmitquestion.getText().toString().trim();
                if(questionTxt.equals("")){
                    Toast.makeText(getContext(), "Question is Blank", Toast.LENGTH_SHORT).show();
                }else{

                    mDatabase = FirebaseDatabase.getInstance().getReference();
//                    DatabaseReference ref_carid = mDatabase.child("0").child("question");
//                    final int[] counter = new int[1];
//
//
//                    ref_carid.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            String sc = (String) dataSnapshot.getValue(String.class);
//                            counter[0] = Integer.parseInt(sc);
//                            Log.e("SC", "Error: " + sc);
//                            Toast.makeText(getContext(), sc, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//                    int counter = 30;
//                    int counter = Integer.parseInt(readFromFile(getContext()));
//                    int mergeCounterStudentId = Integer.parseInt(counter)

                    //for unique id of forum Q
                    String countTxt = readFromFile(getContext());
                    SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.studentId), Context.MODE_PRIVATE);
                    int studentId = sharedPref.getInt(getString(R.string.studentId), 1000030);
                    String myStudenId = String.valueOf(studentId - 1000000);
                    int counter = Integer.parseInt(myStudenId + countTxt);

//                    Toast.makeText(getContext(), lastQuery.toString(), Toast.LENGTH_SHORT).show();
//                    String uniqueNode = mDatabase.push().getKey();

                    //Add the numberID for the student into this so can make it a unique ID






//                    mDatabase.child(Integer.toString(counter)).child("question").setValue(questionTxt);
                    mDatabase.child("Classroom1").child("Forum").child(Integer.toString(counter)).child("question").setValue(questionTxt);

                    Toast.makeText(getContext(), "Question Submitted", Toast.LENGTH_SHORT).show();
                    FragmentManager manager;
                    manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new Forum()).commit();
                }







            }

        });


    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("counter.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("postQ activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("postQ activity", "Can not read file: " + e.toString());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if( ret.trim().equals("")){
            Log.e("postQ activity", "Empty String");
            ret = "30";
        }

        return ret;
    }


}
