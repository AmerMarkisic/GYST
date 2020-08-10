package com.westcoast.gyst.db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Grade;

import java.util.ArrayList;
import java.util.List;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.OverviewViewHolder> {

    private List<Grade> dtList = new ArrayList<Grade>();
    private Context context;
    private String className;
    private long average;

    public OverviewAdapter(List<Grade> list, Context context) {
        this.context = context;
        this.dtList = list;
    }

    @Override
    public long getItemId(int position) {
        return dtList.get(position).getId();
    }

    public OverviewAdapter(List<Grade> list, long average) {
        this.dtList = list;
        this.average = average;
    }

    @NonNull
    @Override
    public OverviewAdapter.OverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_table, parent, false);
        return new OverviewAdapter.OverviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OverviewAdapter.OverviewViewHolder holder, int position) {
        holder.bind(dtList.get(position), this.average);
    }

    @Override
    public int getItemCount() {
        return dtList.size();
    }

    public class OverviewViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView note;
        public TextView index;
        public TextView average;

        public OverviewViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.overview_title);
            note = view.findViewById(R.id.overview_grade);
            index = view.findViewById(R.id.overview_grade_index);
            average = view.findViewById(R.id.overview_average);
        }

        public void bind(final Grade data, long averageGrade) {
            title.setText(data.getBeschreibung());
            note.setText(data.getNote());
            //index.setText();
            average.setText(String.valueOf(averageGrade));
        }
    }
}
