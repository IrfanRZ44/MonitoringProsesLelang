package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.Adapter.ViewPagerAdapter;
import com.exomatik.monitoringproseslelang.Fragment.fragStep1;
import com.exomatik.monitoringproseslelang.Fragment.fragStep2;
import com.exomatik.monitoringproseslelang.Fragment.fragStep3;
import com.exomatik.monitoringproseslelang.Model.ModelDocs;
import com.exomatik.monitoringproseslelang.R;
import com.google.android.material.tabs.TabLayout;

public class ContractViewerAct extends AppCompatActivity {
    public static ModelDocs dataContract;
    private TextView textContractTitle;
    private TabLayout tabKategori;
    private ViewPager viewKategori;
    private fragStep1 fgDocs1;
    private fragStep2 fgDocs2;
    private fragStep3 fgDocs3;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_viewer);

        init();
        setData();
        setViewPager();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataContract = null;
    }

    private void init() {
        textContractTitle = findViewById(R.id.textContractTitle);
        tabKategori = (TabLayout) findViewById(R.id.tab_menu_edit);
        viewKategori = (ViewPager) findViewById(R.id.view_menu_edit);
        view = findViewById(android.R.id.content);

        fgDocs1 = new fragStep1();
        fgDocs2 = new fragStep2();
        fgDocs3 = new fragStep3();
    }

    private void setData() {
        if (dataContract != null){
            textContractTitle.setText(dataContract.getNama());
        }
    }

    private void setViewPager() {
        ViewPagerAdapter adapterKategori = new ViewPagerAdapter(getSupportFragmentManager());

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
