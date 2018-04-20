package com.example.caffae.studentsc.Forum;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caffae.studentsc.R;
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
        edittextsubmitquestion = view.findViewById(R.id.QuestionTxtSubmit);
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

                    //get student ID
                    SharedPreferences sharedPrefStudent = getActivity().getSharedPreferences(getString(R.string.studentId), Context.MODE_PRIVATE);
                    int studentId = sharedPrefStudent.getInt(getString(R.string.studentId), 1000030);


                    //get counter ID
                    SharedPreferences myCounterPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                    int countVar = myCounterPref.getInt(getString(R.string.counterQ), 1);
//                    String countTxt = myCounterPref.getString(getString(R.string.counterQuestion).trim(), "1");
                    Integer countvar = myCounterPref.getInt(getString(R.string.counterQuestion), 1);
                    String countTxt = String.valueOf(countvar);

                    //Increment and save
                    int newCount = Integer.parseInt(countTxt) + 1;

                    SharedPreferences.Editor editorC = myCounterPref.edit();
                    editorC.putInt(getString(R.string.counterQuestion), newCount);
                    editorC.commit();

                    //merge the IDs
                    String myStudentId = String.valueOf(studentId - 1000000);
//                    int counter = Integer.parseInt(myStudentId.trim() + countTxt.trim());
                    String mergedIds = myStudentId.trim() + countTxt.trim();

                    //get classroom ID
                    SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.classroomID), Context.MODE_PRIVATE);
                    String classroom = sharedPref.getString(getString(R.string.classroomID), "Classroom1");

                    //Post



                    //Add the numberID for the student into this so can make it a unique ID





//                    int newCount = 1 + Integer.parseInt(countTxt);
//                    String myCountTxt = String.valueOf(newCount);
//                    System.out.println(destination.getAbsolutePath());


//                    stringToFile(myCountTxt,"counter.txt");


//                    mDatabase.child(Integer.toString(counter)).child("question").setValue(questionTxt);
                    mDatabase.child(classroom).child("Forum").child(mergedIds).child("question").setValue(questionTxt);

                    Toast.makeText(getContext(), "Question Submitted", Toast.LENGTH_SHORT).show();
                    FragmentManager manager;
                    manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new Forum()).commit();
                }







            }

        });


    }
//
//    private String readFromFile(Context context) {
//
//        String ret = "";
//
//        try {
//            InputStream inputStream = context.openFileInput("counter.txt");
//
//            if ( inputStream != null ) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String receiveString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while ( (receiveString = bufferedReader.readLine()) != null ) {
//                    stringBuilder.append(receiveString);
//                }
//
//                inputStream.close();
//                ret = stringBuilder.toString();
//            }
//        }
//        catch (FileNotFoundException e) {
//            Log.e("postQ activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("postQ activity", "Can not read file: " + e.toString());
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
//
//        if( ret.trim().equals("")){
//            Log.e("postQ activity", "Empty String");
//            ret = "30";
//        }
//
//        return ret;
//    }
//
//    private void stringToFile( String text, String fileName )
//    {
//        try
//        {
//            File file = new File( fileName );
//
//            // if file doesnt exists, then create it
//            if ( ! file.exists( ) )
//            {
//                file.createNewFile( );
//            }
//
//            FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
//            BufferedWriter bw = new BufferedWriter( fw );
//            bw.write( text );
//            bw.close( );
//            //System.out.println("Done writing to " + fileName); //For testing
//        }
//        catch( IOException e )
//        {
//            System.out.println("Error: " + e);
//            e.printStackTrace( );
//        }
    } //End method stringToFile



