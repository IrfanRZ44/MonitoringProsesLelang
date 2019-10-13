package com.exomatik.monitoringproseslelang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.exomatik.monitoringproseslelang.Adapter.ViewPagerAdapter;
import com.exomatik.monitoringproseslelang.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ContentStep extends Fragment {
    private View view;
    private TabLayout tabKategori;
    private ViewPager viewKategori;
    private fragStep1 fgDocs1;
    private fragStep2 fgDocs2;
    private fragStep3 fgDocs3;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.activity_main, paramViewGroup, false);

        init();
        setViewPager();

        return view;
    }

    private void init() {
        tabKategori = (TabLayout) view.findViewById(R.id.tab_menu_edit);
        viewKategori = (ViewPager) view.findViewById(R.id.view_menu_edit);

        fgDocs1 = new fragStep1();
        fgDocs2 = new fragStep2();
        fgDocs3 = new fragStep3();
    }

    private void setViewPager() {
        ViewPagerAdapter adapterKategori = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapterKategori.AddFragment(fgDocs1);
        adapterKategori.AddFragment(fgDocs2);
        adapterKategori.AddFragment(fgDocs3);

        viewKategori.setAdapter(adapterKategori);
        tabKategori.setupWithViewPager(viewKategori);

        tabKategori.getTabAt(0).setText("Step 1");
        tabKategori.getTabAt(1).setText("Step 2");
        tabKategori.getTabAt(2).setText("Step 3");
    }
}