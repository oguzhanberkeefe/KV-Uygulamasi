package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TemelUygulamaSecimActivity extends AppCompatActivity {

    private FirebaseUser mevcutKullanici;
    private FirebaseAuth mYetki;



    private Button TemelKUygulamasiGirisYap,TemelVUygulamasiGirisYap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temel_uygulama_secim);

        mYetki=FirebaseAuth.getInstance();

        TemelKUygulamasiGirisYap = findViewById(R.id.K_GirisYap);
        TemelVUygulamasiGirisYap = findViewById(R.id.V_GirisYap);

        TemelKUygulamasiGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TemelKUygulamasinaGonder = new Intent(TemelUygulamaSecimActivity.this, SplashScreenTemelKUygulamasi.class);
                startActivity(TemelKUygulamasinaGonder);
            }
        });

        TemelVUygulamasiGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TemelVUygulamasinaGonder = new Intent(TemelUygulamaSecimActivity.this, SplashScreenTemelZUygulamasi.class);
                startActivity(TemelVUygulamasinaGonder);
            }
        });
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
            Intent giris = new Intent(TemelUygulamaSecimActivity.this,LoginActivity.class);
            startActivity(giris);
        }

        return true;
    }
}