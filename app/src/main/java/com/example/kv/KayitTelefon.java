package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.mukesh.OtpView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class KayitTelefon extends AppCompatActivity {

    OtpView edittxtdogrulama;
    TextView phonenum, dogrulamakodunuasagidakialana, none;
    Button devamet1;
    private FirebaseAuth mAuth;

    private String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_telefon);

        Intent intent42 = getIntent();
        String veri2 = intent42.getStringExtra("phonecode");


        ProgressDialog yuklemebar = new ProgressDialog(KayitTelefon.this);

        ImageView lefticon = (ImageView) findViewById(R.id.left_icon);
        ImageView righticon = (ImageView) findViewById(R.id.right_icon);

        edittxtdogrulama = (OtpView) findViewById(R.id.edttxtdogrulama);
        dogrulamakodunuasagidakialana = (TextView) findViewById(R.id.dogrulamakodunuasagidakialana);
        devamet1 = (Button) findViewById(R.id.devamet1);
        phonenum = (TextView) findViewById(R.id.phonenum);

        mAuth = FirebaseAuth.getInstance();



        phonenum.setText(veri2);

        sendVerificationCode(phonenum.getText().toString());

        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KayitTelefon.this, SecimActivity.class);
                startActivity(intent);
            }
        });

        righticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        devamet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edittxtdogrulama.getText().toString())) {
                    Toast.makeText(KayitTelefon.this, "Lütfen doğrulama kodunu giriniz!", Toast.LENGTH_SHORT).show();
                } else {
                    yuklemebar.dismiss();
                    verfyCode(edittxtdogrulama.getText().toString());
                }

            }
        });
    }

    private void sendVerificationCode(String phone) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
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
            edittxtdogrulama.setText(code);
            verfyCode(code);
        }

        @Override
        public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {

            Toast.makeText(KayitTelefon.this, e.getMessage(), Toast.LENGTH_SHORT).show();

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
                if (task.isSuccessful()) {

                    Toast.makeText(KayitTelefon.this, "okaybaby", Toast.LENGTH_SHORT).show();
                    
                } else {

                    Toast.makeText(KayitTelefon.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}