package com.westcoast.gyst.db;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.westcoast.gyst.CourseDialog;
import com.westcoast.gyst.MainActivity;
import com.westcoast.gyst.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> dtList = new ArrayList<Course>();
    private Context context;

    public CourseAdapter(List<Course> list, Context context) {
        this.context = context;
        this.dtList = list;
    }


    public CourseAdapter(List<Course> list){
        this.dtList = list;
    }
    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_cardview, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bind(dtList.get(position));
    }

    @Override
    public int getItemCount() {
        return dtList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        public TextView fach;
        public TextView zeit;
        public TextView klasse;
        public TextView id;

        public CourseViewHolder(View view){
            super(view);
            fach = view.findViewById(R.id.kurs_fach);
            zeit = view.findViewById(R.id.kurs_zeit);
            klasse = view.findViewById(R.id.kurs_klasse);
            id = view.findViewById(R.id.kurs_id);
    }


        public void bind(final Course data){
            fach.setText(data.getFach());
            zeit.setText(data.getZeit());
            klasse.setText(data.getKlasse());
            id.setText(data.getId().toString());
        }
    }
}
