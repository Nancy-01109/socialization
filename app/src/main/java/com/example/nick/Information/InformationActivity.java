package com.example.nick.Information;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nick.R;
import com.example.nick.Register.RegisterDatabase;
import com.example.nick.Themes.ThemeActivity;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity {

    TextView steps, idInteest;
    RegisterDatabase tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforomation);
        steps = findViewById(R.id.steps);
        idInteest = findViewById(R.id.idInterest);
        tb = new RegisterDatabase(InformationActivity.this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int idTheme = bundle.getInt("idTheme");
            SharedPreferences prfs = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            Integer iduser = prfs.getInt("user", 0);
            int idInterest = tb.findIdInterest(iduser);
            idInteest.setText(String.valueOf(idInterest));
            steps.setText(tb.findSteps(idTheme));

            Toast.makeText(getApplicationContext(), "idTheme" + idTheme, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"idInterest"+ idInterest,Toast.LENGTH_SHORT).show();


        }


    }


}
