package com.example.kv;

import android.content.DialogInterface;
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

public class TurkKahvesiDetay extends AppCompatActivity {


    private Button sepeteekle;
    private TextView adetbirim,boyutu;
    private ImageView adeteksilt,adetarttır;
    private Spinner boyutsecimi;
    private FrameLayout sekerframe;
    TextView sekeruye,sekeri;
    boolean[] selectedSeker;
    ArrayList<Integer> sekerList = new ArrayList<>();
    String[] sekerArray = {"Şekerli"
            ,"Şekersiz"
            ,"Orta Şekerli"};

    int adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turk_kahvesi);



        sekeruye = findViewById(R.id.sekeruye);
        sekeri = findViewById(R.id.sekeri);
        sekerframe = findViewById(R.id.sekerframe);
        TextView text = (TextView) findViewById(R.id.text);
        TextView name = (TextView) findViewById(R.id.name);
        text.setText(name.getText());

        sekeri.setVisibility(View.VISIBLE);
        sekeruye.setVisibility(View.INVISIBLE);

        selectedSeker = new boolean[sekerArray.length];

        sekerframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekeri.setVisibility(View.INVISIBLE);
                sekeruye.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(
                  TurkKahvesiDetay.this
                );

                builder.setTitle("Seçilen Şurup");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(sekerArray, selectedSeker, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b)
                        {
                            sekerList.add(i);
                            Collections.sort(sekerList);

                        }
                        else
                        {
                            sekerList.remove(i);

                        }
                    }
                });


                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j=0; j<sekerList.size(); j++)
                        {
                            stringBuilder.append(sekerArray[sekerList.get(j)]);
                            if (j != sekerList.size()-1)
                            {
                                stringBuilder.append(", ");
                            }
                        }
                        sekeruye.setText(stringBuilder.toString()); }
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
                        for (int j=0; j<selectedSeker.length; j++)
                        {
                            selectedSeker[j] = false;
                            sekerList.clear();
                            sekeruye.setText("");
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
        boyutsecimi = (Spinner) findViewById(R.id.boyutsecimi);
        boyutu = (TextView) findViewById(R.id.boyutu);

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