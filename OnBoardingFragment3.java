package com.example.kv;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class OnBoardingFragment3 extends Fragment
{

    FloatingActionButton fab;
    TextView gec3;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3,container,false);

        fab = root.findViewById(R.id.fab);
        gec3 = root.findViewById(R.id.gec3);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SplashScreenLoginToKampanyalar.class);
                startActivity(intent);
            }
        });

        gec3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SplashScreenLoginToKampanyalar.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
