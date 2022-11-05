package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StandartKUygulamasi extends AppCompatActivity {

    private FirebaseUser mevcutKullanici;
    private FirebaseAuth mYetki;

    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private SekmeErisimAdapter mySekmeErisimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standart_kuygulamasi);

        mYetki=FirebaseAuth.getInstance();

        mToolbar = findViewById(R.id.standart_K_Uygulamasi_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("K");

        myViewPager=findViewById(R.id.standart_ana_sekmeler_pager);
        mySekmeErisimAdapter=new SekmeErisimAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(mySekmeErisimAdapter);

        myTabLayout=findViewById(R.id.standart_ana_sekmeler);
        myTabLayout.setupWithViewPager(myViewPager);
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
            Intent giris = new Intent(StandartKUygulamasi.this,LoginActivity.class);
            startActivity(giris);
        }

        return true;
    }
}