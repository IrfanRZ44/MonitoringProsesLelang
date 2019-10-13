package com.exomatik.monitoringproseslelang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by IrfanRZ on 21/09/2018.
 */

public class fragProgressDocs1 extends Fragment {
    private View view;
    private TextView textFrag;
    private String fragment;

    public fragProgressDocs1(String fragment) {
        this.fragment = fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_progress_docs_1, container, false);

        init();
        return view;
    }

    private void init() {
        textFrag = view.findViewById(R.id.textFrag);
    }
}
