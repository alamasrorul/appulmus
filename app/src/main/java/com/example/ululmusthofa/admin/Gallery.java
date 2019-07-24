package com.example.ululmusthofa.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ululmusthofa.R;

public class Gallery extends AppCompatActivity implements OnItemClickListener{

    //Deklarasi Variable GridView
    protected GridView TampilanGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        //getSupportActionBar().setTitle("Image Gallery");
        TampilanGrid = findViewById(R.id.gridview);
        //Mengset/Menerapkan adapter pada GridView
        TampilanGrid.setAdapter(new ImageAdapter(this));

        //Membuat Listener untuk menangani kejadian saat salah satu item di dalam GrdiView diklik
        TampilanGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView adapterView, View view, int position, long l) {
        Toast.makeText(Gallery.this, "Posisi yan diklik : "+position, Toast.LENGTH_SHORT).show();
        Intent nextPage = new Intent(this, ViewGambar.class);
        Bundle bundle = new Bundle();
        //Menyimpan nilai dari padameter position kedalah key posisi.
        //Yang akan dikirimkan pada Activiy ViewGambar
        bundle.putInt("posisi", position);
        nextPage.putExtras(bundle);
        startActivity(nextPage);
    }
}