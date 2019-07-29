package com.example.ululmusthofa.admin.AdminLagu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ululmusthofa.R;
import com.example.ululmusthofa.admin.AdminLagu.ListLagu.EditListLagu;
import com.example.ululmusthofa.admin.AdminLagu.ListLagu.ListLagu;
import com.example.ululmusthofa.admin.FirebaseGallery.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UploadLagu extends AppCompatActivity {
    private  final int PICK_IMAGE_REQUEST =81 ;
    private  final int PICK_IMAGE_REQUESTED =91 ;

    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath;
    private Uri filePath2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_lagu);

        storage = FirebaseStorage.getInstance();

        storageReference = storage.getReference();
    }

    public void onklikMp3(View view){selectMp3();}
    public void onklikpdf(View view){selectPdf();}
    public void onklikUpload(View view){uploadimg();}


    //onklik browse mp3
    private void selectMp3() {
        Intent intent = new Intent();
        //intent.setType("application/pdf");
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Audio"), PICK_IMAGE_REQUESTED);


    }


    //onklik browse pdf
    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Audio"), PICK_IMAGE_REQUEST);


    }

    //ubah status get mp3 & pdf

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUESTED && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            TextView t=(TextView) findViewById(R.id.notificationmp3);
            t.setText(filePath.toString());
        }
        else if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath2 = data.getData();
            TextView t=(TextView) findViewById(R.id.notificationpdf);
            t.setText(filePath2.toString());
        }
    }
    //upload
    private void uploadimg() {
        final String name;
        name =UUID.randomUUID().toString();
        //mp3 upload
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            //Random name

            final StorageReference ref = storageReference.child("Mp3/"+ name);
            ref.putFile(filePath)



                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadLagu.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            //Get Url Download
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    TextView t= (EditText) findViewById(R.id.JdlLagu);
                                    DatabaseReference imagestore=FirebaseDatabase.getInstance().getReference("ListLAgu").child(name);


                                    imagestore.child("JudulLagu").setValue(t.getText().toString());
                                    imagestore.child("Mp3Url").setValue(String.valueOf(uri)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(UploadLagu.this, "Final ", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadLagu.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });

        }

        //pdf upload

        if(filePath2 != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();



            final StorageReference ref = storageReference.child("Mp3/"+ UUID.randomUUID().toString());
            ref.putFile(filePath2)



                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadLagu.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            //Get Url Download
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    DatabaseReference imagestore=FirebaseDatabase.getInstance().getReference("ListLAgu").child(name);


                                    imagestore.child("PdfUrl").setValue(String.valueOf(uri)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(UploadLagu.this, "Final ", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadLagu.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });

        }










    }

    public void ListLagu(View view){
        Intent i=new Intent(UploadLagu.this, ListLagu.class);
        startActivity(i);
    }


}