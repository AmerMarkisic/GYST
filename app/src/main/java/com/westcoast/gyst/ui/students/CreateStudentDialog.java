package com.westcoast.gyst.ui.students;

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
import com.westcoast.gyst.db.entities.Student;

public class CreateStudentDialog extends DialogFragment {
    public static final String TAG = "create_student_Dialog";

    private androidx.appcompat.widget.Toolbar toolbar;

    private FloatingActionButton fab;

    private int courseId;

    private TextInputEditText vorname;
    private TextInputEditText nachname;
    private TextInputEditText email;


    public CreateStudentDialog(int courseId){
        super();
        this.courseId = courseId;
    }


    public static CreateStudentDialog display(FragmentManager fragmentManager, int courseId){
        CreateStudentDialog dialog = new CreateStudentDialog(courseId);
        dialog.show(fragmentManager, TAG);
        return dialog;
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
        View view = inflater.inflate(R.layout.student_dialog, container, false);

        fab = view.findViewById(R.id.student_create);
        vorname = view.findViewById(R.id.student_vorname);
        nachname = view.findViewById(R.id.student_nachname);
        email = view.findViewById(R.id.student_email);

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
        Student student = new Student(vorname.getText().toString(), nachname.getText().toString(), email.getText().toString());
        student.setCourseId(this.courseId);
        student.save();
        ((StudentActivity)getActivity()).refresh();
        dismiss();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Schueler erstellen");
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }
}
