package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.exomatik.monitoringproseslelang.Adapter.RecyclerContract;
import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.ItemClickSupport;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.Model.ModelUser;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;

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
    private ImageButton btnScan, btnLogout;
    private UserSave userSave;
    private CustomComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getDataContract();
        onClick();
    }

    private void init() {
        view = findViewById(android.R.id.content);
        rcStep = findViewById(R.id.rcContract);
        btnScan = findViewById(R.id.btnScan);
        btnLogout = findViewById(R.id.btnLogout);

        userSave = new UserSave(this);
        component = new CustomComponent(view, MainAct.this);
    }

    private void getDataContract(){
        progressDialog = component.makeProgress(getResources().getString(R.string.mohon_tunggu));
        progressDialog.show();

        HashMap<String,String> body = new HashMap<String,String>();
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

                if (dataContract.get(0).getResponse().equals("Success")){
//                    setData(dataContract);
                    Log.e("Data", dataContract.get(0).getResponse());
                    setAdapter(dataContract);
                }
                else{
                    component.makeSnackbar(dataContract.get(0).getResponse(), R.drawable.snakbar_red);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelContract>> call, Throwable t) {
                progressDialog.dismiss();
                if (t.getMessage().toString().contains("Unable to resolve host")){
                    component.makeSnackbar("Mohon periksa koneksi Internet Anda", R.drawable.snakbar_red);
                }
                else {
                    component.makeSnackbar(t.getMessage().toString(), R.drawable.snakbar_red);
                }
            }
        });
    }

    private void setData(ArrayList<ModelContract> listContract) {
    }

    private void setAdapter(ArrayList<ModelContract> listContract){
        adapter = new RecyclerContract(listContract, MainAct.this);
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(MainAct.this, LinearLayoutManager.VERTICAL, false);
        rcStep.setLayoutManager(localLinearLayoutManager);
        rcStep.setNestedScrollingEnabled(false);
        rcStep.setAdapter(adapter);
    }

    private void onClick() {
        ItemClickSupport.addTo(rcStep).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                ContractViewerAct.dataContract = listDocs.get(position);
//                startActivity(new Intent(MainAct.this, ContractViewerAct.class));
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this, ScanQR.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSave.setKEY_USER(null);
                startActivity(new Intent(MainAct.this, SplashAct.class));
                finish();
            }
        });
    }
}
