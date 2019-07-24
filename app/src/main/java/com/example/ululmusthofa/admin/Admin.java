package com.example.ululmusthofa.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ululmusthofa.R;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    public void onklikGallery(View v){
        Intent i=new Intent(Admin.this,AdmGallery.class);
        startActivity(i);
    }
}
