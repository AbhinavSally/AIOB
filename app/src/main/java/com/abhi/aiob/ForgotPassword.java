package com.abhi.aiob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText e;
    Button b;
    FirebaseAuth f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        e = findViewById(R.id.editText3);
        b = findViewById(R.id.button4);
        f = FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.sendPasswordResetEmail(e.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(ForgotPassword.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                    Toast.makeText(ForgotPassword.this, "Password reset email sent successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ForgotPassword.this, "Please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
