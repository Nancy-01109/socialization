package com.example.nick.Themes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nick.Information.InformationActivity;
import com.example.nick.R;
import com.example.nick.Register.RegisterDatabase;

import java.util.ArrayList;

public class ThemeActivity extends AppCompatActivity implements ThemeAdapter.OnItemClicked {
    RegisterDatabase tb;
    ArrayList<String> name;
    ArrayList<Integer> id;
    ThemeAdapter themeAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        recyclerView= findViewById(R.id.rv);
        tb=new RegisterDatabase(ThemeActivity.this);
        id= new ArrayList<>();
        name= new ArrayList<>();

//        tb.InsertTheme(new Theme("Како да се запознаеш со луѓе",1));
//       tb.InsertTheme(new Theme("Што да кажеш ако некој почине",0));

        storeDatainArrays();
        themeAdapter=new ThemeAdapter(ThemeActivity.this, id, name);
        
        recyclerView.setAdapter(themeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ThemeActivity.this));

        themeAdapter.setOnClick(ThemeActivity.this);
    }




    void storeDatainArrays(){
        Cursor cursor=tb.readALLData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                id.add(cursor.getInt(0));
                name.add(cursor.getString(1));

            }
        }
    }


    @Override
    public void OnItemClicked(int position) {
        Intent intent= new Intent(ThemeActivity.this,InformationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt( "idTheme",id.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}