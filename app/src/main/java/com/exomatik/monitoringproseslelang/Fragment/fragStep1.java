package com.exomatik.monitoringproseslelang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by IrfanRZ on 21/09/2018.
 */

public class fragStep1 extends Fragment {
    private View view;
    private RecyclerView rcStep;
    private RecyclerStepContract adapter;
    private ArrayList<ModelStepContract> listDocs = new ArrayList<ModelStepContract>();
    private TextView textUnstepped;
    private SwipeRefreshLayout swipeRefresh;

    public fragStep1(ArrayList<ModelStepContract> dataContract) {
        this.listDocs = dataContract;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_step, container, false);

        init();
        if (listDocs.size() == 0){
            textUnstepped.setVisibility(View.VISIBLE);
        }
        else if (!listDocs.get(0).getResponse().equals("Success")){
            textUnstepped.setVisibility(View.VISIBLE);
        }
        else{
            setAdapter();
        }
        onClick();

        return view;
    }

    private void init() {
        rcStep = view.findViewById(R.id.rcStep);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        textUnstepped = view.findViewById(R.id.textUnstepped);
    }

    private void setAdapter() {
        adapter = new RecyclerStepContract(listDocs, getContext());
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcStep.setLayoutManager(localLinearLayoutManager);
        rcStep.setNestedScrollingEnabled(false);
        rcStep.setAdapter(adapter);

        if (swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }

    private void onClick() {
        ItemClickSupport.addTo(rcStep).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                DocumentViewerAct.dataContract = listDocs.get(position);
                startActivity(new Intent(getActivity(), DocumentViewerAct.class));
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (listDocs.size() != 0){
                    setAdapter();
                }
                else{
                    textUnstepped.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
