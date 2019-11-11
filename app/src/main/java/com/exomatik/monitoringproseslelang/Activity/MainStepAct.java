package com.exomatik.monitoringproseslelang.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.Fragment.fragStep;
import com.exomatik.monitoringproseslelang.Fragment.fragStepMemo;
import com.exomatik.monitoringproseslelang.Model.ModelCekImei;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.Model.ModelStepContract;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainStepAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static ModelContract dataContract;
    private ProgressDialog progressDialog;
    private CircleImageView imgUser;
    private TextView namaUser, textHeader;
    private View view;
    private fragStep fgDocs2, fgDocs3, fgDocs4, fgDocs5, fgDocs6, fgDocs7, fgDocs8, fgDocs9, fgDocs10, fgDocs11, fgDocs12, fgDocs13;
    private fragStepMemo fgDocs1;
    private CustomComponent component;
    private UserSave userSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main_step);

        init();
        getDataContract();
        onClick();
    }

    private void init() {
        view = (View) findViewById(android.R.id.content);
        textHeader = findViewById(R.id.textHeader);

        userSave = new UserSave(this);
        component = new CustomComponent(view, MainStepAct.this);

        progressDialog = component.makeProgress(getResources().getString(R.string.mohon_tunggu));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View localView = navigationView.getHeaderView(0);
        imgUser = ((CircleImageView) localView.findViewById(R.id.image_person));
        namaUser = ((TextView) localView.findViewById(R.id.text_nama));
    }

    private void setData(ArrayList<ModelStepContract> dataStep) {
        ArrayList<ModelStepContract> dataStep2 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep3 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep4 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep5 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep6 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep7 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep8 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep9 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep10 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep11 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep12 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep13 = new ArrayList<ModelStepContract>();

        if (dataStep != null){
            for (int a = 0; a < dataStep.size(); a++){
                if (dataStep.get(a).getIdDokumen().equals("1")){
                    dataStep2.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("2")){
                    dataStep3.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("3")){
                    dataStep4.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("4")){
                    dataStep5.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("5")){
                    dataStep6.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("6")){
                    dataStep7.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("7")){
                    dataStep8.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("8")){
                    dataStep9.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("9")){
                    dataStep10.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("10")){
                    dataStep11.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("11")){
                    dataStep12.add(dataStep.get(a));
                } else if (dataStep.get(a).getIdDokumen().equals("12")){
                    dataStep13.add(dataStep.get(a));
                }
            }
        }

        fgDocs1 = new fragStepMemo(dataContract);
        fgDocs2 = new fragStep(dataStep2, dataContract, 1);
        fgDocs3 = new fragStep(dataStep3, dataContract, 2);
        fgDocs4 = new fragStep(dataStep4, dataContract, 3);
        fgDocs5 = new fragStep(dataStep5, dataContract, 4);
        fgDocs6 = new fragStep(dataStep6, dataContract, 5);
        fgDocs7 = new fragStep(dataStep7, dataContract, 6);
        fgDocs8 = new fragStep(dataStep8, dataContract, 7);
        fgDocs9 = new fragStep(dataStep9, dataContract, 8);
        fgDocs10 = new fragStep(dataStep10, dataContract, 9);
        fgDocs11 = new fragStep(dataStep11, dataContract, 10);
        fgDocs12 = new fragStep(dataStep12, dataContract, 11);
        fgDocs13 = new fragStep(dataStep13, dataContract, 12);

        textHeader.setText(getResources().getString(R.string.docs_1));

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                , fgDocs1).commit();
    }

    private void onClick() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_step1) {
            textHeader.setText(getResources().getString(R.string.docs_1));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs1).commit();
        } else if (id == R.id.nav_step2) {
            textHeader.setText(getResources().getString(R.string.docs_2));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs2).commit();
        } else if (id == R.id.nav_step3) {
            textHeader.setText(getResources().getString(R.string.docs_3));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs3).commit();
        } else if (id == R.id.nav_step4) {
            textHeader.setText(getResources().getString(R.string.docs_4));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs4).commit();
        } else if (id == R.id.nav_step5) {
            textHeader.setText(getResources().getString(R.string.docs_5));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs5).commit();
        } else if (id == R.id.nav_step6) {
            textHeader.setText(getResources().getString(R.string.docs_6));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs6).commit();
        } else if (id == R.id.nav_step7) {
            textHeader.setText(getResources().getString(R.string.docs_7));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs7).commit();
        } else if (id == R.id.nav_step8) {
            textHeader.setText(getResources().getString(R.string.docs_8));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs8).commit();
        } else if (id == R.id.nav_step9) {
            textHeader.setText(getResources().getString(R.string.docs_9));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs9).commit();
        } else if (id == R.id.nav_step10) {
            textHeader.setText(getResources().getString(R.string.docs_10));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs10).commit();
        } else if (id == R.id.nav_step11) {
            textHeader.setText(getResources().getString(R.string.docs_11));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs11).commit();
        } else if (id == R.id.nav_step12) {
            textHeader.setText(getResources().getString(R.string.docs_12));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs12).commit();
        } else if (id == R.id.nav_step13) {
            textHeader.setText(getResources().getString(R.string.docs_13));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs13).commit();
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getDataContract() {
        progressDialog.show();

        HashMap<String, String> body = new HashMap<String, String>();
        body.put("id_proyek", dataContract.getIdProyek());
        body.put("userRole", userSave.getKEY_USER().getLevel());
        body.put("userName", userSave.getKEY_USER().getUsername());

        Log.e("Body", body.toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.jsonProcess)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<ArrayList<ModelStepContract>> call = api.getStepContract(body, "application/json");

        call.enqueue(new Callback<ArrayList<ModelStepContract>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelStepContract>> call, Response<ArrayList<ModelStepContract>> response) {
                ArrayList<ModelStepContract> dataContract = response.body();

                if (dataContract.size() != 0){
                    if (dataContract.get(0).getResponse().equals("Success")) {
                        setData(dataContract);
                    } else {
                        setData(null);
                        component.makeSnackbar(dataContract.get(0).getResponse(), R.drawable.snakbar_red);
                    }
                }
                else {
                    setData(null);
                    component.makeSnackbar("Belum ada dokumen contract yang di upload", R.drawable.snakbar_red);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelStepContract>> call, Throwable t) {
                progressDialog.dismiss();
                if (t.getMessage().toString().contains("Unable to resolve host")) {
                    component.makeSnackbar("Mohon periksa koneksi Internet Anda", R.drawable.snakbar_red);
                } else {
                    Log.e("Errr", t.getMessage().toString());
                    component.makeSnackbar(t.getMessage().toString(), R.drawable.snakbar_red);
                }
                setData(null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cekImei();
    }

    private void cekImei() {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("username", userSave.getKEY_USER().getUsername());
        body.put("imei", userSave.getKEY_USER().getImei());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.jsonProcess)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<ModelCekImei> call = api.cekImei(body, "application/json");

        call.enqueue(new Callback<ModelCekImei>() {
            @Override
            public void onResponse(Call<ModelCekImei> call, Response<ModelCekImei> response) {
                ModelCekImei dataContract = response.body();

                if (dataContract.getResponse().equals("Match")) {

                } else {
                    Intent homeIntent = new Intent(MainStepAct.this, SplashAct.class);
                    startActivity(homeIntent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<ModelCekImei> call, Throwable t) {
            }
        });
    }
}
