package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.slider.Slider;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.mukesh.OtpView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class KayitActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth maAuth;

    private Slider slider;

    private EditText edittextad, edittextsoyad, edittextsifre, edittextemail, edittextsifredogrula, edittexttelefon;

    private Button buttonuyeol, uyelikseciminionayla, devamet1;

    private ImageView temelarrow, temelarrow2, standartarrow, standartarrow2, ozelarrow, ozelarrow2, lefticon, righticon;

    private String value;

    private OtpView edttxtdogrulama;

    private CheckBox sifreyigor;

    private String verificationId;

    private ProgressDialog progressDialog;

    private TextView telefon, temeluyelik, standartuyelik, ozeluyelik, secilenuyelik, secilenuyeliknedir, txtad, non, txtsoyad, txtemail, txtsifre, txtsifredogrula, aylikabonelikmodelleritextview, txttelefon, txtverify, phonenum, dogrulamakodunuasagidakialana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        lefticon = (ImageView) findViewById(R.id.left_icon);

        righticon = (ImageView) findViewById(R.id.right_icon);

        maAuth = FirebaseAuth.getInstance();

        txttelefon = (TextView) findViewById(R.id.txttelefon);

        txtverify = (TextView) findViewById(R.id.txtverify);

        phonenum = (TextView) findViewById(R.id.phonenum);

        dogrulamakodunuasagidakialana = (TextView) findViewById(R.id.dogrulamakodunuasagidakialana);

        edttxtdogrulama = (OtpView) findViewById(R.id.edttxtdogrulama);

        devamet1 = (Button) findViewById(R.id.devamet1);


        uyelikseciminionayla = (Button) findViewById(R.id.uyelikseciminionayla);

        slider = (Slider) findViewById(R.id.abonelikbar);

        temeluyelik = (TextView) findViewById(R.id.temeluyelik);

        standartuyelik = (TextView) findViewById(R.id.standartuyelik);

        ozeluyelik = (TextView) findViewById(R.id.ozeluyelik);

        temelarrow = (ImageView) findViewById(R.id.temeluyelikarrow);

        temelarrow2 = (ImageView) findViewById(R.id.temeluyelikarrow2);

        standartarrow = (ImageView) findViewById(R.id.standartuyelikarrow);

        standartarrow2 = (ImageView) findViewById(R.id.standartuyelikarrow2);

        ozelarrow = (ImageView) findViewById(R.id.ozeluyelikarrow);

        ozelarrow2 = (ImageView) findViewById(R.id.ozeluyelikarrow2);

        secilenuyelik = (TextView) findViewById(R.id.secilenuyeliktextview);

        secilenuyeliknedir = (TextView) findViewById(R.id.secilenuyeliknedirtextview);

        txtad = (TextView) findViewById(R.id.txtad);

        txtsoyad = (TextView) findViewById(R.id.txtsoyad);

        txtemail = (TextView) findViewById(R.id.txtemail);

        txtsifre = (TextView) findViewById(R.id.txtsifre);

        txtsifredogrula = (TextView) findViewById(R.id.txtsifredogrula);

        aylikabonelikmodelleritextview = (TextView) findViewById(R.id.aylikabonelikmodelleritextview);

        txttelefon = (TextView) findViewById(R.id.txttelefon);

        edittexttelefon = (EditText) findViewById(R.id.edttxttelefon);

        progressDialog = new ProgressDialog(KayitActivity.this);

        edittextad = (EditText) findViewById(R.id.edttxtad);

        edittextsoyad = (EditText) findViewById(R.id.edttxtsoyad);

        edittextsifre = (EditText) findViewById(R.id.edttxtsifre);

        edittextemail = (EditText) findViewById(R.id.edttxtemail);

        edittextsifredogrula = (EditText) findViewById(R.id.edttxtsifredogrula);

        sifreyigor = (CheckBox) findViewById(R.id.sifregoster);

        buttonuyeol = (Button) findViewById(R.id.uyeol);

        telefon = (TextView) findViewById(R.id.non);

        buttonuyeol.setOnClickListener(this);


        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull @NotNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull @NotNull Slider slider) {

                value = String.valueOf(slider.getValue());

                if (value.equals("0.0")) {
                    uyelikseciminionayla.setVisibility(View.VISIBLE);
                    secilenuyelik.setVisibility(View.VISIBLE);
                    secilenuyeliknedir.setVisibility(View.VISIBLE);
                    secilenuyeliknedir.setText("Temel");


                    temelarrow.setVisibility(View.VISIBLE);
                    temelarrow2.setVisibility(View.INVISIBLE);
                    standartarrow.setVisibility(View.INVISIBLE);
                    standartarrow2.setVisibility(View.VISIBLE);
                    ozelarrow.setVisibility(View.INVISIBLE);
                    ozelarrow2.setVisibility(View.VISIBLE);


                    temeluyelik.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    temeluyelik.setTextColor(Color.WHITE);
                    ozeluyelik.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    ozeluyelik.setTextColor(Color.GRAY);
                    standartuyelik.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    standartuyelik.setTextColor(Color.GRAY);
                }

                if (value.equals("50.0")) {
                    uyelikseciminionayla.setVisibility(View.VISIBLE);
                    secilenuyelik.setVisibility(View.VISIBLE);
                    secilenuyeliknedir.setVisibility(View.VISIBLE);
                    secilenuyeliknedir.setText("Standart");

                    temelarrow.setVisibility(View.INVISIBLE);
                    temelarrow2.setVisibility(View.VISIBLE);
                    standartarrow.setVisibility(View.VISIBLE);
                    standartarrow2.setVisibility(View.INVISIBLE);
                    ozelarrow.setVisibility(View.INVISIBLE);
                    ozelarrow2.setVisibility(View.VISIBLE);


                    temeluyelik.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    temeluyelik.setTextColor(Color.GRAY);
                    ozeluyelik.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    ozeluyelik.setTextColor(Color.GRAY);
                    standartuyelik.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    standartuyelik.setTextColor(Color.WHITE);
                }

                if (value.equals("100.0")) {
                    uyelikseciminionayla.setVisibility(View.VISIBLE);
                    secilenuyelik.setVisibility(View.VISIBLE);
                    secilenuyeliknedir.setVisibility(View.VISIBLE);
                    secilenuyeliknedir.setText("Özel");

                    temelarrow.setVisibility(View.INVISIBLE);
                    temelarrow2.setVisibility(View.VISIBLE);
                    standartarrow.setVisibility(View.INVISIBLE);
                    standartarrow2.setVisibility(View.VISIBLE);
                    ozelarrow.setVisibility(View.VISIBLE);
                    ozelarrow2.setVisibility(View.INVISIBLE);

                    temeluyelik.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    temeluyelik.setTextColor(Color.GRAY);
                    ozeluyelik.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    ozeluyelik.setTextColor(Color.WHITE);
                    standartuyelik.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    standartuyelik.setTextColor(Color.GRAY);
                }
            }
        });


        sifreyigor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    edittextsifre.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edittextsifredogrula.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    edittextsifre.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edittextsifredogrula.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uyeol:
                registerUser();
                break;

        }

    }

    private void registerUser() {
        String ad = edittextad.getText().toString().trim();
        String soyad = edittextsoyad.getText().toString().trim();
        String sifre = edittextsifre.getText().toString().trim();
        String sifredogrula = edittextsifredogrula.getText().toString().trim();
        String email = edittextemail.getText().toString().trim();
        String telefon2 = telefon.getText().toString().trim();
        String tel = edittexttelefon.getText().toString().trim();

        if (ad.isEmpty()) {
            edittextad.setError("Ad boş bırakılamaz!");
            edittextad.requestFocus();
            return;
        }

        if (soyad.isEmpty()) {
            edittextsoyad.setError("Soyad boş bırakılamaz!");
            edittextsoyad.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            edittextemail.setError("Email boş bırakılamaz!");
            edittextemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edittextemail.setError("Email adresi istenilen biçimde değil");
            edittextemail.requestFocus();
            return;
        }

        if (sifre.isEmpty()) {
            edittextsifre.setError("Şifre boş bırakılamaz!");
            edittextsifre.requestFocus();
            return;
        }

        if (tel.isEmpty()) {
            edittexttelefon.setError("Telefon numarası boş bırakılamaz!");
            edittexttelefon.requestFocus();
            return;
        }

        if (sifre.length() < 8) {
            edittextsifre.setError("Şifreniz çok kısa! minimum 8 karakter giriniz!");
            edittextsifre.requestFocus();
            return;
        }

        if (!sifredogrula.matches(sifre)) {
            edittextsifredogrula.setError("Şifreleriniz uyuşmuyor! Lütfen tekrar deneyiniz!");
            edittextsifredogrula.requestFocus();
            return;
        }

        phonenum.setText(edittexttelefon.getText().toString());

        sendVerificationCode(edittexttelefon.getText().toString());

        edittextad.setVisibility(View.INVISIBLE);

        edittextsoyad.setVisibility(View.INVISIBLE);

        edittextsifre.setVisibility(View.INVISIBLE);

        edittextemail.setVisibility(View.INVISIBLE);

        txttelefon.setVisibility(View.INVISIBLE);

        edittexttelefon.setVisibility(View.INVISIBLE);

        edittextsifredogrula.setVisibility(View.INVISIBLE);

        buttonuyeol.setVisibility(View.INVISIBLE);

        sifreyigor.setVisibility(View.INVISIBLE);

        telefon.setVisibility(View.INVISIBLE);

        txtad.setVisibility(View.INVISIBLE);

        txtsoyad.setVisibility(View.INVISIBLE);

        txtemail.setVisibility(View.INVISIBLE);

        txtsifre.setVisibility(View.INVISIBLE);

        txtsifredogrula.setVisibility(View.INVISIBLE);


        txtverify.setVisibility(View.VISIBLE);

        phonenum.setVisibility(View.VISIBLE);

        dogrulamakodunuasagidakialana.setVisibility(View.VISIBLE);

        edttxtdogrulama.setVisibility(View.VISIBLE);

        devamet1.setVisibility(View.VISIBLE);


        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        uyelikseciminionayla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value.equals("0.0")) {
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(KayitActivity.this);
                    alertdialog.setMessage("Üyelik seçimini onaylamak istediğinize emin misiniz?");
                    alertdialog.setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            maAuth.createUserWithEmailAndPassword(email, sifre)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                            if (task.isSuccessful()) {
                                                User user = new User(ad, soyad, email, edittexttelefon.getText().toString(), edittextsifre.getText().toString(), secilenuyeliknedir.getText().toString());
                                                FirebaseDatabase.getInstance().getReference("Users")
                                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(KayitActivity.this, "Başarıyla Kayıt Olundu!", Toast.LENGTH_SHORT).show();
                                                            Intent urunleregondertemel = new Intent(KayitActivity.this, SplashScreenTemelKUygulamasi.class);
                                                            startActivity(urunleregondertemel);
                                                            finish();


                                                        } else {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(KayitActivity.this, "Kayıt başarısız! tekrar deneyin", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                            } else {
                                                progressDialog.dismiss();

                                                Toast.makeText(KayitActivity.this, "Kayıt başarısız! girdiğiniz bilgileri ya da email adresinizi kontrol edin", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                        }
                    }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.dismiss();
                            dialog.cancel();

                        }
                    });
                    AlertDialog alert = alertdialog.create();
                    alert.show();
                }

                if (value.equals("50.0")) {
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(KayitActivity.this);
                    alertdialog.setMessage("Üyelik seçimini onaylamak istediğinize emin misiniz?");
                    alertdialog.setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            maAuth.createUserWithEmailAndPassword(email, sifre)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                            if (task.isSuccessful()) {
                                                User user = new User(ad, soyad, email, edittexttelefon.getText().toString(), edittextsifre.getText().toString(), secilenuyeliknedir.getText().toString());
                                                FirebaseDatabase.getInstance().getReference("Users")
                                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(KayitActivity.this, "Başarıyla Kayıt Olundu!", Toast.LENGTH_SHORT).show();
                                                            Intent urunleregonderstandart = new Intent(KayitActivity.this, SplashScreenStandartKUygulamasi.class);
                                                            startActivity(urunleregonderstandart);
                                                            finish();


                                                        } else {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(KayitActivity.this, "Kayıt başarısız! tekrar deneyin", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                            } else {
                                                progressDialog.dismiss();

                                                Toast.makeText(KayitActivity.this, "Kayıt başarısız! girdiğiniz bilgileri ya da email adresinizi kontrol edin", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                        }
                    }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.dismiss();
                            dialog.cancel();

                        }
                    });
                    AlertDialog alert = alertdialog.create();
                    alert.show();
                }

                if (value.equals("100.0")) {
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(KayitActivity.this);
                    alertdialog.setMessage("Üyelik seçimini onaylamak istediğinize emin misiniz?");
                    alertdialog.setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            maAuth.createUserWithEmailAndPassword(email, sifre)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                            if (task.isSuccessful()) {
                                                User user = new User(ad, soyad, email, edittexttelefon.getText().toString(), edittextsifre.getText().toString(), secilenuyeliknedir.getText().toString());
                                                FirebaseDatabase.getInstance().getReference("Users")
                                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(KayitActivity.this, "Başarıyla Kayıt Olundu!", Toast.LENGTH_SHORT).show();
                                                            Intent urunleregonderozel = new Intent(KayitActivity.this, SplashScreenOzelKUygulamasi.class);
                                                            startActivity(urunleregonderozel);
                                                            finish();


                                                        } else {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(KayitActivity.this, "Kayıt başarısız! tekrar deneyin", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(KayitActivity.this, "Kayıt başarısız! girdiğiniz bilgileri ya da email adresinizi kontrol edin", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.dismiss();
                            dialog.cancel();

                        }
                    });
                    AlertDialog alert = alertdialog.create();
                    alert.show();
                }
            }
        });

        devamet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(edttxtdogrulama.getText().toString())) {
                    Toast.makeText(KayitActivity.this, "Lütfen doğrulama kodunu giriniz!", Toast.LENGTH_SHORT).show();
                } else {
                    verfyCode(edttxtdogrulama.getText().toString());
                }
            }
        });


    }

    private void sendVerificationCode(String phone) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(maAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallBack)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull @com.google.firebase.database.annotations.NotNull String s, @NonNull @com.google.firebase.database.annotations.NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {


            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            progressDialog.dismiss();
        }

        @Override
        public void onVerificationCompleted(@NonNull @com.google.firebase.database.annotations.NotNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            edttxtdogrulama.setText(code);
            verfyCode(code);

        }

        @Override
        public void onVerificationFailed(@NonNull @com.google.firebase.database.annotations.NotNull FirebaseException e) {

            Toast.makeText(KayitActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };

    private void verfyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        SignInWithCreditial(credential);
    }

    private void SignInWithCreditial(PhoneAuthCredential credential) {
        maAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @com.google.firebase.database.annotations.NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    slider.setVisibility(View.VISIBLE);

                    temeluyelik.setVisibility(View.VISIBLE);

                    standartuyelik.setVisibility(View.VISIBLE);

                    ozeluyelik.setVisibility(View.VISIBLE);

                    temelarrow.setVisibility(View.VISIBLE);

                    standartarrow2.setVisibility(View.VISIBLE);

                    ozelarrow2.setVisibility(View.VISIBLE);

                    aylikabonelikmodelleritextview.setVisibility(View.VISIBLE);


                    txtverify.setVisibility(View.INVISIBLE);

                    phonenum.setVisibility(View.INVISIBLE);

                    dogrulamakodunuasagidakialana.setVisibility(View.INVISIBLE);

                    edttxtdogrulama.setVisibility(View.INVISIBLE);

                    devamet1.setVisibility(View.INVISIBLE);

                } else {

                    Toast.makeText(KayitActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}