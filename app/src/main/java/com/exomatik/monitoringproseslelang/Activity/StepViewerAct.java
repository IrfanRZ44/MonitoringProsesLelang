package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.Adapter.ViewPagerAdapter;
import com.exomatik.monitoringproseslelang.Fragment.fragStep1;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.R;
import com.google.android.material.tabs.TabLayout;

public class StepViewerAct extends AppCompatActivity {
    public static ModelContract dataContract;
    private TextView textContractTitle;
    private TabLayout tabKategori;
    private ViewPager viewKategori;
    private fragStep1 fgDocs1, fgDocs2, fgDocs3, fgDocs4, fgDocs5, fgDocs6, fgDocs7, fgDocs8, fgDocs9, fgDocs10;
    private View view;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contract_viewer);

        init();
        setData();
        setViewPager();
        onClick();
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
        btnBack = findViewById(R.id.btnBack);
        tabKategori = (TabLayout) findViewById(R.id.tab_menu_edit);
        viewKategori = (ViewPager) findViewById(R.id.view_menu_edit);
        view = findViewById(android.R.id.content);
    }

    private void setData() {
        if (dataContract != null){
            textContractTitle.setText(dataContract.getJudulproyek());
        }

//        if (dataContract.getStep().equals("1")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(null);
//            fgDocs3 = new fragStep1(null);
//            fgDocs4 = new fragStep1(null);
//            fgDocs5 = new fragStep1(null);
//            fgDocs6 = new fragStep1(null);
//            fgDocs7 = new fragStep1(null);
//            fgDocs8 = new fragStep1(null);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        } else if (dataContract.getStep().equals("2")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(null);
//            fgDocs4 = new fragStep1(null);
//            fgDocs5 = new fragStep1(null);
//            fgDocs6 = new fragStep1(null);
//            fgDocs7 = new fragStep1(null);
//            fgDocs8 = new fragStep1(null);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("3")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(null);
//            fgDocs5 = new fragStep1(null);
//            fgDocs6 = new fragStep1(null);
//            fgDocs7 = new fragStep1(null);
//            fgDocs8 = new fragStep1(null);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("4")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(dataContract);
//            fgDocs5 = new fragStep1(null);
//            fgDocs6 = new fragStep1(null);
//            fgDocs7 = new fragStep1(null);
//            fgDocs8 = new fragStep1(null);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("5")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(dataContract);
//            fgDocs5 = new fragStep1(dataContract);
//            fgDocs6 = new fragStep1(null);
//            fgDocs7 = new fragStep1(null);
//            fgDocs8 = new fragStep1(null);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("6")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(dataContract);
//            fgDocs5 = new fragStep1(dataContract);
//            fgDocs6 = new fragStep1(dataContract);
//            fgDocs7 = new fragStep1(null);
//            fgDocs8 = new fragStep1(null);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("7")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(dataContract);
//            fgDocs5 = new fragStep1(dataContract);
//            fgDocs6 = new fragStep1(dataContract);
//            fgDocs7 = new fragStep1(dataContract);
//            fgDocs8 = new fragStep1(null);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("8")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(dataContract);
//            fgDocs5 = new fragStep1(dataContract);
//            fgDocs6 = new fragStep1(dataContract);
//            fgDocs7 = new fragStep1(dataContract);
//            fgDocs8 = new fragStep1(dataContract);
//            fgDocs9 = new fragStep1(null);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("9")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(dataContract);
//            fgDocs5 = new fragStep1(dataContract);
//            fgDocs6 = new fragStep1(dataContract);
//            fgDocs7 = new fragStep1(dataContract);
//            fgDocs8 = new fragStep1(dataContract);
//            fgDocs9 = new fragStep1(dataContract);
//            fgDocs10 = new fragStep1(null);
//        }else if (dataContract.getStep().equals("10")){
//            fgDocs1 = new fragStep1(dataContract);
//            fgDocs2 = new fragStep1(dataContract);
//            fgDocs3 = new fragStep1(dataContract);
//            fgDocs4 = new fragStep1(dataContract);
//            fgDocs5 = new fragStep1(dataContract);
//            fgDocs6 = new fragStep1(dataContract);
//            fgDocs7 = new fragStep1(dataContract);
//            fgDocs8 = new fragStep1(dataContract);
//            fgDocs9 = new fragStep1(dataContract);
//            fgDocs10 = new fragStep1(dataContract);
//        }
    }

    private void onClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setViewPager() {
        ViewPagerAdapter adapterKategori = new ViewPagerAdapter(getSupportFragmentManager());

        adapterKategori.AddFragment(fgDocs1);
        adapterKategori.AddFragment(fgDocs2);
        adapterKategori.AddFragment(fgDocs3);
        adapterKategori.AddFragment(fgDocs4);
        adapterKategori.AddFragment(fgDocs5);
        adapterKategori.AddFragment(fgDocs6);
        adapterKategori.AddFragment(fgDocs7);
        adapterKategori.AddFragment(fgDocs8);
        adapterKategori.AddFragment(fgDocs9);
        adapterKategori.AddFragment(fgDocs10);

        viewKategori.setAdapter(adapterKategori);
        tabKategori.setupWithViewPager(viewKategori);

        tabKategori.getTabAt(0).setText(getResources().getString(R.string.step_1));
        tabKategori.getTabAt(1).setText(getResources().getString(R.string.step_2));
        tabKategori.getTabAt(2).setText(getResources().getString(R.string.step_3));
        tabKategori.getTabAt(3).setText(getResources().getString(R.string.step_4));
        tabKategori.getTabAt(4).setText(getResources().getString(R.string.step_5));
        tabKategori.getTabAt(5).setText(getResources().getString(R.string.step_6));
        tabKategori.getTabAt(6).setText(getResources().getString(R.string.step_7));
        tabKategori.getTabAt(7).setText(getResources().getString(R.string.step_8));
        tabKategori.getTabAt(8).setText(getResources().getString(R.string.step_9));
        tabKategori.getTabAt(9).setText(getResources().getString(R.string.step_10));
    }
}
