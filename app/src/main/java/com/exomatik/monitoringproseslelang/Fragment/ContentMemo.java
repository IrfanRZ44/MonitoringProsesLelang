package com.exomatik.monitoringproseslelang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContentMemo extends Fragment {
    private View view;
    private int jumlahKhutbah, jumlahTarwih;
    private TextView textKhutbah, textTarwih;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.content_memo, paramViewGroup, false);


        return view;
    }
}