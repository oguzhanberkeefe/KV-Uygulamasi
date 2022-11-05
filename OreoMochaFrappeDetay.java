package com.example.kv;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class OreoMochaFrappeDetay extends AppCompatActivity {


    private Button sepeteekle;
    private TextView adetbirim,boyutu;
    private ImageView adeteksilt,adetarttır;
    private FrameLayout sutframe;
    TextView sutu,sutuye;
    boolean[] selectedSut;
    ArrayList<Integer> surupList = new ArrayList<>();
    String[] surupArray = {"Beyaz Çikolata"
            ,"Fındık"
            ,"Çikolata"
            ,"Vanilya"
            ,"Karamel"};

    ArrayList<Integer> sutList = new ArrayList<>();
    String[] sutArray = {"Laktozsuz"
            ,"Standart"
            ,"Yağsız"};

    int adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oreo_mocha_frappe);



        sutuye = findViewById(R.id.sutuye);
        sutu = findViewById(R.id.sutu);
        sutframe = findViewById(R.id.sutframe);
        TextView text = (TextView) findViewById(R.id.text);
        TextView name = (TextView) findViewById(R.id.name);
        text.setText(name.getText());
        sutu.setVisibility(View.VISIBLE);
        sutuye.setVisibility(View.INVISIBLE);

        selectedSut = new boolean[sutArray.length];

        sutframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sutu.setVisibility(View.INVISIBLE);
                sutuye.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        OreoMochaFrappeDetay.this
                );

                builder.setTitle("Seçilen Süt");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(sutArray, selectedSut, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b)
                        {
                            sutList.add(i);
                            Collections.sort(sutList);

                        }
                        else
                        {
                            sutList.remove(i);

                        }
                    }
                });

                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j=0; j<sutList.size(); j++)
                        {
                            stringBuilder.append(sutArray[sutList.get(j)]);
                            if (j != sutList.size()-1)
                            {
                                stringBuilder.append(", ");
                            }
                        }
                        sutuye.setText(stringBuilder.toString()); }
                });

                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Temizle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j=0; j<selectedSut.length; j++)
                        {
                            selectedSut[j] = false;
                            sutList.clear();
                            sutuye.setText("");
                        }
                    }
                });

                builder.show();
            }
        });

        adet = 1;

        sepeteekle = (Button) findViewById(R.id.sepeteekle);
        adetbirim = (TextView) findViewById(R.id.adetbirim);
        adeteksilt = (ImageView) findViewById(R.id.adeteksilt);
        adetarttır = (ImageView) findViewById(R.id.adetarttır);
        boyutu = (TextView) findViewById(R.id.boyutu);

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



    }
}