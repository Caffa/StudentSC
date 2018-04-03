package com.example.caffae.studentsc.QuizTest;

import com.example.caffae.studentsc.Classroom.QuizItem;
import com.example.caffae.studentsc.Classroom.QuizAndBroadcastMain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dorette_ong on 7/3/2018.
 */
@RunWith(Parameterized.class)
public class QuizCheckPrintScoreTest {
    private QuizAndBroadcastMain quizAndBroadcastMain;
    private ArrayList<QuizItem> quiz;
    private ArrayList<Integer> buttonnumber;
    int expected;

    public QuizCheckPrintScoreTest (int expected, ArrayList<Integer> buttonnumber , ArrayList<QuizItem>quiz) {
        this.expected = expected;
        this.buttonnumber=buttonnumber;
        this.quiz=quiz;

    }
    @Before
    public void runBeforeEachTest() {
        quizAndBroadcastMain = new QuizAndBroadcastMain();
    }

    @Parameterized.Parameters
    private static Collection<Object[]> parameters() {
        ArrayList<QuizItem> setquiz= new ArrayList<>();
        QuizItem quizItem1 = new QuizItem();
        quizItem1.setQuestion("2+5=?");
        quizItem1.setOption1("3");
        quizItem1.setOption2("4");
        quizItem1.setOption3("7");
        quizItem1.setOption4("8");
        quizItem1.setAnswer("3");
        QuizItem quizItem2 = new QuizItem();
        quizItem2.setQuestion("14+7 =?");
        quizItem2.setOption1("11");
        quizItem2.setOption2("23");
        quizItem2.setOption3("21");
        quizItem2.setOption4("13");
        quizItem2.setAnswer("3");
        setquiz.add(quizItem1);
       setquiz.add(quizItem2);
        ArrayList<Integer> test1 = new ArrayList<>();
        test1.add(1);
        test1.add(3);
        ArrayList<Integer> test2 = new ArrayList<>();
        test2.add(3);
        test2.add(3);
        ArrayList<Integer> test3 = new ArrayList<>();
        test3.add(1);
        test3.add(1);
        return Arrays.asList (new Object [][] {{1,test1,setquiz}, {2,test2,setquiz},{0,test3,setquiz}});
    }

    @Test
    public void testPrintScore() {
        assertEquals(expected, quizAndBroadcastMain.multipleChoiceScore(buttonnumber, quiz));
    }

}
