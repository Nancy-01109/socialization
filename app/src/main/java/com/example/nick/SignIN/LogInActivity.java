package com.example.nick.SignIN;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nick.Interest.InterestActivity;
import com.example.nick.R;
import com.example.nick.Register.RegisterActivity;
import com.example.nick.Register.RegisterDatabase;

public class LogInActivity extends AppCompatActivity {

    EditText LEmail,LPassword;
    Button LogIn,Register;
    RegisterDatabase database;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        LEmail= findViewById(R.id.log_Email);
        LPassword= findViewById(R.id.log_Password);
        database= new RegisterDatabase(this);
        LogIn= findViewById(R.id.btn_login);
        Register=findViewById(R.id.btn_register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= LEmail.getText().toString();
                String password= LPassword.getText().toString();
                Boolean checklogin = database.CheckLogin(email, password);
                if(checklogin){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogInActivity.this, InterestActivity.class);
//                    Bundle bundle = new Bundle();
                        Integer user= database.findId(email);
//                    bundle.putInt( "user",user);
//
//                    intent.putExtras(bundle);



                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                    SharedPreferences.Editor myEdit = sharedPreferences.edit();


                    myEdit.putInt("user", user);




                    myEdit.apply();

                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


            }

