package com.example.caffae.studentsc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caffae on 21/2/18.
 */

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.MyViewHolder> implements Filterable{

    private List<ForumQuestion> forumList;

    protected List<ForumQuestion> list;
    protected List<ForumQuestion> originalList;
    protected Context context;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<ForumQuestion>) results.values;
                ForumAdapter.this.notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ForumQuestion> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = forumList;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected List<ForumQuestion> getFilteredResults(String constraint) {
        List<ForumQuestion> results = new ArrayList<>();

        for (ForumQuestion item : originalList) {
            if (item.getQuestion().toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer, answered;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question);
            answer = (TextView) view.findViewById(R.id.answer);
            answered = (TextView) view.findViewById(R.id.answered);
        }



    }

    public ForumAdapter(Context context,
                                 List<ForumQuestion> list)
    {
        this.originalList = list;
        this.list = list;
        this.context = context;
        this.forumList = list;
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
        int itemc;
        if(forumList == null){
            itemc = 0;
        }else{
            itemc = forumList.size();
        }


        Log.d("RecyclerV", "The item count: " + itemc);
        return itemc;
    }
}
