package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class KayitActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth maAuth;
    private EditText edittextad,edittextsoyad,edittextsifre,edittextemail;
    private Button buttonuyeol,facebookhesabinizla,googlehesabinizla;
    CheckBox sifreyigor;
    TextView telefon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        maAuth=FirebaseAuth.getInstance();

        edittextad = (EditText) findViewById(R.id.edttxtad);
        edittextsoyad = (EditText) findViewById(R.id.edttxtsoyad);
        edittextsifre = (EditText) findViewById(R.id.edttxtsifre);
        edittextemail = (EditText) findViewById(R.id.edttxtemail);
        sifreyigor = (CheckBox) findViewById(R.id.sifregoster);
        buttonuyeol = (Button) findViewById(R.id.uyeol);
        facebookhesabinizla = (Button) findViewById(R.id.facebook);
        googlehesabinizla = (Button) findViewById(R.id.google);
        telefon = (TextView) findViewById(R.id.non);
        Intent in = getIntent();
        String telefon2 = in.getStringExtra("telefon");
        telefon.setText(telefon2);

        buttonuyeol.setOnClickListener(this);
        facebookhesabinizla.setOnClickListener(this);
        googlehesabinizla.setOnClickListener(this);

        sifreyigor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    edittextsifre.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else {
                    edittextsifre.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uyeol:
                registerUser();
                break;

        }

    }

    private void registerUser() {
        String ad= edittextad.getText().toString().trim();
        String soyad= edittextsoyad.getText().toString().trim();
        String sifre= edittextsifre.getText().toString().trim();
        String email= edittextemail.getText().toString().trim();
        String telefon2= telefon.getText().toString().trim();

        if (ad.isEmpty()){
            edittextad.setError("Ad boş bırakılamaz!");
            edittextad.requestFocus();
            return;
        }

        if (soyad.isEmpty()){
            edittextsoyad.setError("Soyad boş bırakılamaz!");
            edittextsoyad.requestFocus();
            return;
        }

        if (email.isEmpty()){
            edittextemail.setError("Email boş bırakılamaz!");
            edittextemail.requestFocus();
            return;
        }

        if (sifre.isEmpty()){
            edittextsifre.setError("Şifre boş bırakılamaz!");
            edittextsifre.requestFocus();
            return;
        }

        if (sifre.length()<8){
            edittextsifre.setError("Şifreniz çok kısa! minimum 8 karakter giriniz!");
            edittextsifre.requestFocus();
            return;
        }
        maAuth.createUserWithEmailAndPassword(email,sifre)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(ad,soyad,email,telefon.getText().toString(),edittextsifre.getText().toString());
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(KayitActivity.this, "Başarıyla kayıt olundu!", Toast.LENGTH_LONG).show();
                                        Intent in = new Intent(KayitActivity.this,SplashScreenRegister.class);
                                        startActivity(in);
                                        finish();

                                    }else{
                                        Toast.makeText(KayitActivity.this, "Kayıt başarısız! tekrar deneyin", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {

                            Toast.makeText(KayitActivity.this, "Kayıt başarısız! girdiğiniz bilgileri ya da email adresinizi kontrol edin", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}