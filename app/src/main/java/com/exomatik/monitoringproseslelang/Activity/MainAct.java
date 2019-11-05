package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.exomatik.monitoringproseslelang.Adapter.RecyclerContract;
import com.exomatik.monitoringproseslelang.CustomDialog.DialogProfil;
import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.ItemClickSupport;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.Model.ModelCekImei;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainAct extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private View view;
    private RecyclerView rcStep;
    private RecyclerContract adapter;
    private ImageButton btnProfile;
    private FloatingActionButton btnScan;
    private UserSave userSave;
    private CustomComponent component;
    private EditText et_cari;
    private ArrayList<ModelContract> listContract = new ArrayList<ModelContract>();
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        init();
        getDataContract();
        onClick();
    }

    private void init() {
        view = findViewById(android.R.id.content);
        rcStep = findViewById(R.id.rcContract);
        btnProfile = findViewById(R.id.btnProfile);
        btnScan = findViewById(R.id.btnScan);
        et_cari = findViewById(R.id.et_cari);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        userSave = new UserSave(this);
        component = new CustomComponent(view, MainAct.this);
    }

    private void onClick() {
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogProfil dialog = DialogProfil.newInstance();
                dialog.setCancelable(true);
                dialog.show(getFragmentManager(), "dialog");
            }
        });

        et_cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = et_cari.getText().toString();

                if (text.isEmpty()) {
                    setAdapter(listContract);
                } else {
                    ArrayList<ModelContract> list = new ArrayList<ModelContract>();

                    for (int a = 0; a < listContract.size(); a++) {
                        if (listContract.get(a).getJudulproyek().contains(text)) {
                            list.add(listContract.get(a));
                        }
                    }

                    setAdapter(list);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listContract.removeAll(listContract);
                getDataContract();
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this, ScanQR.class));
            }
        });
    }

    private void getDataContract() {
        progressDialog = component.makeProgress(getResources().getString(R.string.mohon_tunggu));
        progressDialog.show();

        HashMap<String, String> body = new HashMap<String, String>();
        body.put("username", userSave.getKEY_USER().getUsername());
        body.put("level", userSave.getKEY_USER().getLevel());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.jsonProcess)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<ArrayList<ModelContract>> call = api.getContract(body, "application/json");

        call.enqueue(new Callback<ArrayList<ModelContract>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelContract>> call, Response<ArrayList<ModelContract>> response) {
                ArrayList<ModelContract> dataContract = response.body();

                if (dataContract.get(0).getResponse().equals("Success")) {
                    listContract = dataContract;
                    setAdapter(dataContract);
                } else {
                    component.makeSnackbar(dataContract.get(0).getResponse(), R.drawable.snakbar_red);
                }

                progressDialog.dismiss();
                if (swipeRefresh.isRefreshing()) {
                    swipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelContract>> call, Throwable t) {
                progressDialog.dismiss();
                if (swipeRefresh.isRefreshing()) {
                    swipeRefresh.setRefreshing(false);
                }
                if (t.getMessage().toString().contains("Unable to resolve host")) {
                    component.makeSnackbar("Mohon periksa koneksi Internet Anda", R.drawable.snakbar_red);
                } else {
                    component.makeSnackbar(t.getMessage().toString(), R.drawable.snakbar_red);
                }
            }
        });
    }

    private void setAdapter(ArrayList<ModelContract> list) {
        adapter = new RecyclerContract(list, MainAct.this);
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(MainAct.this, LinearLayoutManager.VERTICAL, false);
        rcStep.setLayoutManager(localLinearLayoutManager);
        rcStep.setNestedScrollingEnabled(false);
        rcStep.setAdapter(adapter);

        ItemClickSupport.addTo(rcStep).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                MainStepAct.dataContract = listContract.get(position);
                startActivity(new Intent(MainAct.this, MainStepAct.class));
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
                    Intent homeIntent = new Intent(MainAct.this, SplashAct.class);
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
