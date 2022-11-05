package com.example.kv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.firebase.database.annotations.NotNull;


public class SekmeErisimAdapter extends FragmentPagerAdapter {
    public SekmeErisimAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int i)
    {

        switch (i)
        {
            case 0:
                UrunlerFrgmnt urunlerFragment = new UrunlerFrgmnt();
                return urunlerFragment;

            case 1:
                GpsFrgmnt gpsFrgmnt = new GpsFrgmnt();
                return gpsFrgmnt;

            default:
                return null;
        }

    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Urunler";

            case 1:
                return "GPS";

            default:
                return null;
        }

    }
}
