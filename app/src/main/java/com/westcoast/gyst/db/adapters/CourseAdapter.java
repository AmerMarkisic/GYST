package com.westcoast.gyst.db.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> _dtList;

    public CourseAdapter(List<Course> list) {
        this._dtList = list;
    }

    @Override
    public long getItemId(int position) {
        return _dtList.get(position).getId();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_cardview, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bind(_dtList.get(position));
    }

    @Override
    public int getItemCount() {
        return _dtList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder{
        private TextView _fach;
        private TextView _zeit;
        private TextView _klasse;
        private TextView _id;

        public CourseViewHolder(View view){
            super(view);
            _fach = view.findViewById(R.id.kurs_fach);
            _zeit = view.findViewById(R.id.kurs_zeit);
            _klasse = view.findViewById(R.id.kurs_klasse);
            _id = view.findViewById(R.id.kurs_id);
    }


        @SuppressLint("SetTextI18n")
        public void bind(final Course data){
            _fach.setText(data.getFach());
            _zeit.setText(data.getZeit());
            _klasse.setText(data.getKlasse());
            _id.setText(data.getId().toString());
        }
    }
}
