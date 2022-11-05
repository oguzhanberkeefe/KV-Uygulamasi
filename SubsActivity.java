package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SubsActivity extends AppCompatActivity {


    CardView subs1,subs2,subs3;

    private FirebaseUser mevcutKullanici;
    private FirebaseAuth mYetki;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subs);

        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.viewflipper);


        subs1 = (CardView) findViewById(R.id.subs1);
        subs2 = (CardView) findViewById(R.id.subs2);
        subs3 = (CardView) findViewById(R.id.subs3);

        subs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subs1intent = new Intent(SubsActivity.this,SplashScreenTemelUygulamaSecim.class);
                startActivity(subs1intent);
            }
        });

        subs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my2 = new Intent(SubsActivity.this,SplashScreenStandartUygulamaSecim.class);
                startActivity(my2);
            }
        });

        subs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my3 = new Intent(SubsActivity.this,SplashScreenOzelUygulamaSecim.class);
                startActivity(my3);
            }
        });

        flipper.setAutoStart(true);
        flipper.setInAnimation(getApplicationContext(),R.anim.giris);
        flipper.setOutAnimation(getApplicationContext(),R.anim.cikis);

        mYetki=FirebaseAuth.getInstance();
        mevcutKullanici=mYetki.getCurrentUser();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.secenekler_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId()==R.id.ana_cikis_secenegi)
        {
            mYetki.signOut();
            Intent giris = new Intent(SubsActivity.this,SplashScreenLogin.class);
            startActivity(giris);
        }

        return true;
    }
}