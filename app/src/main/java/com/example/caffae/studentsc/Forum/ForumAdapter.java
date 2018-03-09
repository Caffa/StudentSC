package com.example.caffae.studentsc.Forum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.caffae.studentsc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caffae on 21/2/18.
 */

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.MyViewHolder> implements Filterable{

    private List<ForumQuestion> forumList;
    private List<ForumQuestion> forumListFiltered;
    protected List<ForumQuestion> list;
    protected List<ForumQuestion> originalList;
    protected Context context;

    private ForumAdapterListener listener;

    @Override
    public Filter getFilter() {
        return new Filter() {
//            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                forumListFiltered = (ArrayList<ForumQuestion>) results.values;

                list = (List<ForumQuestion>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ForumQuestion> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = forumList;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                forumListFiltered = filteredResults;

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

    public ForumAdapter(Context context, List<ForumQuestion> list, ForumAdapterListener listener)
    {
        this.originalList = list;
        this.list = list;
        this.context = context;
        this.forumList = list;
        this.forumListFiltered = list;
        this.listener = listener;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer, answered;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question);
            answer = (TextView) view.findViewById(R.id.answer);
            answered = (TextView) view.findViewById(R.id.answered);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // send selected forumQ in callback
//                    ForumQuestion holding = forumListFiltered.get(getAdapterPosition());
//                    if(holding != null) {
//                        listener.onFQSelected(holding);
//                    }else{
//                        Toast.makeText(context, "Clicked a null object", Toast.LENGTH_SHORT);
//                    }
//                }
//            });
        }



    }

    @Override
    public ForumAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forum_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForumAdapter.MyViewHolder holder, int position) {
        final ForumQuestion fm = forumListFiltered.get(position);
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
        if(forumListFiltered == null){
            itemc = 0;
        }else{
            itemc = forumListFiltered.size();
        }


//        Log.d("RecyclerV", "The item count: " + itemc);
        return itemc;
    }

    public interface ForumAdapterListener {
        void onFQSelected(ForumQuestion fq);
    }
}
