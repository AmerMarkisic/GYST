package com.westcoast.gyst.ui.courses;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Course;

public class EditCourseDialog extends DialogFragment {
    public static final String TAG = "edit_course_Dialog";

    private androidx.appcompat.widget.Toolbar toolbar;

    private Course course;

    private FloatingActionButton fab;

    private TextInputEditText fach;
    private TextInputEditText klasse;
    private TextInputEditText zeit;

    public static EditCourseDialog display(FragmentManager fragmentManager, int id){
        EditCourseDialog dialog = new EditCourseDialog(id);
        dialog.show(fragmentManager, TAG);
        return dialog;
    }

    public EditCourseDialog(int id){
        super();
        course = Course.findById(Course.class, id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart(){
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.course_dialog, container, false);

        fab = view.findViewById(R.id.course_create);
        fach = view.findViewById(R.id.courseDialog_fach);
        klasse = view.findViewById(R.id.courseDialog_klasse);
        zeit = view.findViewById(R.id.courseDialog_zeit);

        fach.setText(course.getFach());
        klasse.setText(course.getKlasse());
        zeit.setText(course.getZeit());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        toolbar = view.findViewById(R.id.toolbar);

        return view;
    }

    public void save(){
        course.setFach(fach.getText().toString());
        course.setKlasse(klasse.getText().toString());
        course. setZeit(zeit.getText().toString());
        course.save();
        ((CourseActivity)getActivity()).refresh();
        dismiss();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Kurs bearbeiten");
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }




}
