package com.exomatik.monitoringproseslelang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exomatik.monitoringproseslelang.Adapter.RecyclerContract;
import com.exomatik.monitoringproseslelang.Model.ModelDocs;
import com.exomatik.monitoringproseslelang.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by IrfanRZ on 21/09/2018.
 */

public class fragStep1 extends Fragment {
    private View view;
    private RecyclerView rcStep;
    private RecyclerContract adapter;
    private ArrayList<ModelDocs> listDocs = new ArrayList<ModelDocs>();

    public fragStep1() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_step_1, container, false);

        init();
        setData();
        setAdapter();

        return view;
    }

    private void init() {
        rcStep = view.findViewById(R.id.rcStep);
    }

    private void setData() {
        listDocs.add(new ModelDocs("Ini Step 1", "22-09-2018"));
        listDocs.add(new ModelDocs("Ini nama step 3", "15-10-2018"));
        listDocs.add(new ModelDocs("Ini nama step 5", "08-04-2019"));
    }

    private void setAdapter(){
//        adapter = new RecyclerContract(listDocs, getContext(), getActivity());
//        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rcStep.setLayoutManager(localLinearLayoutManager);
//        rcStep.setNestedScrollingEnabled(false);
//        rcStep.setAdapter(adapter);
    }

}
