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

public class CreateCourseDialog extends DialogFragment {
    public static final String TAG = "create_course_Dialog";

    private androidx.appcompat.widget.Toolbar toolbar;

    private FloatingActionButton fab;


    private TextInputEditText fach;
    private TextInputEditText klasse;
    private TextInputEditText zeit;




    public static void display(FragmentManager fragmentManager){
        CreateCourseDialog dialog = new CreateCourseDialog();
        dialog.show(fragmentManager, TAG);
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

        fab.setOnClickListener(view1 -> save());

        toolbar = view.findViewById(R.id.toolbar);

        return view;
    }

    public void save(){
        Course course = new Course(fach.getText().toString(), klasse.getText().toString(), zeit.getText().toString());
        course.save();
        ((CourseActivity)getActivity()).refresh();
        dismiss();
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Kurs erstellen");
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }




}
