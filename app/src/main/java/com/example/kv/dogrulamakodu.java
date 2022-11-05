package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class dogrulamakodu extends AppCompatActivity {


    public EditText edttxtdogrulama;
    public Button devametbutton1;
    private FirebaseAuth mAuth;
    TextView phonen;

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogrulamakodu);
        Intent intent2 = getIntent();
        String phone = intent2.getStringExtra("Key");
        edttxtdogrulama = (EditText) findViewById(R.id.edttxtdogrulama);
        devametbutton1 = (Button) findViewById(R.id.devamet1);
        phonen = (TextView) findViewById(R.id.phonenum);
        phonen.setText(phone);
        devametbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edttxtdogrulama.getText().toString())){
                    Toast.makeText(dogrulamakodu.this, "Lütfen Doğrulama Kodunu Giriniz!", Toast.LENGTH_SHORT).show();
                }
                else {
                    verfyCode(edttxtdogrulama.getText().toString());
                }

            }
        });
    }

    private void sendVerificationCode(String phone) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber((String) phonen.getText())
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallBack)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            edttxtdogrulama.setText(code);

            verfyCode(code);
        }

        @Override
        public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
            Toast.makeText(dogrulamakodu.this, e.getMessage(), Toast.LENGTH_SHORT).show();

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
                    Intent in = new Intent(dogrulamakodu.this,KayitActivity.class);
                    startActivity(in);
                    finish();
                }else {

                    Toast.makeText(dogrulamakodu.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}