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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FiltreKahveDetay extends AppCompatActivity {


    private Button sepeteekle;
    private TextView adetbirim,boyutu,id2,isim;
    private ImageView adeteksilt,adetarttır;
    private Spinner boyutsecimi;
    private FrameLayout surupframe;
    TextView surupuye,surupu;
    boolean[] selectedSurup;
    ArrayList<Integer> surupList = new ArrayList<>();
    String[] surupArray = {"Beyaz Çikolata"
            ,"Fındık"
            ,"Çikolata"
            ,"Vanilya"
            ,"Karamel"};

    int adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre_kahve);


        id2 = findViewById(R.id.id);
        isim = findViewById(R.id.name);
        surupuye = findViewById(R.id.surupuye);
        surupu = findViewById(R.id.surupu);
        surupframe = findViewById(R.id.surupframe);

        surupu.setVisibility(View.VISIBLE);
        surupuye.setVisibility(View.INVISIBLE);
        TextView text = (TextView) findViewById(R.id.text);
        TextView name = (TextView) findViewById(R.id.name);
        text.setText(name.getText());
        selectedSurup = new boolean[surupArray.length];

        surupframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surupu.setVisibility(View.INVISIBLE);
                surupuye.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(
                  FiltreKahveDetay.this
                );

                builder.setTitle("Seçilen Şurup");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(surupArray, selectedSurup, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b)
                        {
                            surupList.add(i);
                            Collections.sort(surupList);

                        }
                        else
                        {
                            surupList.remove(i);

                        }
                    }
                });


                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j=0; j<surupList.size(); j++)
                        {
                            stringBuilder.append(surupArray[surupList.get(j)]);
                            if (j != surupList.size()-1)
                            {
                                stringBuilder.append(", ");
                            }
                        }
                        surupuye.setText(stringBuilder.toString()); }
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
                        for (int j=0; j<selectedSurup.length; j++)
                        {
                            selectedSurup[j] = false;
                            surupList.clear();
                            surupuye.setText("");
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
                CharSequence surupugonder = surupuye.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                CharSequence adetgonder = adetbirim.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                Intent karekod=new Intent(FiltreKahveDetay.this,KarekodViewFiltreKahve.class);///İntent ouşturup 2. activity'e gideceğini belirledik.
                karekod.putExtra("id",randomid);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("isim",ismigonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("boyut",boyutugonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("surup",surupugonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                karekod.putExtra("adet",adetgonder);//Gönderilecek veriyi ve bir anahtar belirledik.
                startActivity(karekod);//İntenti başlatarak yeni activityin başlamasını sağladık.
            }
        });



    }
}