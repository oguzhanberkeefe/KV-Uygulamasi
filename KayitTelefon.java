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

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class KayitTelefon extends AppCompatActivity {

    EditText telefonno,edttxtdogrulama;
    TextView phonenum,dogrulamakodunuasagidakialana,ceptelefonnumaranigir,none;
    Button devamet,devamet1;
    private FirebaseAuth mAuth;

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_telefon);

        ProgressDialog yuklemebar = new ProgressDialog(KayitTelefon.this);

        ImageView lefticon = (ImageView) findViewById(R.id.left_icon);
        ImageView righticon = (ImageView) findViewById(R.id.right_icon);

        telefonno = (EditText) findViewById(R.id.cepnogirdi);
        devamet = (Button) findViewById(R.id.devametbutton);
        edttxtdogrulama = (EditText) findViewById(R.id.edttxtdogrulama);
        dogrulamakodunuasagidakialana = (TextView) findViewById(R.id.dogrulamakodunuasagidakialana);
        devamet1 = (Button) findViewById(R.id.devamet1);
        ceptelefonnumaranigir = (TextView) findViewById(R.id.ceptelefonnumaranigir);
        phonenum = (TextView) findViewById(R.id.phonenum);

        edttxtdogrulama.setVisibility(View.INVISIBLE);
        dogrulamakodunuasagidakialana.setVisibility(View.INVISIBLE);
        devamet1.setVisibility(View.INVISIBLE);
        telefonno.setVisibility(View.VISIBLE);
        phonenum.setVisibility(View.INVISIBLE);
        devamet.setVisibility(View.VISIBLE);
        ceptelefonnumaranigir.setVisibility(View.VISIBLE);

        mAuth=FirebaseAuth.getInstance();

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


        devamet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(telefonno.getText().toString())){
                    Toast.makeText(KayitTelefon.this, "Lütfen telefon numarası giriniz!", Toast.LENGTH_SHORT).show();
                }
                else {
                    yuklemebar.setMessage("Doğrulama kodu gönderiliyor. Lütfen bekleyiniz...");
                    yuklemebar.show();
                    String phone = telefonno.getText().toString();
                    sendVerificationCode(phone);
                    phonenum.setText(telefonno.getText());
                    yuklemebar.dismiss();

                }
            }
        });

        devamet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edttxtdogrulama.getText().toString())){
                    Toast.makeText(KayitTelefon.this, "Lütfen doğrulama kodunu giriniz!", Toast.LENGTH_SHORT).show();
                }
                else {
                    yuklemebar.dismiss();
                    verfyCode(edttxtdogrulama.getText().toString());
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

            edttxtdogrulama.setVisibility(View.VISIBLE);
            dogrulamakodunuasagidakialana.setVisibility(View.VISIBLE);
            devamet1.setVisibility(View.VISIBLE);
            telefonno.setVisibility(View.INVISIBLE);
            phonenum.setVisibility(View.VISIBLE);
            devamet.setVisibility(View.INVISIBLE);
            ceptelefonnumaranigir.setVisibility(View.INVISIBLE);

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

            edttxtdogrulama.setVisibility(View.INVISIBLE);
            dogrulamakodunuasagidakialana.setVisibility(View.INVISIBLE);
            devamet1.setVisibility(View.INVISIBLE);
            telefonno.setVisibility(View.VISIBLE);
            phonenum.setVisibility(View.INVISIBLE);
            devamet.setVisibility(View.VISIBLE);
            ceptelefonnumaranigir.setVisibility(View.VISIBLE);

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
                if (task.isSuccessful())
                {
                    Intent in = new Intent(getApplicationContext(),KayitActivity.class);
                    in.putExtra("telefon",telefonno.getText().toString());
                    startActivity(in);
                    finish();
                }else {

                    Toast.makeText(KayitTelefon.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}