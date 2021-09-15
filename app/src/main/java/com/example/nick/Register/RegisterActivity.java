package com.example.nick.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nick.SignIN.LogInActivity;
import com.example.nick.R;

public class RegisterActivity extends AppCompatActivity {

    EditText name,surname,em_contact,password,email;
    Button register;
    Button logIn;
    RegisterDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name=findViewById(R.id.user_name);
        surname=findViewById(R.id.user_Surname);
        em_contact=findViewById(R.id.user_emergencyNumber);
        email=findViewById(R.id.user_email);
        password=findViewById(R.id.user_password);
        register=findViewById(R.id.btn_register);
         database = new RegisterDatabase(this);
        logIn= findViewById(R.id.btn_login);


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=name.getText().toString();
                String Surname=surname.getText().toString();
                String EmContact= em_contact.getText().toString();
                String Email= email.getText().toString();
                String Pass= password.getText().toString();
                String confirm_password = password.getText().toString();
                if(Email.equals("") || password.equals("") || confirm_password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(Pass.equals(confirm_password)){
                        Boolean checkEmail = database.CheckEmail(Email);
                        if(checkEmail == true){
                            User user = new User(Name,Surname,EmContact, Email,Pass);
                            Boolean insert = database.InsertUser(user);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                surname.setText("");
                                em_contact.setText("");
                                email.setText("");
                                password.setText("");

                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Email already taken", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }

           }



        });



    }

}

