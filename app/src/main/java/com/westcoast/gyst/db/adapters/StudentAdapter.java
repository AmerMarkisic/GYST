package com.westcoast.gyst.db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> dtList = new ArrayList<Student>();
    private Context context;

    public StudentAdapter(List<Student> list, Context context) {
        this.context = context;
        this.dtList = list;
    }

    @Override
    public long getItemId(int position) {
        return dtList.get(position).getId();
    }

    public StudentAdapter(List<Student> list){
        this.dtList = list;
    }
    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_cardview, parent, false);
        return new StudentAdapter.StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        holder.bind(dtList.get(position));
    }

    @Override
    public int getItemCount() {
        return dtList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        public TextView vorname;
        public TextView nachname;
        public TextView email;
        public TextView id;

        public StudentViewHolder(View view){
            super(view);
            vorname = view.findViewById(R.id.student_vorname);
            nachname = view.findViewById(R.id.student_nachname);
            email = view.findViewById(R.id.student_email);
            id = view.findViewById(R.id.student_id);
        }


        public void bind(final Student data){
            vorname.setText(data.getVorname());
            nachname.setText(data.getNachname());
            email.setText(data.getEmail());
            id.setText(data.getId().toString());
        }
    }



}
