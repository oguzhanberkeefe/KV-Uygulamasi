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

public class OzelUygulamaSecimActivity extends AppCompatActivity {

    private FirebaseUser mevcutKullanici;
    private FirebaseAuth mYetki;

    private Button OzelKUygulamasiGirisYap,OzelVUygulamasiGirisYap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozel_uygulama_secim);

        mYetki=FirebaseAuth.getInstance();

        OzelKUygulamasiGirisYap = findViewById(R.id.K_GirisYap);
        OzelVUygulamasiGirisYap = findViewById(R.id.V_GirisYap);

        OzelKUygulamasiGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StandartKUygulamasinaGonder = new Intent(OzelUygulamaSecimActivity.this, SplashScreenOzelKUygulamasi.class);
                startActivity(StandartKUygulamasinaGonder);
            }
        });

        OzelVUygulamasiGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StandartVUygulamasinaGonder = new Intent(OzelUygulamaSecimActivity.this, SplashScreenOzelVUygulamasi.class);
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
            Intent giris = new Intent(OzelUygulamaSecimActivity.this,LoginActivity.class);
            startActivity(giris);
        }

        return true;
    }
}