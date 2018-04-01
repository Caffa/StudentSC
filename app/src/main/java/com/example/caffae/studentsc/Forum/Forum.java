package com.example.caffae.studentsc.Forum;

import android.app.SearchManager;
import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.caffae.studentsc.Forum.ForumAdapter.ForumAdapterListener;
import com.example.caffae.studentsc.MainActivity;
import com.example.caffae.studentsc.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

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
        // Inflate the layout for this fragment
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
                        String item = forumList.get(itemPosition).getQuestion();
                        Toast.makeText(getContext(), "Short Click " + item, Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(getContext(), "Long Click", Toast.LENGTH_LONG).show();
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

    private void fetchDatabaseInfo() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }



                        List<ForumQuestion> items = new Gson().fromJson(response.toString(), new TypeToken<List<ForumQuestion>>() {
                        }.getType());

                        for(ForumQuestion i : items){
                            if(i.getQuestion().equals("0")){
                                //this is the counter node so don't show
                                i.setDontShow();
                                items.remove(i);
                            }

                            if(i.getAnswer().equals("")){
                                i.setAnswered(false);
                            }else{
                                i.setAnswered(true);
                            }
                        }

                        forumList.clear();
                        forumList.addAll(items);
//                        for(ForumQuestion i : items) {
//                            if (i.show == false) {
//                                //this is the counter node so don't show
//                                forumList.remove(i);
//                            }
//                        }

                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("counter.txt", Context.MODE_PRIVATE));
                            outputStreamWriter.write(items.size());
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
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);

    }

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
        Toast.makeText(getContext(), "Selected: " + fq.getQuestion() + ", " + fq.getAnswer(), Toast.LENGTH_LONG).show();

    }
}
