package com.example.worldcinema;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class launch_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
       Thread splash = new Thread(){
           public void run(){
               try {
                   sleep(5*100);
                   Intent i = new Intent(getBaseContext(), signin_screen.class);
                   startActivity(i);
                   finish();
               }catch (Exception e){}
           }
       }; splash.start();
    }
}
