package com.westcoast.gyst.ui.students;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.adapters.StudentAdapter;
import com.westcoast.gyst.db.entities.Student;
import com.westcoast.gyst.common.RecyclerItemClickListener;
import com.westcoast.gyst.ui.overview.OverviewActivity;

import java.util.List;

public class StudentActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener,View.OnCreateContextMenuListener {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab;

    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        SugarContext.init(this);

        rv = findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        fab = findViewById(R.id.fab);

        courseId = getIntent().getIntExtra("courseId",0);

        fab.setOnClickListener(view -> {

            showCreateDialog();
            refresh();
        });

        refresh();
    }

    public void refresh(){
        int i = courseId;
        List<Student> students = Student.find(Student.class, "course_id = ?", Integer.toString(i));
        adapter = new StudentAdapter(students);
        rv.setAdapter(adapter);
    }


    public void showCreateDialog(){
        CreateStudentDialog.display(getSupportFragmentManager(), courseId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SugarContext.terminate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SugarContext.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SugarContext.init(this);
    }

    @Override
    public void onItemClick(View childView, int position) {
        int id = (int) adapter.getItemId(position);
        Intent intent = new Intent(StudentActivity.this, OverviewActivity.class);
        intent.putExtra("schuelerId", id);

        startActivity(intent);
    }

    @Override
    public void onItemLongPress(View childView, int position) {
        getWindow().getDecorView().performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        registerForContextMenu(childView);
        openContextMenu(childView);
        unregisterForContextMenu(childView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        StudentAdapter.StudentViewHolder holder = (StudentAdapter.StudentViewHolder)rv.getChildViewHolder(view);
        int position = holder.getAdapterPosition();

        int id = (int) adapter.getItemId(position);

        contextMenu.add(1,id, 0, "Bearbeiten").setOnMenuItemClickListener(menuItem -> {
            EditStudentDialog.display(getSupportFragmentManager(), menuItem.getItemId());
            return true;
        });
        contextMenu.add(2,id, 0, "Löschen").setOnMenuItemClickListener(menuItem -> {
            SugarContext.init(this);
            Student student = Student.findById(Student.class, menuItem.getItemId());
            student.delete();
            refresh();
            return true;
        });
    }
}
