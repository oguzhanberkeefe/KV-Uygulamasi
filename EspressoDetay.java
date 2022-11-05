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

public class EspressoDetay extends AppCompatActivity {


    private Button sepeteekle;
    private TextView adetbirim,boyutu;
    private ImageView adeteksilt,adetarttır;
    private Spinner boyutsecimi;
    private FrameLayout sutframe;
    TextView sutuye,sutu;
    boolean[] selectedSut;
    ArrayList<Integer> sutList = new ArrayList<>();
    String[] sutArray = {"Yağsız"
            ,"Laktozsuz"};

    int adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso);



        sutuye = findViewById(R.id.sutuye);
        sutu = findViewById(R.id.sutu);
        sutframe = findViewById(R.id.sutframe);

        sutu.setVisibility(View.VISIBLE);
        sutuye.setVisibility(View.INVISIBLE);
        TextView text = (TextView) findViewById(R.id.text);
        TextView name = (TextView) findViewById(R.id.name);
        text.setText(name.getText());
        selectedSut = new boolean[sutArray.length];

        sutframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sutu.setVisibility(View.INVISIBLE);
                sutuye.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(
                  EspressoDetay.this
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
        boyutsecimi = (Spinner) findViewById(R.id.boyutsecimi);
        boyutu = (TextView) findViewById(R.id.boyutu);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.BoyutSecimiEspresso, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        boyutsecimi.setAdapter(adapter);

        boyutsecimi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] BoyutSecimi=getResources().getStringArray(R.array.BoyutSecimiEspresso);
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