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

public class EditStudentDialog extends DialogFragment {
    public static final String TAG = "edit_schueler_Dialog";

    private androidx.appcompat.widget.Toolbar toolbar;

    private Student student;

    private FloatingActionButton fab;

    private TextInputEditText vorname;
    private TextInputEditText nachname;
    private TextInputEditText email;

    public static void display(FragmentManager fragmentManager, int id){
        EditStudentDialog dialog = new EditStudentDialog(id);
        dialog.show(fragmentManager, TAG);
    }

    public EditStudentDialog(int id){
        super();
        student = Student.findById(Student.class, id);
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
        vorname = view.findViewById(R.id.studentDialog_vorname);
        nachname = view.findViewById(R.id.studentDialog_nachname);
        email = view.findViewById(R.id.studentDialog_email);

        vorname.setText(student.getVorname());
        nachname.setText(student.getNachname());
        email.setText(student.getEmail());

        fab.setOnClickListener(view1 -> save());

        toolbar = view.findViewById(R.id.toolbar);

        return view;
    }

    public void save(){
        student.setVorname(vorname.getText().toString());
        student.setNachname(nachname.getText().toString());
        student.setEmail(email.getText().toString());
        student.save();
        ((StudentActivity)getActivity()).refresh();
        dismiss();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Schueler bearbeiten");
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }




}
