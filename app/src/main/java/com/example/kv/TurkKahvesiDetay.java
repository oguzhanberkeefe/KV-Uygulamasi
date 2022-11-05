package com.example.kv;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TurkKahvesiDetay extends AppCompatActivity {


    private Button sepeteekle;
    private TextView adetbirim,boyutu,sekeri,id2,isim;
    private ImageView adeteksilt,adetarttır;
    private Spinner boyutsecimi,sekersecimi;

    int adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turk_kahvesi);

        adet = 1;
        id2 = findViewById(R.id.id);
        isim = findViewById(R.id.name);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(isim.getText());
        sepeteekle = (Button) findViewById(R.id.sepeteekle);
        adetbirim = (TextView) findViewById(R.id.adetbirim);
        adeteksilt = (ImageView) findViewById(R.id.adeteksilt);
        adetarttır = (ImageView) findViewById(R.id.adetarttır);
        boyutsecimi = (Spinner) findViewById(R.id.boyutsecimi);
        boyutu = (TextView) findViewById(R.id.boyutu);
        sekersecimi = (Spinner) findViewById(R.id.sekersecimi);
        sekeri = (TextView) findViewById(R.id.sekeri);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.BoyutSecimiFiltreKahve, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        boyutsecimi.setAdapter(adapter);

        boyutsecimi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] BoyutSecimi=getResources().getStringArray(R.array.BoyutSecimiFiltreKahve);
                boyutu.setText(BoyutSecimi[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.SekerSecimi, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sekersecimi.setAdapter(adapter2);

        sekersecimi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] SutSecimi=getResources().getStringArray(R.array.SekerSecimi);
                sekeri.setText(SutSecimi[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (adet == 1)
        {
            adetbirim.setText(adet + " " + "Adet");
        }


        adetarttır.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adet++;
                adetbirim.setText(adet + " " + "Adet");
            }
        });

        adeteksilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adet--;
                adetbirim.setText(adet + " " + "Adet");

                if (adet <1)
                {
                    adet = 1;
                    adetbirim.setText(adet + " " + "Adet");
                }
            }
        });

        sepeteekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int minimum = 1;
                int maksimum = 2147483647;

                int randomSayi = new Random().nextInt((maksimum - minimum) + 1) + minimum;

                id2.setText(String.valueOf(randomSayi));

                CharSequence randomid=id2.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                CharSequence ismigonder= isim.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                CharSequence boyutugonder = boyutu.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                CharSequence sekerigonder = sekeri.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                CharSequence adetgonder = adetbirim.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                Intent karekod=new Intent(TurkKahvesiDetay.this,KarekodViewTurkKahvesi.class);///İntent ouşturup 2. activity'e gideceğini belirledik.
                karekod.putExtra("id",randomid);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("isim",ismigonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("boyut",boyutugonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("seker",sekerigonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("adet",adetgonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                startActivity(karekod);//İntenti başlatarak yeni activityin başlamasını sağladık.
            }
        });



    }
}