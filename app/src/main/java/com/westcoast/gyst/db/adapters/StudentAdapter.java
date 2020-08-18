package com.westcoast.gyst.db.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> _dtList;


    @Override
    public long getItemId(int position) {
        return _dtList.get(position).getId();
    }

    public StudentAdapter(List<Student> list){
        this._dtList = list;
    }
    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_cardview, parent, false);
        return new StudentAdapter.StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        holder.bind(_dtList.get(position));
    }

    @Override
    public int getItemCount() {
        return _dtList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView _vorname;
        private TextView _nachname;
        private TextView _email;
        private TextView _id;

        public StudentViewHolder(View view){
            super(view);
            _vorname = view.findViewById(R.id.student_vorname);
            _nachname = view.findViewById(R.id.student_nachname);
            _email = view.findViewById(R.id.student_email);
            _id = view.findViewById(R.id.student_id);
        }


        @SuppressLint("SetTextI18n")
        public void bind(final Student data){
            _vorname.setText(data.getVorname());
            _nachname.setText(data.getNachname());
            _email.setText(data.getEmail());
            _id.setText(data.getId().toString());
        }
    }



}
