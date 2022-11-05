package com.example.kv;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class KarekodView extends AppCompatActivity {

    private ImageView karekodview;


    DatabaseReference dbref;

    TextView id2,isim,boyut,surup,sut,adet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karekod_view);


        karekodview = findViewById(R.id.karekod);

        id2=(TextView)findViewById(R.id.id);
        isim=(TextView)findViewById(R.id.name);
        boyut=(TextView)findViewById(R.id.boyut);
        surup=(TextView)findViewById(R.id.surup);
        sut=(TextView)findViewById(R.id.sut);
        adet=(TextView)findViewById(R.id.adet);
        Bundle gelenVeri=getIntent().getExtras();
        id2.setText(gelenVeri.getString("id"));
        isim.setText(gelenVeri.getString("isim"));
        boyut.setText(gelenVeri.getString("boyut"));
        surup.setText(gelenVeri.getString("surup"));
        sut.setText(gelenVeri.getString("sut"));
        adet.setText(gelenVeri.getString("adet"));
        String id23 = id2.getText().toString().trim();
        String isim2 = isim.getText().toString().trim();
        String boyut2 = boyut.getText().toString().trim();
        String surup2 = surup.getText().toString().trim();
        String sut2 = sut.getText().toString().trim();
        String adet2 = adet.getText().toString().trim();

        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child(id23).child("id").setValue(id23);
        dbref.child(id23).child("isim").setValue(isim2);
        dbref.child(id23).child("boyut").setValue(boyut2);
        dbref.child(id23).child("surup").setValue(surup2);
        dbref.child(id23).child("sut").setValue(sut2);
        dbref.child(id23).child("adet").setValue(adet2);


        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(id23, BarcodeFormat.QR_CODE
                    , 300, 300);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            karekodview.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }


    public void onBackPressed()
    {
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
        alertdialog.setMessage("Kapatmak istediğinizden emin misiniz?");
        alertdialog.setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent urunleregonder = new Intent(KarekodView.this,TemelKUygulamasi.class);
                startActivity(urunleregonder);

            }
        }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog alert=alertdialog.create();
        alert.show();
    }
}