package com.makaryon.ululmusthofan.admin.AdminLagu.ListLagu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.makaryon.ululmusthofan.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;

public class EditListLagu extends AppCompatActivity {
//percobaan
    private DatabaseReference database;

    // variable fields EditText dan Button

    private EditText etNama;
    private EditText etMp3;
    private EditText etPdf;
    private Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_lagu);


        etNama = findViewById(R.id.nama);
        etMp3 = findViewById(R.id.Mp3);
        etPdf = findViewById(R.id.Pdf);

  //      btSubmit = (Button) findViewById(R.id.submit);
    //    database = FirebaseDatabase.getInstance().getReference();
        final PojoLagu barang = (PojoLagu) getIntent().getSerializableExtra("date");

        etNama.setText(barang.getJudulLagu());
        etMp3.setText(barang.getMp3Url());
        etPdf.setText(barang.getPdfUrl());
/*
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                barang.setJudulLagu(etNama.getText().toString());
                barang.setMp3Url(etMp3.getText().toString());
                barang.setPdfUrl(etPdf.getText().toString());

                updateBarang(barang);
            }
        });*/


    }

    private void updateBarang(PojoLagu barang) {
        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("ListLAgu") //akses parent index, ibaratnya seperti nama tabel
                .child(barang.getKey()) //select barang berdasarkan key
                .setValue(barang) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */
                        Snackbar.make(findViewById(R.id.submit), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }


    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, EditListLagu.class);
    }
}
