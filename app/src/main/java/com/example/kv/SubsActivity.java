package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SubsActivity extends AppCompatActivity {

    private Slider slider;

    private TextView temeluyelik,standartuyelik,ozeluyelik,secilenuyelik,secilenuyeliknedir;

    private FirebaseAuth mYetki;

    private ImageView temelarrow,temelarrow2,standartarrow,standartarrow2,ozelarrow,ozelarrow2;

    private Button uyelikseciminionayla;

    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subs);

        uyelikseciminionayla=(Button) findViewById(R.id.uyelikseciminionayla);

        slider=(Slider) findViewById(R.id.abonelikbar);

        temeluyelik=(TextView) findViewById(R.id.temeluyelik);

        standartuyelik=(TextView) findViewById(R.id.standartuyelik);

        ozeluyelik=(TextView) findViewById(R.id.ozeluyelik);

        temelarrow=(ImageView) findViewById(R.id.temeluyelikarrow);

        temelarrow2=(ImageView) findViewById(R.id.temeluyelikarrow2);

        standartarrow=(ImageView) findViewById(R.id.standartuyelikarrow);

        standartarrow2=(ImageView) findViewById(R.id.standartuyelikarrow2);

        ozelarrow=(ImageView) findViewById(R.id.ozeluyelikarrow);

        ozelarrow2=(ImageView) findViewById(R.id.ozeluyelikarrow2);

        secilenuyelik  = (TextView) findViewById(R.id.secilenuyeliktextview);

        secilenuyeliknedir=(TextView) findViewById(R.id.secilenuyeliknedirtextview);

        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull @NotNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull @NotNull Slider slider) {

                value = String.valueOf(slider.getValue());

                if (value.equals("0.0"))
                {
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

                if (value.equals("50.0"))
                {
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

                if (value.equals("100.0"))
                {
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

                uyelikseciminionayla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (value.equals("0.0"))
                        {
                            AlertDialog.Builder alertdialog=new AlertDialog.Builder(SubsActivity.this);
                            alertdialog.setMessage("Üyelik seçimini onaylamak istediğinize emin misiniz?");
                            alertdialog.setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent urunleregondertemel = new Intent(SubsActivity.this,SplashScreenTemelKUygulamasi.class);
                                    startActivity(urunleregondertemel);


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

                        if (value.equals("50.0"))
                        {
                            AlertDialog.Builder alertdialog=new AlertDialog.Builder(SubsActivity.this);
                            alertdialog.setMessage("Üyelik seçimini onaylamak istediğinize emin misiniz?");
                            alertdialog.setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent urunleregonderstandart = new Intent(SubsActivity.this,SplashScreenStandartKUygulamasi.class);
                                    startActivity(urunleregonderstandart);

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

                        if (value.equals("100.0"))
                        {
                            AlertDialog.Builder alertdialog=new AlertDialog.Builder(SubsActivity.this);
                            alertdialog.setMessage("Üyelik seçimini onaylamak istediğinize emin misiniz?");
                            alertdialog.setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent urunleregonderozel = new Intent(SubsActivity.this,SplashScreenOzelKUygulamasi.class);
                                    startActivity(urunleregonderozel);

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
                });
            }
        });



        mYetki=FirebaseAuth.getInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.secenekler_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId()==R.id.ana_cikis_secenegi)
        {
            mYetki.signOut();
            Intent giris = new Intent(SubsActivity.this,SplashScreenLogin.class);
            startActivity(giris);
        }

        return true;
    }
}