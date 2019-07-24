package com.example.ululmusthofa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ululmusthofa.admin.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void login(View v)
    {
        Intent i = new Intent(MainActivity.this, Login.class);
        startActivity(i);
    }
    public void exit(View v){
        finish();
        System.exit(0);
    }

}
