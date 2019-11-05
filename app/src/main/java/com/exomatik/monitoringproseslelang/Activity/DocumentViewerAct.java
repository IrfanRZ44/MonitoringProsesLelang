package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Featured.CustomWebChromeClient;
import com.exomatik.monitoringproseslelang.Featured.FileDownloader;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.Model.ModelCekImei;
import com.exomatik.monitoringproseslelang.Model.ModelStepContract;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private UserSave userSave;
    private RelativeLayout rlDownload;

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
        rlDownload = findViewById(R.id.rlDownload);
        view = findViewById(android.R.id.content);

        userSave = new UserSave(this);
        component = new CustomComponent(view, DocumentViewerAct.this);

        progressDialog = component.makeProgress(getResources().getString(R.string.mohon_tunggu));
        progressDialog.show();
        swipeRefresh.setEnabled(false);

        if (userSave.getKEY_USER().getLevel().equals("user")){
            rlDownload.setVisibility(View.GONE);
        }


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
        webViewDocs.clearCache(true);
        webViewDocs.clearHistory();
        webViewDocs.clearMatches();
        webViewDocs.clearSslPreferences();
        webViewDocs.clearView();
//        webViewDocs.clearFormData();

        webViewDocs.setWebChromeClient(new CustomWebChromeClient(this));
        webViewDocs.setWebViewClient(new WebViewClient());
        webViewDocs.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewDocs.getSettings().setDomStorageEnabled(true);
        webViewDocs.getSettings().setBuiltInZoomControls(true);
        webViewDocs.getSettings().setJavaScriptEnabled(true);
        webViewDocs.getSettings().setSupportZoom(false);
        webViewDocs.getSettings().setBuiltInZoomControls(false);
        webViewDocs.getSettings().setDisplayZoomControls(false);
        webViewDocs.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        String content = "";
        content = baseUrl + "assets/files/" + dataContract.getUrl();
        content = "https://drive.google.com/viewerng/viewer?embedded=true&samesite=none&promo_shown=1&url=" + content + "";
        Log.e("Url doc", content + "  asdasd");

        webViewDocs.loadUrl(content);

        webViewDocs.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                component.makeSnackbar("Gagal membuka dokumen", R.drawable.snakbar_red);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String weburl) {

                if (jalan) {
                    swipeRefresh.setEnabled(true);
                    swipeRefresh.setRefreshing(false);
                    progressDialog.dismiss();
                    jalan = false;
                }

                if (view.getContentHeight() == 0){
                    swipeRefresh.setRefreshing(true);
                    jalan = true;
                    setWebView();
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
                startActivity(Intent.createChooser(intent, "Open folder"));
            }catch(ActivityNotFoundException e){
                component.makeSnackbar("No Application available to view PDF", R.drawable.snakbar_red);
            }
        }
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
                    Intent homeIntent = new Intent(DocumentViewerAct.this, SplashAct.class);
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
