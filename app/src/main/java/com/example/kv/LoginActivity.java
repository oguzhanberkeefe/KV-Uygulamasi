package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextSifre;

    private Button girisyap;

    private CheckBox sifreyigor;

    private ImageView facebook, google;

    private FirebaseUser mevcutKullanici;

    private FirebaseAuth maAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        maAuth = FirebaseAuth.getInstance();

        girisyap = (Button) findViewById(R.id.girisyap);

        girisyap.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);

        editTextSifre = (EditText) findViewById(R.id.edttxtsifre);

        sifreyigor = (CheckBox) findViewById(R.id.sifregoster);

        facebook = (ImageView) findViewById(R.id.facebook);

        google = (ImageView) findViewById(R.id.google);

        progressDialog = new ProgressDialog(LoginActivity.this);


        sifreyigor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    editTextSifre.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    editTextSifre.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.girisyap:
                userLogin();
                break;
        }

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String sifre = editTextSifre.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email boş olamaz!");
            editTextEmail.requestFocus();
            return;
        }

        if (sifre.isEmpty()) {
            editTextSifre.setError("Sifre boş olamaz!");
            editTextSifre.requestFocus();
            return;
        }

        if (sifre.length() < 8) {
            editTextSifre.setError("Sifreniz minimum 8 karakter olmalıdır!");
            editTextSifre.requestFocus();
            return;
        }

        maAuth.signInWithEmailAndPassword(email, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Giriş Başarılı", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, SplashScreenTelefonToSub.class));

                } else {
                    startActivity(new Intent(LoginActivity.this, SplashScreenLoginFailed.class));
                    Toast.makeText(LoginActivity.this, "Giriş başarısız lütfen tekrar deneyiniz!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}