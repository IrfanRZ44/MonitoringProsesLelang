package com.exomatik.monitoringproseslelang.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exomatik.monitoringproseslelang.Model.ModelDocs;
import com.exomatik.monitoringproseslelang.R;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class RecyclerDocs extends RecyclerView.Adapter<RecyclerDocs.bidangViewHolder> {
    private ArrayList<ModelDocs> dataList;
    private Context context;
    private ProgressDialog progressDialog = null;
    private Activity activity;

    public RecyclerDocs(ArrayList<ModelDocs> dataList, Context context, Activity activity) {
        this.dataList = dataList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_docs, parent, false);

        this.context = parent.getContext();
        return new bidangViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(bidangViewHolder holder, final int position) {
        holder.textTitle.setText(dataList.get(position).getNama());
        holder.textTanggal.setText(dataList.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder {
        private TextView textTanggal, textTitle;
        private RelativeLayout rlDate;

        public bidangViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textTanggal = itemView.findViewById(R.id.textTanggal);
        }
    }
}
