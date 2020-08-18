package com.westcoast.gyst.ui.courses;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Course;
import com.westcoast.gyst.db.adapters.CourseAdapter;
import com.westcoast.gyst.infrastructure.RecyclerItemClickListener;
import com.westcoast.gyst.ui.students.StudentActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.HapticFeedbackConstants;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class CourseActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener,View.OnCreateContextMenuListener{

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab;

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
        fab.setOnClickListener(view -> {

            fab.setEnabled(false);
            showCreateDialog();

            refresh();
        });

        refresh();

    }





    public void refresh(){
        fab.setEnabled(true);
        List<Course> courses = Course.listAll(Course.class);
        adapter = new CourseAdapter(courses);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showCreateDialog(){
        CreateCourseDialog.display(getSupportFragmentManager());
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
    public void onItemClick(View childView, int position) {
        int id = (int) adapter.getItemId(position);
        Intent intent = new Intent(CourseActivity.this, StudentActivity.class);
        intent.putExtra("courseId", id);

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
        CourseAdapter.CourseViewHolder holder = (CourseAdapter.CourseViewHolder)rv.getChildViewHolder(view);
        int position = holder.getAdapterPosition();

        int id = (int) adapter.getItemId(position);

        contextMenu.add(1,id, 0, "Bearbeiten").setOnMenuItemClickListener(menuItem -> {
            EditCourseDialog.display(getSupportFragmentManager(), menuItem.getItemId());
            return true;
        });
        contextMenu.add(2,id, 0, "Löschen").setOnMenuItemClickListener(menuItem -> {
            Course course = Course.findById(Course.class, menuItem.getItemId());
            course.delete();
            refresh();
            return true;
        });
    }
}