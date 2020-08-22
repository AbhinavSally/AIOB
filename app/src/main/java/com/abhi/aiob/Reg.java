package com.abhi.aiob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Reg extends AppCompatActivity {
    EditText e1,e2;
    Button b;
    FirebaseAuth f;
    AdView mAdView;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        MobileAds.initialize(this,"ca-app-pub-8870323806538407/3815539002");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
t=findViewById(R.id.textView39);
        f=FirebaseAuth.getInstance();
        e1=findViewById(R.id.editText13);
        e2=findViewById(R.id.editText14);
        b=findViewById(R.id.button13);
t.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Reg.this,MainActivity.class);
        startActivity(intent);
    }
});

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s1=e1.getText().toString();
                    String s2=e2.getText().toString();
                   if(s1.isEmpty()||s2.isEmpty())
                   {
                       Toast.makeText(Reg.this, "Kindly fill all the fields", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       ProgressDialog.show(Reg.this,"","Please Wait");
                       reg(s1,s2);

                   }

                }
            });   

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();

    }

    public void reg( String email,String passsword)
    {
        f.createUserWithEmailAndPassword(email, passsword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            Intent i=new Intent(Reg.this,KhaJana.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(Reg.this, "welcome", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Reg.this, "please try again", Toast.LENGTH_SHORT).show();
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

