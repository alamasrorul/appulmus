package com.example.ululmusthofa.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ululmusthofa.R;

public class Login extends AppCompatActivity {

    EditText id;
    EditText pasw;
    Button login;
    String inputan;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id=findViewById(R.id.username);
        pasw=findViewById(R.id.password);
    }
    public void loginadmin(View v)
    {

        inputan=id.getText().toString();
        password=pasw.getText().toString();


        if(inputan.equals("1")&& password.equals("1"))
        {
            Intent i = new Intent(Login.this, Admin.class);
            startActivity(i);

        }
        else
        {

            Toast.makeText(getApplicationContext(), "Password Atau Masukkan Salah ", Toast.LENGTH_SHORT).show();
        }
    }


}
