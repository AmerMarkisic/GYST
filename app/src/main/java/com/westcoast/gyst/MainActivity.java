package com.westcoast.gyst;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.orm.SugarContext;
import com.westcoast.gyst.db.Course;
import com.westcoast.gyst.db.CourseAdapter;
import com.westcoast.gyst.infrastructure.RecyclerItemClickListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener,View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener{

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        SugarContext.init(this);

        rv = (RecyclerView)findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateDialog();
            }
        });

        refresh();

    }



    public void refresh(){
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
        CourseDialog.display(getSupportFragmentManager());
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
        registerForContextMenu(childView);
        openContextMenu(childView);
        unregisterForContextMenu(childView);
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getGroupId() == 2){
            Course course = Course.findById(Course.class, menuItem.getItemId());
            course.delete();
            refresh();
            return true;
        } if(menuItem.getGroupId() == 1){
            //((MainActivity)(AppCompatActivity)context).showCreateDialog();
            return true;
        }
        return false;
    }


    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        TextView tv = view.getRootView().findViewById(R.id.kurs_id);
        int id = Integer.parseInt(tv.getText().toString());

        contextMenu.add(1,id, 0, "Bearbeiten");
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