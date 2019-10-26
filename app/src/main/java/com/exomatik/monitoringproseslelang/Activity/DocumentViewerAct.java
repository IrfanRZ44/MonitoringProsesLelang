package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.exomatik.monitoringproseslelang.Featured.CustomComponent;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.R;
import com.exomatik.monitoringproseslelang.Rest.RetrofitApi;

public class DocumentViewerAct extends AppCompatActivity {
    public static ModelContract dataContract;
    private WebView webViewDocs;
    private SwipeRefreshLayout swipeRefresh;
    private boolean jalan = true;
    private ProgressDialog progressDialog;
    private CustomComponent component;
    private View view;
    private String baseUrl = "http://dolby.mor7.com/";

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataContract = null;
    }

    private void setWebView() {
        webViewDocs.getSettings().setJavaScriptEnabled(true);

        String content = "";
        content = baseUrl + "export/" + dataContract.getIdProyek() + ".pdf";
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
}
