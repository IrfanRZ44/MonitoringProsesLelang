package com.exomatik.monitoringproseslelang.CustomDialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.exomatik.monitoringproseslelang.Activity.MainAct;
import com.exomatik.monitoringproseslelang.Activity.ScanQR;
import com.exomatik.monitoringproseslelang.Activity.SplashAct;
import com.exomatik.monitoringproseslelang.Featured.UserSave;
import com.exomatik.monitoringproseslelang.R;

public class DialogProfil extends DialogFragment {
    private TextView text_nama, text_role, text_email, text_phone;
    private UserSave userSave;
    private ImageButton btnLogout;

    public static DialogProfil newInstance() {
        return new DialogProfil();
    }

    @RequiresApi(api = 23)
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View localView = paramLayoutInflater.inflate(R.layout.dialog_profile, paramViewGroup, false);

        init(localView);
        onClick();
        setData();

        return localView;
    }

    private void init(View localView) {
        btnLogout = localView.findViewById(R.id.btnLogout);
        text_nama = localView.findViewById(R.id.textNama);
        text_role = localView.findViewById(R.id.textRole);
        text_email = localView.findViewById(R.id.textEmail);
        text_phone = localView.findViewById(R.id.textPhone);

        userSave = new UserSave(getActivity());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void setData() {
        text_nama.setText(userSave.getKEY_USER().getNama());
        text_nama.setPaintFlags(text_nama.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        text_role.setText(userSave.getKEY_USER().getLevel());

        if (userSave.getKEY_USER().getNohp() == null) {
            text_phone.setText("-");
        } else if (userSave.getKEY_USER().getNohp().equals("")) {
            text_phone.setText("-");
        } else {
            text_phone.setText(userSave.getKEY_USER().getNohp());
        }

        if (userSave.getKEY_USER().getEmail() == null) {
            text_email.setText("-");
        } else if (userSave.getKEY_USER().getEmail().equals("")) {
            text_email.setText("-");
        } else {
            text_email.setText(userSave.getKEY_USER().getEmail());
        }
    }

    private void onClick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertLogout();
            }
        });
    }

    private void alertLogout() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity(), R.style.MyProgressDialogTheme);

        alert.setTitle("Logout");
        alert.setMessage("Are you sure want to Logout?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                userSave.setKEY_USER(null);
                startActivity(new Intent(getActivity(), SplashAct.class));
                getActivity().finish();
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