package com.example.worldcinema;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class signin_screen extends AppCompatActivity {
    public static ArrayList<user> Users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Users = new ArrayList<>();
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("Users.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (login TEXT, password TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM users;", null);
        while (query.moveToNext()) {
            String login = query.getString(0);
            String password = query.getString(1);
            Users.add(new user(login, password));
        }
        query.close();
        db.close();
    }

    public void reg (View view){
        Intent i = new Intent(this, signup_screen.class);
        startActivity(i);
        finish();
    }
    public void vxod (View view){
        Intent i = new Intent(this,Main_screen.class);
        startActivity(i);
        finish();
    }
}
