package com.example.kv;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;

public class SecimActivityFragment extends Fragment {

    SliderView sliderView;
    int[] images = {R.drawable.cappucinopng,
            R.drawable.espresso,
            R.drawable.latte,
            R.drawable.machi24};

    private Button uyeol;
    private TextView login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_secim_activity, container, false);
        {

            sliderView = (SliderView) view.findViewById(R.id.image_silder);

            SliderAdapter sliderAdapter = new SliderAdapter(images);

            sliderView.setSliderAdapter(sliderAdapter);
            sliderView.setIndicatorAnimation(IndicatorAnimationType.COLOR.WORM);
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
            sliderView.startAutoCycle();



            uyeol = (Button) view.findViewById(R.id.UyeOl);
            login = (TextView) view.findViewById(R.id.zatenhesabimvar);

            uyeol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(getActivity(), SplashScreenSecimToKayit.class);
                    startActivity(intent2);
                }
            });


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent(getActivity(), SplashScreenSecimToLogin.class);
                    startActivity(intent3);
                }
            });

            return view;
        }


    }
}