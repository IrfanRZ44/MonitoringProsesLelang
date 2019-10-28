package com.exomatik.monitoringproseslelang.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.exomatik.monitoringproseslelang.Model.ModelStepContract;
import com.exomatik.monitoringproseslelang.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class RecyclerStepContract extends RecyclerView.Adapter<RecyclerStepContract.bidangViewHolder> {
    private ArrayList<ModelStepContract> dataList;
    private Context context;

    public RecyclerStepContract(ArrayList<ModelStepContract> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
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
        holder.textTime.setText(dataList.get(position).getDatetimeCreate());
        holder.textStep.setText(setStatus(dataList.get(position).getIdDokumen(), holder.imgBulat));
    }

    private String setStatus(String step, CardView card){
        if (step.equals("1")){
            step = context.getResources().getString(R.string.step_1);
            card.setCardBackgroundColor(Color.RED);
        } else if (step.equals("2")){
            step = context.getResources().getString(R.string.step_2);
            card.setCardBackgroundColor(Color.BLUE);
        }else if (step.equals("3")){
            step = context.getResources().getString(R.string.step_3);
            card.setCardBackgroundColor(Color.GREEN);
        }else if (step.equals("4")){
            step = context.getResources().getString(R.string.step_4);
            card.setCardBackgroundColor(Color.YELLOW);
        }else if (step.equals("5")){
            step = context.getResources().getString(R.string.step_5);
            card.setCardBackgroundColor(Color.BLACK);
        }else if (step.equals("6")){
            step = context.getResources().getString(R.string.step_6);
            card.setCardBackgroundColor(Color.RED);
        }else if (step.equals("7")){
            step = context.getResources().getString(R.string.step_7);
            card.setCardBackgroundColor(Color.BLUE);
        }else if (step.equals("8")){
            step = context.getResources().getString(R.string.step_8);
            card.setCardBackgroundColor(Color.GREEN);
        }else if (step.equals("9")){
            step = context.getResources().getString(R.string.step_9);
            card.setCardBackgroundColor(Color.YELLOW);
        }else if (step.equals("10")){
            step = context.getResources().getString(R.string.step_10);
            card.setCardBackgroundColor(Color.BLACK);
        }
        return "Tahap " + step;
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder {
        private TextView textTime, textNamaFile, textBulat, textStep;
        private CardView imgBulat;

        public bidangViewHolder(View itemView) {
            super(itemView);

            textTime = itemView.findViewById(R.id.textTime);
            textNamaFile = itemView.findViewById(R.id.textNamaFile);
            textBulat = itemView.findViewById(R.id.textBulat);
            textStep = itemView.findViewById(R.id.textStep);
            imgBulat = itemView.findViewById(R.id.imgBulat);
        }
    }
}
