package com.exomatik.monitoringproseslelang.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by IrfanRZ on 21/09/2018.
 */

public class fragStepMemo extends Fragment {
    private View view;
    private ImageView imgQr;
    private ModelContract dataContract;
    private TextView textJudul, textNoRak, textLokasi;

    public fragStepMemo(ModelContract dataContract) {
        this.dataContract = dataContract;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_step_memo, container, false);

        init();
        generateQrCode();
        setData();
        onClick();

        return view;
    }

    private void init() {
        imgQr = view.findViewById(R.id.imgQr);
        textJudul = view.findViewById(R.id.textJudul);
        textNoRak = view.findViewById(R.id.textNoRak);
        textLokasi = view.findViewById(R.id.textLokasi);
    }

    private void generateQrCode() {
        String str = dataContract.getIdProyek();

        MultiFormatWriter localMultiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix localBitMatrix = localMultiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 800, 800);
            Bitmap localBitmap = new BarcodeEncoder().createBitmap(localBitMatrix);
            imgQr.setImageBitmap(localBitmap);
            return;
        } catch (Exception localException) {
            Toast.makeText(getActivity(), localException.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        textJudul.setText("Nama Contract : " + dataContract.getJudulproyek());
        textNoRak.setText("Nomor Rak        : " + dataContract.getNoRak());
        textLokasi.setText("Lokasi Kerja      : " + dataContract.getLokasikerja());
    }


    private void onClick() {
    }

}
