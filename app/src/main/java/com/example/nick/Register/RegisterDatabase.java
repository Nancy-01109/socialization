package com.example.nick.Register;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nick.Information.Information;
import com.example.nick.Interest.Interest;
import com.example.nick.Questions.Question;
import com.example.nick.Themes.Theme;

import java.util.ArrayList;

public class RegisterDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "aplication.db";
    public static final int DATABASE_VERSION = 3;


    public RegisterDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , surname TEXT, emergencyContact TEXT, email TEXT, password TEXT)");
        db.execSQL("CREATE TABLE interests(id INTEGER PRIMARY KEY AUTOINCREMENT, inter TEXT , hobby TEXT, tvshow TEXT, book TEXT, iduser INTEGER, FOREIGN KEY(iduser) REFERENCES user(id))");
        db.execSQL("CREATE TABLE theme(idTheme INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT,type INTEGER)");
        db.execSQL("CREATE TABLE information(id INTEGER PRIMARY KEY AUTOINCREMENT , steps TEXT , idThemes INTEGER,idInterest INTEGER, FOREIGN KEY(idThemes) REFERENCES theme(idTheme), FOREIGN KEY(idInterest) REFERENCES interests(id))");
        db.execSQL("CREATE TABLE questions(id INTEGER PRIMARY KEY AUTOINCREMENT, quest TEXT )");
        db.execSQL("CREATE TABLE infoquestions(id INTEGER PRIMARY KEY AUTOINCREMENT, idQuestion INTEGER, idInformation INTEGER,)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS interests");
        db.execSQL("DROP TABLE IF EXISTS theme");
        db.execSQL("DROP TABLE IF EXISTS information");
        db.execSQL("DROP TABLE IF EXISTS questions");
        onCreate(db);

    }

    public boolean InsertUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("surname", user.getSurname());
        contentValues.put("emergencyContact", user.getEmergencyContact());
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());
        long result = sqLiteDatabase.insert("user", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean InsertUserGoogle(User user)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("surname", user.getSurname());
        contentValues.put("emergencyContact", user.getEmergencyContact());
        contentValues.put("email", user.getEmail());
        long result = sqLiteDatabase.insert("user", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<User> listUsers(){
        String sql = "select * from " + "user";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String emergencyContact = cursor.getString(3);
                String  email=cursor.getString(4);
                String password= cursor.getString(5);
                users.add(new User(name,  surname, emergencyContact, email, password));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public Boolean CheckEmail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }


    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("surname", user.getSurname());
        values.put("emergencyContact", user.getEmergencyContact());
        values.put("password", user.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update( "user",values, "email" + "	= ?", new String[]{user.getEmail()});

    }

    public User findUser(String email) {
        String query = "Select * FROM " + "user" + " WHERE " + "email" + " = " + "email";
        SQLiteDatabase db = this.getWritableDatabase();
        User user = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            Integer id= cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String emergencyContact = cursor.getString(3);

            user = new User(name,  surname, emergencyContact,id);
        }
        cursor.close();
        return user;
    }

    public Integer findId(String email){
        String query = "Select * FROM " + "user" + " WHERE " + "email" + " = " + "email";
        SQLiteDatabase db = this.getWritableDatabase();
        int id1=0;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            id1=id;
        }
        cursor.close();
        return id1;

    }

    public void deleteUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user", "email" + "	= ?", new String[]{String.valueOf(email)});
    }




    public Boolean CheckLogin(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=? AND password=?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean InsertTheme(Theme theme) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",theme.getName());
        contentValues.put("type",theme.getType());
        long result = sqLiteDatabase.insert("theme", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }




    public ArrayList<Theme> listTheme(){
        String sql = "select * from " + "theme";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Theme> themes = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(1);
                themes.add(new Theme(name));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return themes;

    }

    public boolean insertInterest(Interest interest) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inter", interest.getInterest());
        contentValues.put("hobby", interest.getHobby());
        contentValues.put("tvshow", interest.getTvshow());
        contentValues.put("book", interest.getBooks());
        contentValues.put("iduser", interest.getIduser());
        long result = sqLiteDatabase.insert("interests", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Integer findIdInterest(Integer iduser){
        String query = "Select * FROM " + "interests" + " WHERE " + "iduser" + " = " + iduser;
        SQLiteDatabase db = this.getWritableDatabase();
        int id1=0;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            id1=id;
        }
        cursor.close();
        return id1;

    }



    public Cursor readALLData(){
        String sql = "select * from " + "theme";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=null;
        if(db!= null){
            cursor = db.rawQuery(sql,null);

        }
        return cursor;
    }


    public boolean InsertInformation(Information information) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("steps",information.getSteps());
        contentValues.put("idThemes",information.getIdTheme());
        contentValues.put("idInterest", information.getIdInterest());
        long result = sqLiteDatabase.insert("information", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readALLDataInfo(){
        String sql = "select * from " + "information";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=null;
        if(db!= null){
            cursor = db.rawQuery(sql,null);

        }
        return cursor;
    }

    public String findSteps(Integer idThemes){
        String query = "Select * FROM " + "information" + " WHERE " + "idThemes" + " = " + idThemes;
        SQLiteDatabase db = this.getWritableDatabase();
        String step="";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String steps = cursor.getString(1);
            step=steps;

        }
        cursor.close();
        return step;

    }


    public boolean InsertQuestions(Question question){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quest", question.getDescription());
        long result = sqLiteDatabase.insert("questions", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }




}
