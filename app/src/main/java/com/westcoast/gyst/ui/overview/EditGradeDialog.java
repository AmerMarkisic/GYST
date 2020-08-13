package com.westcoast.gyst.ui.overview;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Grade;

public class EditGradeDialog extends DialogFragment {

    public static final String TAG = "edit_grade_Dialog";

    private androidx.appcompat.widget.Toolbar toolbar;

    private FloatingActionButton fab;

    private TextInputEditText beschreibung;
    private TextInputEditText note;
    private Grade grade;

    public EditGradeDialog(int gradeId) {
        super();
        grade = Grade.findById(Grade.class, gradeId);
    }

    public static EditGradeDialog display(FragmentManager fragmentManager, int gradeId){
        EditGradeDialog dialog = new EditGradeDialog(gradeId);
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
        View view = inflater.inflate(R.layout.grade_dialog, container, false);

        fab = view.findViewById(R.id.grade_create);
        beschreibung = view.findViewById(R.id.gradeDialog_beschreibung);
        note = view.findViewById(R.id.gradeDialog_note);
        
        beschreibung.setText(grade.getBeschreibung());
        note.setText(grade.getNote());

        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                save();
            }
        });

        toolbar = view.findViewById(R.id.toolbar);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void save(){
        grade.setBeschreibung(beschreibung.getText().toString());
        grade.setNote(note.getText().toString());
        grade.save();
        ((OverviewActivity)getActivity()).refreshView();
        dismiss();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Note bearbeiten");
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }

}
