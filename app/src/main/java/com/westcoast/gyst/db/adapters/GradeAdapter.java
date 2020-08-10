package com.westcoast.gyst.db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Course;
import com.westcoast.gyst.db.entities.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeViewHoler> {


    private List<Grade> dtList = new ArrayList<Grade>();
    private Context context;

    public GradeAdapter(List<Grade> list, Context context) {
        this.context = context;
        this.dtList = list;
    }

    @Override
    public long getItemId(int position) {
        return dtList.get(position).getId();
    }

    public GradeAdapter(List<Grade> list){
        this.dtList = list;
    }

    @NonNull
    @Override
    public GradeAdapter.GradeViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_cardview, parent, false);
        return new GradeAdapter.GradeViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter.GradeViewHoler holder, int position) {
        holder.bind(dtList.get(position));
    }

    @Override
    public int getItemCount() {
        return dtList.size();
    }

    public class GradeViewHoler extends RecyclerView.ViewHolder{
        public TextView beschreibung;
        public TextView note;
        public TextView id;

        public GradeViewHoler(View view){
            super(view);
            beschreibung = view.findViewById(R.id.kurs_zeit);
            note = view.findViewById(R.id.kurs_klasse);
            id = view.findViewById(R.id.kurs_id);
        }


        public void bind(final Grade data){
            beschreibung.setText(data.getBeschreibung());
            note.setText(data.getNote());
            id.setText(data.getId().toString());
        }
    }
}
