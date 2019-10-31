package com.exomatik.monitoringproseslelang.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.Model.ModelStepContract;
import com.exomatik.monitoringproseslelang.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class RecyclerStepContract extends RecyclerView.Adapter<RecyclerStepContract.bidangViewHolder> {
    private ArrayList<ModelStepContract> dataList;
    private ModelContract dataContract;
    private Context context;
    private int step;

    public RecyclerStepContract(ArrayList<ModelStepContract> dataList, Context context, ModelContract dataContract, int step) {
        this.dataList = dataList;
        this.context = context;
        this.dataContract = dataContract;
        this.step = step;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_step_contract, parent, false);

        this.context = parent.getContext();
        return new bidangViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(bidangViewHolder holder, final int position) {
        String text1 = dataList.get(position).getNamaFile().substring(0, 1);
        holder.textNamaFile.setText(dataList.get(position).getNamaFile());
        holder.textBulat.setText(text1);
        if (step == 9){
            holder.textDesc.setText("Nomor Kontrak : " + dataContract.getNokontrak());
        }
        else {
            holder.textDesc.setText(dataList.get(position).getDeskripsi_file());
        }

        setColor(holder.imgBulat, position);
        selectDate(step, holder.textTime);
    }

    private void setColor(CardView card, int position){
        if (position == 1){
            card.setCardBackgroundColor(Color.RED);
        } else if (position == 2){
            card.setCardBackgroundColor(Color.BLUE);
        }else if (position == 3){
            card.setCardBackgroundColor(Color.GREEN);
        }else if (position == 4){
            card.setCardBackgroundColor(Color.YELLOW);
        }else if (position == 5){
            card.setCardBackgroundColor(Color.BLACK);
        }else if (position == 6){
            card.setCardBackgroundColor(Color.RED);
        }else if (position == 7){
            card.setCardBackgroundColor(Color.BLUE);
        }else if (position == 8){
            card.setCardBackgroundColor(Color.GREEN);
        }else if (position == 9){
            card.setCardBackgroundColor(Color.YELLOW);
        }else if (position == 10){
            card.setCardBackgroundColor(Color.BLACK);
        }
    }

    private void selectDate(int status, TextView textTime){
        String tanggal = "";
        if (status == 1){
            tanggal = dataContract.getTglPermintaanproyek();
        } else if (status == 2){
            tanggal = dataContract.getTglPrakualifikasi();
        }else if (status == 3){
            tanggal = dataContract.getTglaanwijzing();
        }else if (status == 4){
            tanggal = dataContract.getTglPembukaansampul1();
        }else if (status == 5){
            tanggal = dataContract.getTglPembukaansampul2();
        }else if (status == 6){
            tanggal = dataContract.getTglNegosiasi();
        }else if (status == 7){
            tanggal = dataContract.getTglPenetapanpemenang();
        }else if (status == 8){
            tanggal = dataContract.getTglPenunjukanpemenang();
        }else if (status == 9){
            tanggal = dataContract.getDatetime();
        }else if (status == 10){
            tanggal = dataContract.getDatetime();
        }

        textTime.setText(tanggal);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder {
        private TextView textTime, textNamaFile, textBulat, textDesc;
        private CardView imgBulat;

        public bidangViewHolder(View itemView) {
            super(itemView);

            textTime = itemView.findViewById(R.id.textTime);
            textNamaFile = itemView.findViewById(R.id.textNamaFile);
            textBulat = itemView.findViewById(R.id.textBulat);
            textDesc = itemView.findViewById(R.id.textDesc);
            imgBulat = itemView.findViewById(R.id.imgBulat);
        }
    }
}
