package com.example.caffae.studentsc.QuizTest;

import com.example.caffae.studentsc.Classroom.QuizAndBroadcastMain;
import com.example.caffae.studentsc.Classroom.QuizItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dorette_ong on 3/4/2018.
 */
@RunWith(Parameterized.class)
public class QuizShortAnswerScoreTest {
    // tests the score for the short answer questions

    private QuizAndBroadcastMain quizAndBroadcastMain;
    private ArrayList<QuizItem> quiz;
    private ArrayList<String> submittedAnswers;
    private double expectedscore;

    public QuizShortAnswerScoreTest(double expectedscore, ArrayList<String> submittedAnswers, ArrayList<QuizItem> quiz) {
        this.expectedscore = expectedscore;
        this.submittedAnswers = submittedAnswers;
        this.quiz = quiz;

    }

    @Before
    public void runBeforeEachTest() {
        quizAndBroadcastMain = new QuizAndBroadcastMain();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        ArrayList<QuizItem> setquiz = new ArrayList<>();
        QuizItem quizItem1 = new QuizItem();
        quizItem1.setQuestion("Type Hello");
        quizItem1.setAnswer("Hello");
        QuizItem quizItem2 = new QuizItem();
        quizItem2.setQuestion("Type False");
        quizItem2.setAnswer("False");
        QuizItem quizItem3 = new QuizItem();
        quizItem3.setQuestion("Type Testing");
        quizItem3.setAnswer("Testing");
        setquiz.add(quizItem1);
        setquiz.add(quizItem2);
        setquiz.add(quizItem3);
        ArrayList<String> submittedTest1 = new ArrayList<>();
        submittedTest1.add("Fail");
        submittedTest1.add("Fail");
        submittedTest1.add("Fail");
        ArrayList<String> submittedTest2 = new ArrayList<>();
        submittedTest2.add("Hello");
        submittedTest2.add("False");
        submittedTest2.add("Testing");
        ArrayList<String> submittedTest3 = new ArrayList<>();
        submittedTest3.add("Hello");
        submittedTest3.add("False");
        submittedTest3.add("Fail");
        ArrayList<String> submittedTest4 = new ArrayList<>();
        submittedTest4.add("");
        submittedTest4.add("");
        submittedTest4.add("");

        return Arrays.asList(new Object[][]{{0.0, submittedTest1, setquiz}, {3.0, submittedTest2, setquiz}, {2.0, submittedTest3, setquiz}, {0.0, submittedTest4, setquiz}});
    }

    @Test
    public void testPrintScore() {
        for (int i = 0; i < submittedAnswers.size(); i++) {
            quizAndBroadcastMain.shortAnswerScore(submittedAnswers.get(i), quiz.get(i).getAnswer());
        }
        assertEquals(expectedscore, quizAndBroadcastMain.getScore());


    }

}
