package com.makaryo.ululmusthofa.admin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makaryo.ululmusthofa.R;
import com.makaryo.ululmusthofa.admin.FirebaseGallery.ImagesActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AdmGallery extends AppCompatActivity {


    private  final int PICK_IMAGE_REQUEST =71 ;
    private ImageView imageView;

    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_gallery);
        imageView = (ImageView) findViewById(R.id.imgfire);
        storage = FirebaseStorage.getInstance();

        storageReference = storage.getReference();
         }


        public void onClick1(View v) {
            chooseImage();
        }


        public void onClick2(View v) {
            uploadimg();
  /*         //instansiasi edittext
    EditText jdl=(EditText) findViewById(R.id.judul);
    EditText jdl2=(EditText) findViewById(R.id.judul2);
            //instansiasi database firebase
        //    FirebaseDatabase database = FirebaseDatabase.getInstance();

            //Referensi database yang dituju
          DatabaseReference myRef = database.getReference("Image").child(jdl.getText().toString());

            //memberi nilai pada referensi yang dituju
            myRef.child("Url").setValue(FilePath);

*/






        }



    //onklik browse
        private void chooseImage() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }

        //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //upload
    private void uploadimg() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)


                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AdmGallery.this, "Uploaded", Toast.LENGTH_SHORT).show();
                           //Get Url Download

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    TextView text1=(EditText) findViewById(R.id.inputtxt) ;
                                    DatabaseReference imagestore=FirebaseDatabase.getInstance().getReference("uploads").child(UUID.randomUUID().toString());//ganti img dengan text


                                    //HashMap<String,String> hashMap = new HashMap<>();
                                    //hashMap.put("imageurl",String.valueOf(uri));
                                    imagestore.child("name").setValue(text1.getText().toString());
                                    imagestore.child("imageUrl").setValue(String.valueOf(uri)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(AdmGallery.this, "Final ", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(AdmGallery.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
//onclick gallery
    public void gallery(View v){
        Intent i = new Intent(AdmGallery.this, ImagesActivity.class);
        startActivity(i);

    }



}
