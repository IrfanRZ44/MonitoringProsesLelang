package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.Model.ModelUser;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAct extends AppCompatActivity {
    private Button btn_sign_in;
    private int PERMISSIONS_REQUEST_READ_PHONE_STATE = 101;
    private TextInputLayout etNama, etPass;
    private View view;
    private ProgressDialog progressDialog;
    private CustomComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        init();
        onClick();
    }

    private void init() {
        btn_sign_in = findViewById(R.id.btn_sign_in);
        view = findViewById(android.R.id.content);
        etNama = findViewById(R.id.etNama);
        etPass = findViewById(R.id.etPass);
        component = new CustomComponent(view, LoginAct.this);
    }

    private void onClick() {
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etNama.getEditText().getText().toString();
                String password = etPass.getEditText().getText().toString();
                String imei = getImei();

                if (username.isEmpty() || password.isEmpty() || imei.isEmpty()){
                    if (username.isEmpty()){
                        component.makeSnackbar(getString(R.string.username_empty), R.drawable.snakbar_red);
                    } else if (password.isEmpty()){
                        component.makeSnackbar(getString(R.string.password_empty), R.drawable.snakbar_red);
                    } else if (imei.isEmpty()){
                        component.makeSnackbar(getString(R.string.imei_empty), R.drawable.snakbar_red);
                    }
                }
                else{
                    Log.e("IMEI", imei);
                    progressDialog = component.makeProgress(getResources().getString(R.string.mohon_tunggu));
                    progressDialog.show();
                    HashMap<String,String> body = new HashMap<String,String>();
                    body.put("password", password);
                    body.put("imei", imei);
                    body.put("username", username);

                    postLoginUser(body);
                }
            }
        });
    }

    private String getImei() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(LoginAct.this,
                        new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST_READ_PHONE_STATE);
            }
            return "";
        } else {
            TelephonyManager tlpMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String mImei = tlpMgr.getDeviceId();
            return mImei;
        }
    }

    private void postLoginUser(HashMap<String,String> body){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.jsonProcess)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<ArrayList<ModelUser>> call = api.signIn(body, "application/json");

        call.enqueue(new Callback<ArrayList<ModelUser>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelUser>> call, Response<ArrayList<ModelUser>> response) {
                ArrayList<ModelUser> dataUser = response.body();

                if (dataUser.get(0).getResponse().equals("Success")){
                    UserSave userSave = new UserSave(LoginAct.this);
                    userSave.setKEY_USER(dataUser.get(0));
                    startActivity(new Intent(LoginAct.this, MainAct.class));
                    finish();
                }
                else{
                    component.makeSnackbar(dataUser.get(0).getResponse(), R.drawable.snakbar_red);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelUser>> call, Throwable t) {
                Log.e("Message", t.getMessage().toString());
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
}