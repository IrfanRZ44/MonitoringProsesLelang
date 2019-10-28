package com.exomatik.monitoringproseslelang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.Activity.DocumentViewerAct;
import com.exomatik.monitoringproseslelang.Adapter.RecyclerStepContract;
import com.exomatik.monitoringproseslelang.Featured.ItemClickSupport;
import com.exomatik.monitoringproseslelang.Model.ModelStepContract;
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
    private RecyclerStepContract adapter;
    private ArrayList<ModelStepContract> listDocs = new ArrayList<ModelStepContract>();
    private TextView textUnstepped;

    public fragStep1(ArrayList<ModelStepContract> dataContract) {
        this.listDocs = dataContract;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_step, container, false);

        init();
//        getContract(dat);
        setData();
        if (listDocs.size() != 0){
            setAdapter();
        }
        else{
            textUnstepped.setVisibility(View.VISIBLE);
        }
        onClick();

        return view;
    }

    private void init() {
        rcStep = view.findViewById(R.id.rcStep);
        textUnstepped = view.findViewById(R.id.textUnstepped);
    }

    private void setData() {

    }

    private void setAdapter() {
        adapter = new RecyclerStepContract(listDocs, getContext());
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcStep.setLayoutManager(localLinearLayoutManager);
        rcStep.setNestedScrollingEnabled(false);
        rcStep.setAdapter(adapter);
    }

    private void onClick() {
        ItemClickSupport.addTo(rcStep).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                DocumentViewerAct.dataContract = listDocs.get(position);
                startActivity(new Intent(getActivity(), DocumentViewerAct.class));
            }
        });
    }

}
