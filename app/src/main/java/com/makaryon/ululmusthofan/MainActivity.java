package com.makaryon.ululmusthofan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.makaryon.ululmusthofan.admin.AdminLagu.ListLagu.ListLagu;
import com.makaryon.ululmusthofan.admin.AdminLagu.UploadLagu;
import com.makaryon.ululmusthofan.admin.FirebaseGallery.ImagesActivity;
import com.makaryon.ululmusthofan.admin.Login.Login;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


//load fragment



    /*
    public void login(View v)
    {
        Intent i = new Intent(MainActivity.this, Login.class);
        startActivity(i);
    }*/

    public void ListLagu(View view){
        Intent i= new Intent(MainActivity.this, ListLagu.class);

        startActivity(i);
    }


    public void admin(View view){
        Intent i= new Intent(MainActivity.this, Login.class);

        startActivity(i);
    }
    public void uplLagu(View view){
        Intent i= new Intent(MainActivity.this, UploadLagu.class);

        startActivity(i);
    }
    public void GaleriMain(View v)  {
        Intent i = new Intent(MainActivity.this, ImagesActivity.class);
        //Button b=(Button)findViewById(R.id.button1);

        //b.setBackground(getResources().getDrawable(R.drawable.buttonexit));

        startActivity(i);



    }
    public void exit(View v){


        finish();
        System.exit(0);
    }




}
