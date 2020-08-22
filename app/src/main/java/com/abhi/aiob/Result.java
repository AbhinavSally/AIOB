package com.abhi.aiob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {
    DatabaseReference deta;
    AdView mAdView;
    Toolbar t;
    List<news> info;
    RecyclerView recyclerView;
    @Override
    protected void onStart() {
        super.onStart();
        deta.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                info.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    news khabar = ds.getValue(news.class);
                    info.add(khabar);
                }

                Adapter ad = new Adapter(Result.this, info);
                recyclerView.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        info = new ArrayList<>();
        deta = FirebaseDatabase.getInstance().getReference("newz");
        MobileAds.initialize(this, "ca-app-pub-8870323806538407/4708311670");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView=findViewById(R.id.recycle);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lm);

        t = findViewById(R.id.toolbar8);
        t.setTitle("Information Seeker");
        t.setTitleTextColor(Color.WHITE);
        t.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(t);
        t.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Result.this, KhaJana.class);
                startActivity(i);
            }
        });

        //  StorageReference storageRef=store.child("news");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
