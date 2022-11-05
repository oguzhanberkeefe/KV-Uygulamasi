package com.example.kv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TemelUygulamaSecimActivity2 extends AppCompatActivity {

    private FirebaseAuth mYetki;

    private Button kahvesiparisiver, marketsiparisiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama_secim);

        mYetki = FirebaseAuth.getInstance();

        kahvesiparisiver = findViewById(R.id.kahvesiparisiver);
        marketsiparisiver = findViewById(R.id.marketsiparisiver);

        kahvesiparisiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SubsActivityeGonder = new Intent(TemelUygulamaSecimActivity2.this, SplashScreenSubActivity.class);
                startActivity(SubsActivityeGonder);
            }
        });

        marketsiparisiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SubsActivityeGonder2 = new Intent(TemelUygulamaSecimActivity2.this, SplashScreenSubActivity.class);
                startActivity(SubsActivityeGonder2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.secenekler_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.ana_cikis_secenegi) {
            mYetki.signOut();
            Intent giris = new Intent(TemelUygulamaSecimActivity2.this, LoginActivity.class);
            startActivity(giris);
        }

        return true;
    }


}