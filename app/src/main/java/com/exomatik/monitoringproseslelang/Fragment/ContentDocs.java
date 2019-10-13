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

public class ContentDocs extends Fragment {
    private View view;
    private TabLayout tabKategori;
    private ViewPager viewKategori;
    private fragProgressDocs1 fgDocs1;
    private fragProgressDocs2 fgDocs2;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.content_docs, paramViewGroup, false);

        init();
        setViewPager();

        return view;
    }

    private void init() {
        tabKategori = (TabLayout) view.findViewById(R.id.tab_menu_edit);
        viewKategori = (ViewPager) view.findViewById(R.id.view_menu_edit);

        fgDocs1 = new fragProgressDocs1("1");
        fgDocs2 = new fragProgressDocs2("2");
    }

    private void setViewPager() {
        ViewPagerAdapter adapterKategori = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapterKategori.AddFragment(fgDocs1);
        adapterKategori.AddFragment(fgDocs2);

        viewKategori.setAdapter(adapterKategori);
        tabKategori.setupWithViewPager(viewKategori);

        tabKategori.getTabAt(0).setText("Docs 1");
        tabKategori.getTabAt(1).setText("Docs 2");
    }
}