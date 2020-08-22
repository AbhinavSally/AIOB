package com.abhi.aiob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b;
    CheckBox c;
    TextView t, t1;
    FirebaseAuth f;
    SharedPreferences s;
    AdView mAdView;
    ProgressDialog pr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-8870323806538407/3494807517");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        pr=new ProgressDialog(MainActivity.this);
        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);
        c = findViewById(R.id.checkBox);
        b = findViewById(R.id.button);
        f = FirebaseAuth.getInstance();
        t = findViewById(R.id.textView13);
        t1 = findViewById(R.id.textView7);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Reg.class);
                startActivity(i);
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(p);
            }
        });
        s = getSharedPreferences("yo", MODE_PRIVATE);
        String st1 = s.getString("one", null);
        String st2 = s.getString("two", null);
        if (st1 != null && st2 != null) {

            Intent i = new Intent(MainActivity.this, KhaJana.class);
            startActivity(i);

            finish();
        }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if (s1.isEmpty() || s2.isEmpty()) {

                    Toast.makeText(MainActivity.this, "fields empty", Toast.LENGTH_SHORT).show();
                } else {
                    pr.setCancelable(false);
                    pr.setMessage("Loading...... ");
                    pr.show();

                    log(s1, s2);

                }
                if (c.isChecked()) {
                    SharedPreferences.Editor e = s.edit();
                    e.putString("one", s1);
                    e.putString("two", s2);
                    e.commit();

                }
            }
        });


    }

    public void log(String email, String password) {

        f.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "WELCOME", Toast.LENGTH_SHORT).show();


                            Intent i = new Intent(MainActivity.this, KhaJana.class);
                            startActivity(i);
                            finish();

                        } else {
                                pr.dismiss();
                            Toast.makeText(MainActivity.this, "password incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

