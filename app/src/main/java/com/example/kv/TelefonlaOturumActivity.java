package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.annotations.NotNull;


import java.util.concurrent.TimeUnit;

public class TelefonlaOturumActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private ProgressDialog YuklemeBar;

    private Button DogrulamaKoduGondermeButtonu, DogrulaButtonu;
    private EditText TelefonNumarasıGirdisi, DogrulamaKoduGirdisi;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefonla_oturum);

        mAuth=FirebaseAuth.getInstance();
        TelefonNumarasıGirdisi = findViewById(R.id.telefon_numarasi_girdi);
        DogrulamaKoduGirdisi = findViewById(R.id.dogrulama_kodu_girdisi);
        DogrulaButtonu = findViewById(R.id.dogrulama_buttonu);
        DogrulamaKoduGondermeButtonu = findViewById(R.id.dogrulama_kodu_gonder_buttonu);

        DogrulamaKoduGondermeButtonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(TelefonNumarasıGirdisi.getText().toString())){
                    Toast.makeText(TelefonlaOturumActivity.this, "Lütfen Telefon Numarası Giriniz!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String phone = TelefonNumarasıGirdisi.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });

        DogrulaButtonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(DogrulamaKoduGirdisi.getText().toString())){
                    Toast.makeText(TelefonlaOturumActivity.this, "Lütfen Doğrulama Kodunu Giriniz!", Toast.LENGTH_SHORT).show();
                }
                else {
                    verfyCode(DogrulamaKoduGirdisi.getText().toString());
                }

            }
        });
    }

    private void sendVerificationCode(String phone) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallBack)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

            DogrulamaKoduGondermeButtonu.setVisibility(View.INVISIBLE);
            DogrulaButtonu.setVisibility(View.VISIBLE);

            TelefonNumarasıGirdisi.setVisibility(View.INVISIBLE);
            DogrulamaKoduGirdisi.setVisibility(View.VISIBLE);

            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            DogrulamaKoduGirdisi.setText(code);

            verfyCode(code);
        }

        @Override
        public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {

            DogrulamaKoduGondermeButtonu.setVisibility(View.VISIBLE);
            DogrulaButtonu.setVisibility(View.INVISIBLE);

            TelefonNumarasıGirdisi.setVisibility(View.VISIBLE);
            DogrulamaKoduGirdisi.setVisibility(View.VISIBLE);

            Toast.makeText(TelefonlaOturumActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };

    private void verfyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        SignInWithCreditial(credential);
    }

    private void SignInWithCreditial(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent in = new Intent(TelefonlaOturumActivity.this,SubsActivity.class);
                    startActivity(in);
                    finish();
                }else {

                    Toast.makeText(TelefonlaOturumActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}