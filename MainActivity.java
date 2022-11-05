package com.example.kv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseUser mevcutKullanici;
    private FirebaseAuth mYetki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mYetki=FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();


        if (mevcutKullanici==null)
        {
            KullaniciyiLoginActivityeGonder();
        }
    }

    private void KullaniciyiLoginActivityeGonder()
    {
        Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }
}