package com.example.caffae.studentsc.Forum;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caffae.studentsc.Forum.ForumAdapter.ForumAdapterListener;
import com.example.caffae.studentsc.MainActivity;
import com.example.caffae.studentsc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class Forum extends Fragment implements ForumAdapter.ForumAdapterListener {
    private List<ForumQuestion> forumList;
    private RecyclerView recyclerView;
    private ForumAdapter fAdapter;
    ForumAdapterListener listener;
    private DatabaseReference mDatabase;
    String currentQ;
    String currentAns;



    private static final String TAG = MainActivity.class.getSimpleName();
    private SearchView searchView;

    // url to fetch contacts json
//    private static final String URL = "https://softwareconstruct-forum.firebaseio.com/.json";
    private static final String URL = "https://softwareconstruct-forum.firebaseio.com/Classroom1/Forum.json";


    public Forum(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the shortansweritem_quiz for this fragment
        this.setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        forumList = new ArrayList<>();

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // toolbar fancy stuff
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title);


        // white background notification bar
//        whiteNotificationBar(recyclerView);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        fAdapter = new ForumAdapter(getContext(),forumList, listener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fAdapter);
        //clicking works for recycler view.... but trying something else
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
//                        String item = forumList.get(itemPosition).getQuestion();
//                        Toast.makeText(getContext(), "Short Click " + item, Toast.LENGTH_LONG).show();
                        ForumQuestion item = forumList.get(itemPosition);
                        onFQSelected(item);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
//                        Toast.makeText(getContext(), "Long Click", Toast.LENGTH_LONG).show();
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
                        ForumQuestion item = forumList.get(itemPosition);
                        longPress(item);
                    }
                })
        );
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(fAdapter);

//        prepareForumData();

        fetchDatabaseInfo();



        Log.d("RecyclerV", "Finish Data Prep");

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Click fab", Toast.LENGTH_LONG).show();

//                Fragment nextFrag = new Fragment();
//                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.all, nextFrag);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
                android.support.v4.app.FragmentManager manager;
                manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.container, new AddQuestionFragment()).commit();

            }
        });

        return view;
    }

    void fetchDatabaseInfo(){

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.classroomID), Context.MODE_PRIVATE);
        String classroom = sharedPref.getString(getString(R.string.classroomID), "Classroom1");
//        System.out.println("Current classroom" + classroom);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query mQueryRef = mDatabase.child(classroom).child("Forum");
        mQueryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+ dataSnapshot.getChildrenCount());
                forumList.clear();
                for (DataSnapshot i: dataSnapshot.getChildren()){
                    ForumQuestion holder;
//                    System.out.println("Key " + i.getKey() + " Value " + i.getValue(String.class));
//                    List<ForumQuestion> items = new Gson().fromJson(i.toString(), new TypeToken<List<ForumQuestion>>() {}.getType());

//                    System.out.println(i.getKey()); //useless node number
//                    System.out.println(i.getValue()); //string off stuff
//                    System.out.println(i.toString());

//                    forumList.addAll(items);
//                    System.out.println(i.getValue());

                    if(i.getValue().toString().contains("answer")){
                        holder = new ForumQuestion(i.child("question").getValue().toString(), i.child("answer").getValue().toString());
                        holder.setAnswered(true);
                    }else{
                        holder = new ForumQuestion(i.child("question").getValue().toString());
                        holder.setAnswered(false);
                    }
                    forumList.add(holder);

//                    System.out.println(i.child("question").getValue());
                }
                try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("counter.txt", Context.MODE_PRIVATE));
                            outputStreamWriter.write(forumList.size());
                            outputStreamWriter.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                        }

                        // refreshing recycler view
                        fAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " , databaseError.getMessage());
            }
        });}

//    private void fetchDatabaseInfo() {
//        JsonArrayRequest request = new JsonArrayRequest(URL,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if (response == null) {
//                            Toast.makeText(getContext(), "Couldn't fetch the forumQs! Pleas try again.", Toast.LENGTH_LONG).show();
//                            return;
//                        }
//
//
//
//                        List<ForumQuestion> items = new Gson().fromJson(response.toString(), new TypeToken<List<ForumQuestion>>() {
//                        }.getType());
//
//                        for(ForumQuestion i : items){
////                            if(i.getQuestion().equals("0")){
////                                //this is the counter node so don't show
////                                i.setDontShow();
////                                items.remove(i);
////                            }
//
//                            if(i.getAnswer().equals("")){
//                                i.setAnswered(false);
//                            }else{
//                                i.setAnswered(true);
//                            }
//                        }
//
//                        forumList.clear();
//                        forumList.addAll(items);
////                        for(ForumQuestion i : items) {
////                            if (i.show == false) {
////                                //this is the counter node so don't show
////                                forumList.remove(i);
////                            }
////                        }
//
//                        try {
//                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("counter.txt", Context.MODE_PRIVATE));
//                            outputStreamWriter.write(items.size());
//                            outputStreamWriter.close();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        catch (NullPointerException e){
//                            e.printStackTrace();
//                        }
//
//                        // refreshing recycler view
//                        fAdapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // error in getting json
//                Log.e(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        MyApplication.getInstance().addToRequestQueue(request);
//
//    }

//
//    private void prepareForumData() {
//
//        Log.d("RecyclerV", "PreparingData");
//        ForumQuestion question = new ForumQuestion("My Question", "My Answer");
//        forumList.add(question);
//
//        question = new ForumQuestion("Just my Question");
//        forumList.add(question);
//
//        question = new ForumQuestion("Another Question");
//        forumList.add(question);
//
//        question = new ForumQuestion("One Question", "One Answer");
//        forumList.add(question);
//
//        question = new ForumQuestion("Last Question");
//        forumList.add(question);
//
//        fAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("ToolB", "Inflating Menu");
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        Log.d("ToolB", "Inflated Menu");
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String text) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String text) {
//                fAdapter.getFilter().filter(text);
//                return true;
//            }
//        });
//        return true;

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                fAdapter.getFilter().filter(query);
                Log.d("ToolB", "onQueryTextSubmit");
                return false;

            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                fAdapter.getFilter().filter(query);
                Log.d("ToolB", "onQueryTextChange");
                return false;
            }
        });
//        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    @Override
//    public void onBackPressed() {
//        // close search view on back button pressed
//        if (!searchView.isIconified()) {
//            searchView.setIconified(true);
//            return;
//        }
//        super.onBackPressed();
//    }

//    private void whiteNotificationBar(View view) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            int flags = view.getSystemUiVisibility();
//            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            view.setSystemUiVisibility(flags);
//            getActivity().getWindow().setStatusBarColor(Color.WHITE);
//        }
//    }


    @Override
    public void onFQSelected(ForumQuestion fq) {
        currentQ = fq.getQuestion();
//        Toast.makeText(getContext(), "Selected: " + fq.getQuestion() + ", " + fq.getAnswer(), Toast.LENGTH_LONG).show();
        FancyToast.makeText(getContext(),"Selected " + fq.getQuestion(),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();

//        FancyToast.makeText(getContext(),fq.getAnswer(),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
        if(fq.isAnswered()){
        currentAns = fq.getAnswer();}
        else{
            currentAns = "Not answered yet";
        }
//        Snackbar mySnackbar = Snackbar.make(getView(), currentAns, LENGTH_LONG);
//        mySnackbar.setAction("Expand", new MyShowAnsListener());
//        mySnackbar.show();



    }

    public void longPress(ForumQuestion fq){
        currentQ = fq.getQuestion();
        if(fq.isAnswered()){
            currentAns = fq.getAnswer();}
        else{
            currentAns = "Not answered yet";
        }
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popupview);//popup view is the shortansweritem_quiz you created
        TextView txt = (TextView)dialog.findViewById(R.id.contentBoxAns);
        txt.setText(currentAns);
        TextView qtitle = (TextView)dialog.findViewById(R.id.titleQ);
        qtitle.setText(currentQ);
        dialog.show();

    }

//    public class MyShowAnsListener implements View.OnClickListener{
//
//        @Override
//        public void onClick(View v) {
//            // Code to undo the user's last action
//            //TODO display screen for currentAns
//            Dialog dialog = new Dialog(getContext());
//            dialog.setContentView(R.shortansweritem_quiz.popupview);//popup view is the shortansweritem_quiz you created
//            TextView txt = (TextView)dialog.findViewById(R.id.contentBoxAns);
//            txt.setText(currentAns);
//            TextView qtitle = (TextView)dialog.findViewById(R.id.titleQ);
//            qtitle.setText(currentQ);
//            dialog.show();
//
//        }
//    }
}
