package com.exomatik.monitoringproseslelang.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.R;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class RecyclerContract extends RecyclerView.Adapter<RecyclerContract.bidangViewHolder> {
    private ArrayList<ModelContract> dataList;
    private Context context;

    public RecyclerContract(ArrayList<ModelContract> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_contract, parent, false);

        this.context = parent.getContext();
        return new bidangViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(bidangViewHolder holder, final int position) {
        holder.textNamaProyek.setText(dataList.get(position).getJudulproyek());
        holder.textNoRak.setText("No. Rak : " + dataList.get(position).getNoRak());
        String status = setStatus(dataList.get(position).getStep(), position);
        holder.textNoKontrak.setText(status);
        holder.textTanggalCreated.setText(dataList.get(position).getTglPermintaanproyek());

//        setTanggal(holder.textTanggalCreated, dataList.get(position).getTglPermintaanproyek());
    }

    private void setTanggal(TextView textTanggalCreated, String tanggalPermintaan) {

    }

    private String setStatus(String step, int position){
        if (step.equals("1")){
            step = "Tahap : " + context.getResources().getString(R.string.step_1);
        } else if (step.equals("2")){
            step = "Tahap : " + context.getResources().getString(R.string.step_2);
        }else if (step.equals("3")){
            step = "Tahap : " + context.getResources().getString(R.string.step_3);
        }else if (step.equals("4")){
            step = "Tahap : " + context.getResources().getString(R.string.step_4);
        }else if (step.equals("5")){
            step = "Tahap : " + context.getResources().getString(R.string.step_5);
        }else if (step.equals("6")){
            step = "Tahap : " + context.getResources().getString(R.string.step_6);
        }else if (step.equals("7")){
            step = "Tahap : " + context.getResources().getString(R.string.step_7);
        }else if (step.equals("8")){
            step = "Tahap : " + context.getResources().getString(R.string.step_8);
        }else if (step.equals("9")){
            step = "Tahap : " + context.getResources().getString(R.string.step_9);
        }else if (step.equals("10")){
            step = "Tahap : " + context.getResources().getString(R.string.step_10);
        }else if (step.equals("11")){
            step = "Tahap : " + context.getResources().getString(R.string.step_11);
        }else if (step.equals("12")){
            step = "Tahap : " + context.getResources().getString(R.string.step_12);
        }else if (step.equals("13")){
            step = "Nomor Kontrak : " + dataList.get(position).getNokontrak();
        } else {
            step = context.getResources().getString(R.string.step_0);
        }
        return step;
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder {
        private TextView textNamaProyek, textNoRak, textNoKontrak, textTanggalCreated;

        public bidangViewHolder(View itemView) {
            super(itemView);

            textNamaProyek = itemView.findViewById(R.id.textNamaProyek);
            textNoRak = itemView.findViewById(R.id.textNoRak);
            textNoKontrak = itemView.findViewById(R.id.textNoKontrak);
            textTanggalCreated = itemView.findViewById(R.id.textTanggalCreated);
        }
    }
}
