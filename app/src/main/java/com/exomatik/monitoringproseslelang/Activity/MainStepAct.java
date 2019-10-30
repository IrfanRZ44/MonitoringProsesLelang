package com.exomatik.monitoringproseslelang.Activity;

import android.app.ProgressDialog;
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
import com.exomatik.monitoringproseslelang.Fragment.fragStep1;
import com.exomatik.monitoringproseslelang.Fragment.fragStepMemo;
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
    private TextView namaUser;
    private View view;
    private fragStep1 fgDocs2, fgDocs3, fgDocs4, fgDocs5, fgDocs6, fgDocs7, fgDocs8, fgDocs9, fgDocs10;
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
        ArrayList<ModelStepContract> dataStep1 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep2 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep3 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep4 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep5 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep6 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep7 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep8 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep9 = new ArrayList<ModelStepContract>();
        ArrayList<ModelStepContract> dataStep10 = new ArrayList<ModelStepContract>();

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
                }
            }
        }

        fgDocs1 = new fragStepMemo(dataContract);
        fgDocs2 = new fragStep1(dataStep2);
        fgDocs3 = new fragStep1(dataStep3);
        fgDocs4 = new fragStep1(dataStep4);
        fgDocs5 = new fragStep1(dataStep5);
        fgDocs6 = new fragStep1(dataStep6);
        fgDocs7 = new fragStep1(dataStep7);
        fgDocs8 = new fragStep1(dataStep8);
        fgDocs9 = new fragStep1(dataStep9);
        fgDocs10 = new fragStep1(dataStep10);


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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs1).commit();
        } else if (id == R.id.nav_step2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs2).commit();
        } else if (id == R.id.nav_step3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs3).commit();
        } else if (id == R.id.nav_step4) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs4).commit();
        } else if (id == R.id.nav_step5) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs5).commit();
        } else if (id == R.id.nav_step6) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs6).commit();
        } else if (id == R.id.nav_step7) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs7).commit();
        } else if (id == R.id.nav_step8) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs8).commit();
        } else if (id == R.id.nav_step9) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs9).commit();
        } else if (id == R.id.nav_step10) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , fgDocs10).commit();
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
}
