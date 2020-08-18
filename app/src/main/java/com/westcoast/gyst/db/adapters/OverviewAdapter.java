package com.westcoast.gyst.db.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Grade;

import java.util.List;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.OverviewViewHolder> {

    private List<Grade> _dtList;


    @Override
    public long getItemId(int position) {
        return _dtList.get(position).getId();
    }

    public OverviewAdapter(List<Grade> list) {
        this._dtList = list;
    }

    @NonNull
    @Override
    public OverviewAdapter.OverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_table, parent, false);
        return new OverviewAdapter.OverviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OverviewAdapter.OverviewViewHolder holder, int position) {
        holder.bind(_dtList.get(position));
    }

    @Override
    public int getItemCount() {
        return _dtList.size();
    }

    public static class OverviewViewHolder extends RecyclerView.ViewHolder{
        private TextView _note;
        private TextView _index;
        private TextView _description;
        public static int _counter;

        public OverviewViewHolder(View view) {
            super(view);
            _note = view.findViewById(R.id.overview_grade);
            _index = view.findViewById(R.id.overview_grade_index);
            _description = view.findViewById(R.id.overview_description);
        }

        public void bind(final Grade data) {
            _note.setText(data.getNote());
            _description.setText(data.getBeschreibung());
            _index.setText(String.format("%s.", _counter));
            _counter++;
        }
    }


}
