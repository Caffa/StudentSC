package com.example.caffae.studentsc.Classroom;

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
    private QuizAndBroadcastMain quizMain;
    private LinearLayout quizlinearlayout;
    private Button quizsubmitbutton;
    private ArrayList<Integer> selectedAnswers;
    private ArrayList<QuizItem> questionanswer;
    int j = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate and return no ongoing quiz layout if no current quiz
        if (StudentMainActivity.ongoingQuiz.equals("-1")) {
            return inflater.inflate(R.layout.fragment_no_ongoing_quiz, container, false);
        }
        // Else inflate and return layout with quiz
        else {

            View view = inflater.inflate(R.layout.fragment_quiz, container, false);
            selectedAnswers = new ArrayList<>();
            quizsubmitbutton = new Button(getContext());
            quizsubmitbutton = view.findViewById(R.id.quizsubmitbutton);
            quizlinearlayout = new LinearLayout(getContext());
            quizlinearlayout = view.findViewById(R.id.quizlinearlayout);

            quizMain = new QuizAndBroadcastMain();

            //covert JSON string into list of Quiz Items
            quizMain.convertJSON(AvailableClassroomFragment.quizjsonArray.toString());
            addListenerOnButton();
            questionanswer = quizMain.getquestionanswer();

            //Dynamically add quiz items into the overall linear layout
            j = 0;
            for (QuizItem i : questionanswer) {
                addQuizItem(i, j);
                j++;
            }

            return view;
        }
    }

    private void addQuizItem(QuizItem quizItem, final int k) {
        // Inflate the layout for a single quiz item
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_quiz, null);

        // Set text for individual radio buttons
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

        //Add selected answers for each question into ArrayList selctedAnswers as (questionnumber, selected option)
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int buttonnumber = 0; buttonnumber < 4; buttonnumber++) {
                    if (radioGroup.getChildAt(buttonnumber).getId() == radioGroup.getCheckedRadioButtonId()) {
                        selectedAnswers.set(k, buttonnumber + 1);
                    }
                }
            }
        });
    }

    // Add listener for submit button
    // Toast Quizscore and push Quizscores to database
    private void addListenerOnButton() {
        quizsubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int finalscore = quizMain.printScore(selectedAnswers, questionanswer);
                DatabaseClassroom databaseClassroom = new DatabaseClassroom();
                databaseClassroom.pushQuizScores(finalscore);
                Toast.makeText(getContext(), "Submitted! Your score is " + finalscore, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
