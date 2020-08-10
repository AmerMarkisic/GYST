package com.westcoast.gyst.ui.students;

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
import com.westcoast.gyst.ui.courses.CreateCourseDialog;
import com.westcoast.gyst.ui.courses.EditCourseDialog;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Course;
import com.westcoast.gyst.db.entities.Student;
import com.westcoast.gyst.db.adapters.StudentAdapter;
import com.westcoast.gyst.infrastructure.RecyclerItemClickListener;

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

        rv = (RecyclerView)findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        fab = findViewById(R.id.fab);

        courseId = getIntent().getIntExtra("courseId",0);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCreateDialog();
                refresh();
            }
        });

        refresh();

    }

    public void refresh(){
        Integer i = Integer.valueOf(courseId);
        List<Student> students = Student.find(Student.class, "courseId = ?", i.toString());
        adapter = new StudentAdapter(students);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showCreateDialog(){
        CreateStudentDialog.display(getSupportFragmentManager(), courseId);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }

    @Override
    public void onItemClick(View childView, int position) {


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

        contextMenu.add(1,id, 0, "Bearbeiten").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                EditCourseDialog.display(getSupportFragmentManager(), menuItem.getItemId());
                return true;
            }
        });
        contextMenu.add(2,id, 0, "Löschen").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Course course = Course.findById(Course.class, menuItem.getItemId());
                course.delete();
                refresh();
                return true;
            }
        });
    }


}
