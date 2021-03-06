package com.westcoast.gyst.ui.overview;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.westcoast.gyst.R;
import com.westcoast.gyst.db.entities.Grade;

public class CreateGradeDialog extends DialogFragment {

    public static final String TAG = "create_grade_Dialog";

    private androidx.appcompat.widget.Toolbar toolbar;

    private FloatingActionButton fab;

    private TextInputEditText beschreibung;
    private TextInputEditText note;
    private int schuelerId;

    public CreateGradeDialog(int schuelerId) {
        super();
        this.schuelerId = schuelerId;
    }

    public static void display(FragmentManager fragmentManager, int schuelerId){
        CreateGradeDialog dialog = new CreateGradeDialog(schuelerId);
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



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.grade_dialog, container, false);

        fab = view.findViewById(R.id.grade_create);
        beschreibung = view.findViewById(R.id.gradeDialog_beschreibung);
        note = view.findViewById(R.id.gradeDialog_note);
        note.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL );

        fab.setOnClickListener(view1 -> save());

        toolbar = view.findViewById(R.id.toolbar);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void save(){
        if(beschreibung.getText().toString().equals("")
            || note.getText().toString().equals("")
            || Double.parseDouble(note.getText().toString()) < 1
            || Double.parseDouble(note.getText().toString()) > 6){
            Toast.makeText(getActivity(),
                    "Bitte überprüfen Sie Ihre Eingabe. Lassen Sie bitte keine Felder frei und tragen Sie eine Note von 1-6 ein!", Toast.LENGTH_SHORT).show();
            return;
        }
        Grade grade = new Grade(beschreibung.getText().toString(),note.getText().toString(), schuelerId);
        grade.save();
        ((OverviewActivity)getActivity()).refreshView();
        dismiss();
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Note hinzufügen");
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }



}
