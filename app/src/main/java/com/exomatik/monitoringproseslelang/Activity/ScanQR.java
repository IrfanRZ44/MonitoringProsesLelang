package com.exomatik.monitoringproseslelang.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.CAMERA;

public class ScanQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final int REQUEST_CAMERA = 1;
    private static int camId = 0;
    private ProgressDialog progressDialog = null, progressDialog2 = null;
    private ZXingScannerView scannerView;
    private View view;
    private CustomComponent component;
    private UserSave userSave;

    private void showMessageOKCancel(String paramString, DialogInterface.OnClickListener paramOnClickListener) {
        new AlertDialog.Builder(this).setMessage(paramString).setPositiveButton("OK", paramOnClickListener).create().show();
    }

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        init();
        cekPermissionCamera();
    }

    private void init() {
        view = findViewById(android.R.id.content);

        userSave = new UserSave(this);
        component = new CustomComponent(view, ScanQR.this);
    }

    private void cekPermissionCamera() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
                scannerView.startCamera();
                scannerView.setResultHandler(ScanQR.this);
            } else {
                requestPermission();
            }
        } else {
            requestPermission();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.scannerView.stopCamera();
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                        scannerView.setResultHandler(this);
                        scannerView.startCamera();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeScanner("Resume");
    }

    private void resumeScanner(String error) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
        } else {
            requestPermission();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void handleResult(Result paramResult) {
        progressDialog2 = new ProgressDialog(ScanQR.this);
        progressDialog2.setMessage("Mohon Tunggu...");
        progressDialog2.setTitle("Proses");
        progressDialog2.setCancelable(false);
        progressDialog2.show();

        getDataContract(paramResult.getText().toString());
        progressDialog2.dismiss();
    }

    private void getDataContract(String result){
        progressDialog = component.makeProgress(getResources().getString(R.string.mohon_tunggu));
        progressDialog.show();

        HashMap<String,String> body = new HashMap<String,String>();
        body.put("username", userSave.getKEY_USER().getUsername());
        body.put("level", userSave.getKEY_USER().getLevel());
        body.put("idContract", result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.jsonProcess)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<ArrayList<ModelContract>> call = api.getResultScanContract(body, "application/json");

        call.enqueue(new Callback<ArrayList<ModelContract>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelContract>> call, Response<ArrayList<ModelContract>> response) {
                ArrayList<ModelContract> dataContract = response.body();

                if (dataContract.get(0).getResponse().equals("Success")){
                    finish();
                    MainStepAct.dataContract = dataContract.get(0);
                    startActivity(new Intent(ScanQR.this, MainStepAct.class));
                }
                else{
                    component.makeSnackbar(dataContract.get(0).getResponse(), R.drawable.snakbar_red);
                    resumeScanner("Resume");
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelContract>> call, Throwable t) {
                resumeScanner("Resume");
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