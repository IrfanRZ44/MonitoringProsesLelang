package com.exomatik.monitoringproseslelang.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Iterator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class ScanQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final int REQUEST_CAMERA = 1;
    private static int camId = 0;
    private ProgressDialog progressDialog = null, progressDialog2 = null;
    private ZXingScannerView scannerView;
    private View view;

    private void showMessageOKCancel(String paramString, DialogInterface.OnClickListener paramOnClickListener) {
        new AlertDialog.Builder(this).setMessage(paramString).setPositiveButton("OK", paramOnClickListener).create().show();
    }

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        view = findViewById(android.R.id.content);

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

    @Override
    public void handleResult(Result paramResult) {
        progressDialog2 = new ProgressDialog(ScanQR.this);
        progressDialog2.setMessage("Mohon Tunggu...");
        progressDialog2.setTitle("Proses");
        progressDialog2.setCancelable(false);
        progressDialog2.show();

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        progressDialog2.dismiss();
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
}