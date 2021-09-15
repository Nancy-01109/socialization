package com.example.nick.SignIN;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nick.R;
import com.example.nick.Register.RegisterDatabase;
import com.example.nick.Register.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignInActivity extends AppCompatActivity  {

    ImageView imageView;
    TextView name, email;
    TextView emContact;
    Button signOut;
    Button EMContact;
    GoogleSignInClient mGoogleSignInClient;
    RegisterDatabase database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        database= new RegisterDatabase(this);
        emContact = findViewById(R.id.GoogleEmContact);
        signOut = findViewById(R.id.SignOut);
        EMContact = findViewById(R.id.GEButton);





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.SignOut:
                        signOut();
                        break;
                }

            }
        });

        EMContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogButtonClicked(view);
            }
        });


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String personName = account.getDisplayName();
            String Email = account.getEmail();
            Uri personPhoto = account.getPhotoUrl();
            name.setText(personName);
            email.setText(Email);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);


        }


    }
    public void showAlertDialogButtonClicked(View view)
    {

        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);
        builder.setTitle("Name");

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.alert_emcontact,
                        null);
        builder.setView(customLayout);

        // add a button
        builder
                .setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which)
                            {

                                // send data from the
                                // AlertDialog to the Activity
                                EditText editText
                                        = customLayout
                                        .findViewById(
                                                R.id.GoogleEmNumber);
                                sendDialogDataToActivity(
                                        editText
                                                .getText()
                                                .toString());
                            }
                        });

        // create and show
        // the alert dialog
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }

    // Do something with the data
    // coming from the AlertDialog
    private void sendDialogDataToActivity(String data)
    {
        emContact.setText(data);
        InsertInfo();



    }

    private void InsertInfo(){

        String[] names= name.getText().toString().split(" ");

        String Name=names[0];
        String Surname=names[1];
        String EmContact= emContact.getText().toString();
        String GEmail= email.getText().toString();

        if(GEmail.equals("") ){
            Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
        }else{

            Boolean checkEmail = database.CheckEmail(GEmail);
            if(checkEmail){
                User user = new User(Name,Surname,EmContact, GEmail);
                Boolean insert = database.InsertUserGoogle(user);
                if(insert){
                    Toast.makeText(getApplicationContext(), "In database", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Email already taken", Toast.LENGTH_SHORT).show();
            }

        }
    }


    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SignInActivity.this, "Signed out success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }




}
