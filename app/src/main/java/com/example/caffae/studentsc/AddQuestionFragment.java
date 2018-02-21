package com.example.caffae.studentsc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
                String questionTxt = edittextsubmitquestion.getText().toString();;
                if(questionTxt.equals("")){
                    Toast.makeText(getContext(), "Question is Blank", Toast.LENGTH_SHORT).show();
                }else{
//                    ForumQuestion holder = new ForumQuestion(questionTxt);
                    //TODO add to firebase properly

                    mDatabase = FirebaseDatabase.getInstance().getReference();

//                    Toast.makeText(getContext(), lastQuery.toString(), Toast.LENGTH_SHORT).show();
//                    String uniqueNode = mDatabase.push().getKey();


                    int counter = 30;


                    mDatabase.child(Integer.toString(counter)).child("question").setValue(questionTxt);


                    Toast.makeText(getContext(), "Question Submitted", Toast.LENGTH_SHORT).show();
                    FragmentManager manager;
                    manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new Forum()).commit();
                }







            }

        });


    }
}
