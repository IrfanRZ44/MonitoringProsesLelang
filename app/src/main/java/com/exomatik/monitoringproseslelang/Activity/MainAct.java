package com.exomatik.monitoringproseslelang.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.Adapter.RecyclerContract;
import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.ItemClickSupport;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
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
    private TextView text_nama, text_role, text_email, text_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        init();
        setData();
        getDataContract();
        onClick();
    }

    private void init() {
        view = findViewById(android.R.id.content);
        rcStep = findViewById(R.id.rcContract);
        btnScan = findViewById(R.id.btnScan);
        btnLogout = findViewById(R.id.btnLogout);
        text_nama = findViewById(R.id.text_nama);
        text_role = findViewById(R.id.text_role);
        text_email = findViewById(R.id.text_email);
        text_phone = findViewById(R.id.text_phone);

        userSave = new UserSave(this);
        component = new CustomComponent(view, MainAct.this);

        text_nama.setPaintFlags(text_nama.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void onClick() {
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAct.this, ScanQR.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertLogout();
            }
        });
    }

    private void setData() {
        text_nama.setText(userSave.getKEY_USER().getNama());
        text_role.setText(userSave.getKEY_USER().getLevel());

        if (userSave.getKEY_USER().getNohp() == null){
            text_phone.setText("-");
        }else if (userSave.getKEY_USER().getNohp().equals("")){
            text_phone.setText("-");
        }
        else {
            text_phone.setText(userSave.getKEY_USER().getNohp());
        }

        if (userSave.getKEY_USER().getEmail() == null){
            text_email.setText("-");
        }else if (userSave.getKEY_USER().getEmail().equals("")){
            text_email.setText("-");
        }
        else {
            text_email.setText(userSave.getKEY_USER().getEmail());
        }
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

    private void setAdapter(final ArrayList<ModelContract> listContract){
        adapter = new RecyclerContract(listContract, MainAct.this);
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(MainAct.this, LinearLayoutManager.VERTICAL, false);
        rcStep.setLayoutManager(localLinearLayoutManager);
        rcStep.setNestedScrollingEnabled(false);
        rcStep.setAdapter(adapter);

        ItemClickSupport.addTo(rcStep).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                StepViewerAct.dataContract = listContract.get(position);
                startActivity(new Intent(MainAct.this, StepViewerAct.class));
            }
        });
    }

    private void setData(ArrayList<ModelContract> listContract) {
    }

    private void alertLogout() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainAct.this, R.style.MyProgressDialogTheme);

        alert.setTitle("Logout");
        alert.setMessage("Are you sure want to Logout?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                userSave.setKEY_USER(null);
                startActivity(new Intent(MainAct.this, SplashAct.class));
                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }
}
