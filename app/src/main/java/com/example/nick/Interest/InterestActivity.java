package com.example.nick.Interest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nick.R;
import com.example.nick.Register.RegisterActivity;
import com.example.nick.Register.RegisterDatabase;
import com.example.nick.Register.User;
import com.example.nick.SignIN.LogInActivity;

public class InterestActivity extends AppCompatActivity {

    TextView inter,hobbies,books,tvshows;
    EditText inter_e,hobby_e,books_e,tv_show_e;
    Button change;
    RegisterDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        inter=findViewById(R.id.interests);
        hobbies=findViewById(R.id.hobby);
        tvshows=findViewById(R.id.tv_show);
        books=findViewById(R.id.books);

        inter_e=findViewById(R.id.interest_edit);
        hobby_e=findViewById(R.id.hobby_edit);
        tv_show_e=findViewById(R.id.tv_show_edit);
        books_e=findViewById(R.id.books_edit);

        database=new RegisterDatabase(this);

        change=findViewById(R.id.change_btn);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    String interest = inter_e.getText().toString();
                    String hobby = hobby_e.getText().toString();
                    String tvshows = tv_show_e.getText().toString();
                    String book = books_e.getText().toString();
//                    Bundle bundle = getIntent().getExtras();
//                    Integer iduser = bundle.getInt("user");


                    SharedPreferences prfs = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                    Integer iduser = prfs.getInt("user", 0);

                    if (interest.equals("") || hobby.equals("") || tvshows.equals("") || book.equals("")) {
                        Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                    } else {

                        Interest interest1 = new Interest(interest, hobby, tvshows, book, iduser);
                        Boolean insert = database.insertInterest(interest1);
                        if (insert == true) {
                            Toast.makeText(getApplicationContext(), "fuck yes", Toast.LENGTH_SHORT).show();
                            inter_e.setText("");
                            hobby_e.setText("");
                            tv_show_e.setText("");
                            books_e.setText("");


                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                        }

                    }
                }


            }
        });


    }
}
