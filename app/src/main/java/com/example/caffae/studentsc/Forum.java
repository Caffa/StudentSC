package com.example.caffae.studentsc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
                        String item = forumList.get(itemPosition).getQuestion();
                        Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



        fAdapter = new ForumAdapter(forumList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        prepareForumData();

        return view;
    }


    private void prepareForumData() {
        ForumQuestion question = new ForumQuestion("My Question", "My Answer");
        forumList.add(question);

        question = new ForumQuestion("Just my Question");
        forumList.add(question);

        fAdapter.notifyDataSetChanged();
    }





}
