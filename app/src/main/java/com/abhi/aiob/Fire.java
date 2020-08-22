package com.abhi.aiob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class Fire extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    EditText e1, e2, e3, e4;
    Button b;
    Uri u;
    Bitmap photo;
    DatePicker d;
    DatabaseReference deta;
    String ss, sti,s1,s2,s3,s4;
    AdView mAdView;
    ImageView img;
    Toolbar t;
    StorageReference store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        MobileAds.initialize(this, "ca-app-pub-8870323806538407/1714821980");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        store = FirebaseStorage.getInstance().getReference("newz");
        t = findViewById(R.id.toolbar3);
        t.setTitle("Fire");
        t.setTitleTextColor(Color.WHITE);
        t.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(t);
        t.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fire.this, Opt.class);
                startActivity(i);
            }
        });
        e1 = findViewById(R.id.frrplace);

        store = FirebaseStorage.getInstance().getReference();
        e2 = findViewById(R.id.frrdied);
        e3 = findViewById(R.id.frrinjured);
        e4 = findViewById(R.id.frrdesc);

        b = findViewById(R.id.frrb);
        d = findViewById(R.id.frrd);
        d.setVisibility(View.GONE);
        img = findViewById(R.id.frrimg);
        deta = FirebaseDatabase.getInstance().getReference("newz");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload();

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == CAMERA_REQUEST) {
                photo = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(photo);
                u = data.getData();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Choose Image first", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    public void Upload() {
        if (photo != null  ) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            final StorageReference sto = store.child("newz/" + System.currentTimeMillis() + ".JPG");
            UploadTask task = sto.putBytes(data);
            task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return sto.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Fire.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                        sti = String.valueOf(task.getResult());
                         s1 = e1.getText().toString();
                         s3 = e3.getText().toString();
                         s2 = e2.getText().toString();
                         s4 = e4.getText().toString();
                        String s = d.getDayOfMonth() + "/" + d.getMonth()+1 + "/" + d.getYear();
                        ss = s1 + " under fire " + s2 + " died " + s3 + " injured on " + s + " as " + s4;

                        String id = deta.push().getKey();
                        news khabar = new news(id, ss, sti);
                        deta.child(id).setValue(khabar);
                        Intent i = new Intent(Fire.this, Result.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Kindly fill all the fields!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
