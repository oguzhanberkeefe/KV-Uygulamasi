package com.example.kv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CikolataliMuffinDetay extends AppCompatActivity {

    private Button sepeteekle;
    private TextView adetbirim;
    private ImageView adeteksilt,adetarttır;

    int adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cikolatali_muffin_detay);

        adet = 1;

        sepeteekle = (Button) findViewById(R.id.sepeteekle);
        adetbirim = (TextView) findViewById(R.id.adetbirim);
        adeteksilt = (ImageView) findViewById(R.id.adeteksilt);
        adetarttır = (ImageView) findViewById(R.id.adetarttır);
        TextView text = (TextView) findViewById(R.id.text);
        TextView name = (TextView) findViewById(R.id.name);
        text.setText(name.getText());
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