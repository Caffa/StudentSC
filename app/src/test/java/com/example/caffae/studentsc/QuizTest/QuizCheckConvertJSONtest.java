package com.example.caffae.studentsc.QuizTest;

import com.example.caffae.studentsc.Classroom.QuizAndBroadcastMain;
import com.example.caffae.studentsc.Classroom.QuizItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by dorette_ong on 7/3/2018.
 */

public class QuizCheckConvertJSONtest {
    QuizAndBroadcastMain quizAndBroadcastMain;
    ArrayList<QuizItem> expected = new ArrayList<>();
    ArrayList<QuizItem> generated;

    @Before
    public void runBeforeEachTest() {
        quizAndBroadcastMain = new QuizAndBroadcastMain();
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

        expected.add(quizItem1);
        expected.add(quizItem2);
        String jsonstring ="[{\"Question\":\"2+5=?\",\"Answer\":\"3\",\"Option1\":\"3\",\"Option2\":\"4\",\"Option3\":\"7\",\"Option4\":\"8\"},{\"Question\":\"14+7 =?\",\"Answer\":\"3\",\"Option1\":\"11\",\"Option2\":\"23\",\"Option3\":\"21\",\"Option4\":\"13\"}]";
        generated =quizAndBroadcastMain.convertJSON(jsonstring);
    }
    @Test
    public void testconvertJSONQuestion() {
        int j =0;
        for (int i = 0; i <expected.size();i++){
            if (generated.get(i).getQuestion().equals(expected.get(i).getQuestion())){
                j++;
            }
        }
        assertEquals(j,expected.size());
    }
    @Test
    public void testconvertJSONAnswer() {
        int j =0;
        for (int i = 0; i <expected.size();i++){
            if (generated.get(i).getAnswer().equals(expected.get(i).getAnswer())){
                j++;
            }
        }
        assertEquals(j,expected.size());
    }

    @Test
    public void testconvertJSONOption1() {
        int j =0;
        for (int i = 0; i <expected.size();i++){
            if (generated.get(i).getOption1().equals(expected.get(i).getOption1())){
                j++;
            }
        }
        assertEquals(j,expected.size());
    }

    @Test
    public void testconvertJSONOption2(){
        int j =0;
        for (int i = 0; i <expected.size();i++){
            if (generated.get(i).getOption2().equals(expected.get(i).getOption2())){
                j++;
            }
        }
        assertEquals(j,expected.size());
    }
    @Test
    public void testconvertJSONOption3(){
        int j =0;
        for (int i = 0; i <expected.size();i++){
            if (generated.get(i).getOption3().equals(expected.get(i).getOption3())){
                j++;
            }
        }
        assertEquals(j,expected.size());
    }
    @Test
    public void testconvertJSONOption4(){
        int j =0;
        for (int i = 0; i <expected.size();i++){
            if (generated.get(i).getOption4().equals(expected.get(i).getOption4())){
                j++;
            }
        }
        assertEquals(j,expected.size());
    }

}
