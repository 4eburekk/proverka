package com.example.worldcinema;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class signup_screen extends AppCompatActivity {
    final int error = 1;
    final int errorpas = 2;
    final int error_email = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinup);
    }
    public void back(View view){
        Intent i = new Intent(this,signin_screen.class);
        startActivity(i);
        finish();
    }
    public void registrationCLICK(View view){
        EditText name = findViewById(R.id.editTextTextPersonName);
        EditText fam = findViewById(R.id.editTextTextPersonName2);
        EditText pas = findViewById(R.id.editTextTextPassword3);
        EditText pass = findViewById(R.id.editTextTextPassword2);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        String emailt = email.getText().toString();
        String namet = name.getText().toString();
        String famt = fam.getText().toString();
        String past = pas.getText().toString();
        String passt = pass.getText().toString();
        String mail = "@";
        String ru = ".ru";
        String com = "com";
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        int Number = emailt.indexOf(mail);
        int ruru = emailt.indexOf(ru);
        int comcom = emailt.indexOf(com);
        boolean resultdog = emailt.contains("@");
        boolean result_ru = emailt.contains(".ru");
        boolean result_com = emailt.contains(".com");
        if(namet.equals("")|| past.equals("") || passt.equals("") || emailt.equals("") || famt.equals("")){
            showDialog(error);
            return;
        }
        else if (past.equals(passt) != true){
            showDialog(errorpas);
            return;
        }else if (emailt.contains("@") != false){
            if(emailt.contains(".ru") !=true && emailt.contains(".com") != true){
                showDialog(error_email);
                return;
            }
        }else if(emailt.contains("@") != true){
            showDialog(error_email);
            return;
        }
        boolean found = false;
        for (user item : signin_screen.Users) {
            if (emailt.equals(item.login)) {
                found = true;
                alert.setTitle("Уже существует");
                alert.setMessage("Пользователь существует");
                alert.show();
            }
        }
        if (!found) {
            Save(emailt, past);
            alert.setMessage("Успешно зарегистирован пользователь");
            alert.setTitle("OK");
            alert.setPositiveButton("Вернуться в меню", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent intent = new Intent(signup_screen.this, signin_screen.class);
                    startActivity(intent);
                    signup_screen.this.finish();
                }
            });
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Intent intent = new Intent(signup_screen.this, signin_screen.class);
                    startActivity(intent);
                    signup_screen.this.finish();
                }
            });
            alert.show();
        }
    }
    protected Dialog onCreateDialog (int id){
        if(id == error){
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
            alertdialog.setTitle(R.string.error);
            alertdialog.setMessage(R.string.fio);
            alertdialog.setNeutralButton(R.string.back, myClick);
            return alertdialog.create();
        }else if(id == errorpas){
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
            alertdialog.setTitle(R.string.error);
            alertdialog.setMessage(R.string.pass);
            alertdialog.setNeutralButton(R.string.back, myClick);
            return alertdialog.create();
        }else if(id == error_email){
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
            alertdialog.setTitle(R.string.error);
            alertdialog.setMessage(R.string.mail);
            alertdialog.setNeutralButton(R.string.back, myClick);
            return alertdialog.create();
        }
        return super.onCreateDialog(id);
    }
    DialogInterface.OnClickListener myClick = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int slovo) {
            switch (slovo){
                case Dialog.BUTTON_NEUTRAL:

                    break;
            }
        }
    };
    public void Save(String login, String password)
    {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("Users.db", MODE_PRIVATE, null);
        ContentValues cv = new ContentValues();
        cv.put("login", login);
        cv.put("password", password);

        db.insert("users", null, cv);
        db.close();
        signin_screen.Users.add(new user(login, password));
    }
}
