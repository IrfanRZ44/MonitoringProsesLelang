package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.FileDownloader;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.Model.ModelStepContract;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;

public class DocumentViewerAct extends AppCompatActivity {
    public static ModelStepContract dataContract;
    private WebView webViewDocs;
    private SwipeRefreshLayout swipeRefresh;
    private boolean jalan = true;
    private ProgressDialog progressDialog;
    private CustomComponent component;
    private View view;
    private String baseUrl = "http://dolby.mor7.com/";
    private FloatingActionButton btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_document_viewer);

        init();
        onClick();
        setWebView();
    }

    private void init() {
        webViewDocs = findViewById(R.id.webViewDocs);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        btnDownload = findViewById(R.id.btnDownload);
        view = findViewById(android.R.id.content);

        component = new CustomComponent(view, DocumentViewerAct.this);

        progressDialog = component.makeProgress(getResources().getString(R.string.mohon_tunggu));
        progressDialog.show();
        swipeRefresh.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void onClick() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setWebView();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                }, 2000L);
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataContract = null;
    }

    private void setWebView() {
        webViewDocs.getSettings().setJavaScriptEnabled(true);

        String content = "";
        content = baseUrl + "assets/files/" + dataContract.getUrl();
        content = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + content;
        Log.e("Url doc", content);

        webViewDocs.loadUrl(content);
        webViewDocs.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String weburl) {
                if (jalan) {
                    swipeRefresh.setEnabled(true);
                    progressDialog.dismiss();
                    jalan = false;
                }
            }
        });
    }

    private void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                new DownloadFile().execute(baseUrl + "assets/files/" + dataContract.getUrl(), dataContract.getNamaFile());
            } else {
                Log.v("Permission","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        else {
            new DownloadFile().execute(baseUrl + "assets/files/" + dataContract.getUrl(), dataContract.getNamaFile());
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "Download");
            folder.mkdir();

            File pdfFile = new File(folder, fileName + ".pdf");

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                    + File.separator + "Download" + File.separator);
//            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
//                    + File.separator + "Download" + File.separator + dataContract.getNamaFile() + ".pdf");
//            intent.setDataAndType(uri, "application/pdf");

            intent.setDataAndType(uri, "*/*");

            try{
                startActivity(Intent.createChooser(intent, "Open file"));
            }catch(ActivityNotFoundException e){
                Toast.makeText(DocumentViewerAct.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
