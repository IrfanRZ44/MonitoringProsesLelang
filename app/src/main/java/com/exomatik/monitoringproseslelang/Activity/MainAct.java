package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.Adapter.RecyclerDocs;
import com.exomatik.monitoringproseslelang.Adapter.ViewPagerAdapter;
import com.exomatik.monitoringproseslelang.Featured.ItemClickSupport;
import com.exomatik.monitoringproseslelang.Fragment.ContentDocs;
import com.exomatik.monitoringproseslelang.Fragment.ContentMemo;
import com.exomatik.monitoringproseslelang.Fragment.ContentStep;
import com.exomatik.monitoringproseslelang.Fragment.fragStep1;
import com.exomatik.monitoringproseslelang.Fragment.fragStep2;
import com.exomatik.monitoringproseslelang.Fragment.fragStep3;
import com.exomatik.monitoringproseslelang.Model.ModelDocs;
import com.exomatik.monitoringproseslelang.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainAct extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private View view;
    private RecyclerView rcStep;
    private RecyclerDocs adapter;
    private ArrayList<ModelDocs> listDocs = new ArrayList<ModelDocs>();
    private ImageButton btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setData();
        setAdapter();
        onClick();
    }

    private void init() {
        view = findViewById(android.R.id.content);
        rcStep = findViewById(R.id.rcContract);
        btnScan = findViewById(R.id.btnScan);
    }

    private void setData() {
        listDocs.add(new ModelDocs("Contract 1", "22-09-2018"));
        listDocs.add(new ModelDocs("Testing Contract", "15-10-2018"));
        listDocs.add(new ModelDocs("Contract Contract", "08-04-2019"));
    }

    private void setAdapter(){
        adapter = new RecyclerDocs(listDocs, MainAct.this, MainAct.this);
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(MainAct.this, LinearLayoutManager.VERTICAL, false);
        rcStep.setLayoutManager(localLinearLayoutManager);
        rcStep.setNestedScrollingEnabled(false);
        rcStep.setAdapter(adapter);
    }

    private void onClick() {
        ItemClickSupport.addTo(rcStep).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ContractViewerAct.dataContract = listDocs.get(position);
                startActivity(new Intent(MainAct.this, ContractViewerAct.class));
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this, ScanQR.class));
            }
        });
    }
}
