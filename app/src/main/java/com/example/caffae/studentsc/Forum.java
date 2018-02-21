package com.example.caffae.studentsc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Forum extends Fragment {
    private List<ForumQuestion> forumList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ForumAdapter fAdapter;

    public Forum(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.forumRecyclerView);

        fAdapter = new ForumAdapter(getContext(),forumList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fAdapter);
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

//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        prepareForumData();
        Log.d("RecyclerV", "Finish Data Prep");

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Click fab", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }


    private void prepareForumData() {

        Log.d("RecyclerV", "PreparingData");
        ForumQuestion question = new ForumQuestion("My Question", "My Answer");
        forumList.add(question);

        question = new ForumQuestion("Just my Question");
        forumList.add(question);

        question = new ForumQuestion("Another Question");
        forumList.add(question);

        question = new ForumQuestion("One Question", "One Answer");
        forumList.add(question);

        question = new ForumQuestion("Last Question");
        forumList.add(question);

        fAdapter.notifyDataSetChanged();
    }

//    @Override
    public boolean OnCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.forumSearch);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                fAdapter.getFilter().filter(text);
                return true;
            }
        });
        return true;
    }
//
//    @Override
//    public boolean onQueryTextChange(String query) {
//        // Here is where we are going to implement the filter logic
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//






}
