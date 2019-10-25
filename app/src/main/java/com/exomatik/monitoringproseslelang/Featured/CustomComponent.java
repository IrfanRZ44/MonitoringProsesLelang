package com.exomatik.monitoringproseslelang.Featured;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.exomatik.monitoringproseslelang.R;
import com.google.android.material.snackbar.Snackbar;

public class CustomComponent {
    private View view;
    private Context context;
    public CustomComponent(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void makeSnackbar(String text, int background) {
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        // Get the Snackbar view
        View v = snackbar.getView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(ContextCompat.getDrawable(context, background));
        }
        TextView tv = (TextView) v.findViewById(com.google.android.material.R.id.snackbar_text);

        tv.setTextColor(Color.parseColor("#FFFFFF"));

        snackbar.setText(text);
        snackbar.show();
    }

    public ProgressDialog makeProgress(String text){
        ProgressDialog progress = new ProgressDialog(context, R.style.MyProgressDialogTheme);
        progress.setMessage(text);
        progress.setCancelable(false);

        return progress;
    }
}
