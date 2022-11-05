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

public class StandartUygulamaSecimActivity extends AppCompatActivity {

    private FirebaseUser mevcutKullanici;
    private FirebaseAuth mYetki;

    private Button StandartKUygulamasiGirisYap,StandartVUygulamasiGirisYap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standart_uygulama_secim);

        mYetki=FirebaseAuth.getInstance();

        StandartKUygulamasiGirisYap = findViewById(R.id.K_GirisYap);
        StandartVUygulamasiGirisYap = findViewById(R.id.V_GirisYap);

        StandartKUygulamasiGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StandartKUygulamasinaGonder = new Intent(StandartUygulamaSecimActivity.this, SplashScreenStandartKUygulamasi.class);
                startActivity(StandartKUygulamasinaGonder);
            }
        });

        StandartVUygulamasiGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StandartVUygulamasinaGonder = new Intent(StandartUygulamaSecimActivity.this, SplashScreenStandartVUygulamasi.class);
                startActivity(StandartVUygulamasinaGonder);
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
            Intent giris = new Intent(StandartUygulamaSecimActivity.this,LoginActivity.class);
            startActivity(giris);
        }

        return true;
    }
}