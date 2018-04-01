package com.example.caffae.studentsc.Class;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.caffae.studentsc.R;
import com.example.caffae.studentsc.StudentMainActivity;

import java.util.ArrayList;



public class QuizFragment extends Fragment {

    public QuizFragment() {

    }

   QuizMain quizMain;
    LinearLayout quizlinearlayout;
    Button quizsubmitbutton;
    ArrayList<Integer> selectedAnswers;
    ArrayList<QuizItem> questionanswer;
    int j = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View noQuizView =  inflater.inflate(R.layout.fragment_no_ongoing_quiz, container, false);
        if (StudentMainActivity.ongoingQuiz.equals("-1")){
            return noQuizView;
        }
        else {

            View view = inflater.inflate(R.layout.fragment_quiz, container, false);
            selectedAnswers = new ArrayList<>();
            quizsubmitbutton = new Button(getContext());
            quizsubmitbutton = view.findViewById(R.id.quizsubmitbutton);
            quizlinearlayout = new LinearLayout(getContext());
            quizlinearlayout = view.findViewById(R.id.quizlinearlayout);
            quizMain = new QuizMain();
            quizMain.convertJSON(AvailableClassroomFragment.quizjsonArray.toString());
            addListenerOnButton(view);
            questionanswer = quizMain.getquestionanswer();

            j = 0;
            for (QuizItem i : questionanswer) {
                addQuizItem(i, j);
                j++;
            }

            return view;
        }
    }

    public void addQuizItem(QuizItem quizItem, final int k) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_quiz, null);
        TextView question = v.findViewById(R.id.quizquestion);
        question.setText(quizItem.getQuestion());
        RadioButton option1 = v.findViewById(R.id.quizOption1);
        option1.setText(quizItem.getOption1());
        RadioButton option2 = v.findViewById(R.id.quizOption2);
        option2.setText(quizItem.getOption2());
        RadioButton option3 = v.findViewById(R.id.quizOption3);
        option3.setText(quizItem.getOption3());
        RadioButton option4 = v.findViewById(R.id.quizOption4);
        option4.setText(quizItem.getOption4());
        quizlinearlayout.addView(v);
        RadioGroup radiogroup = v.findViewById(R.id.quizradiogroup);
        selectedAnswers.add(-1);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int buttonnumber=0; buttonnumber<4; buttonnumber++){
                    if(radioGroup.getChildAt(buttonnumber).getId()==radioGroup.getCheckedRadioButtonId()){
                        selectedAnswers.set(k,buttonnumber+1);
                    };
                }
            }
        });
    }

    public void addListenerOnButton(View view) {
       quizsubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int finalscore= quizMain.printScore(selectedAnswers,questionanswer);
                DatabaseClassroom databaseClassroom = new DatabaseClassroom();
                databaseClassroom.pushQuizScores(finalscore);
                Toast.makeText(getContext(),"Submitted! Your score is " + finalscore, Toast.LENGTH_SHORT).show();
                }
        });

    }
}
