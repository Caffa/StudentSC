package com.example.caffae.studentsc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Caffae on 21/2/18.
 */

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.MyViewHolder>{

    private List<ForumQuestion> forumList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer, answered;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question);
            answer = (TextView) view.findViewById(R.id.answer);
            answered = (TextView) view.findViewById(R.id.answered);
        }



    }


    public ForumAdapter(List<ForumQuestion> forumList){
        this.forumList = forumList;
    }

    @Override
    public ForumAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forum_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForumAdapter.MyViewHolder holder, int position) {
        ForumQuestion fm = forumList.get(position);
        holder.question.setText(fm.getQuestion());
        holder.answer.setText(fm.getAnswer());
        String answ = "Not Answered";
        if(fm.isAnswered()){
            answ = "Answered";
        }
        holder.answered.setText(answ);
    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }
}
