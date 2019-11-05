package com.exomatik.monitoringproseslelang.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
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
    private TextView textJudul, textNoRak, textLokasi, textStep;
    private CardView llBottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ImageButton imgArrow;
    private boolean hide = true;

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
        llBottomSheet = view.findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        textJudul = llBottomSheet.findViewById(R.id.textJudul);
        textNoRak = llBottomSheet.findViewById(R.id.textNoRak);
        textLokasi = llBottomSheet.findViewById(R.id.textLokasi);
        textStep = llBottomSheet.findViewById(R.id.textStep);
        imgArrow = llBottomSheet.findViewById(R.id.imgArrow);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(220);
    }

    private void generateQrCode() {
        String str = dataContract.getIdProyek();

        MultiFormatWriter localMultiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix localBitMatrix = localMultiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 1000, 1000);
            Bitmap localBitmap = new BarcodeEncoder().createBitmap(localBitMatrix);
            imgQr.setImageBitmap(localBitmap);
            return;
        } catch (Exception localException) {
            Toast.makeText(getActivity(), localException.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        textJudul.setText("Nama Contract : " + dataContract.getJudulproyek());
        textStep.setText(setStatus(dataContract.getStep()));
        textNoRak.setText("Nomor Rak        : " + dataContract.getNoRak());
        textLokasi.setText("Lokasi Kerja      : " + dataContract.getLokasikerja());
    }

    private String setStatus(String step){
        if (step.equals("1")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_1);
        } else if (step.equals("2")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_2);
        }else if (step.equals("3")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_3);
        }else if (step.equals("4")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_4);
        }else if (step.equals("5")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_5);
        }else if (step.equals("6")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_6);
        }else if (step.equals("7")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_7);
        }else if (step.equals("8")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_8);
        }else if (step.equals("9")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_9);
        }else if (step.equals("10")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_10);
        }else if (step.equals("11")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_11);
        }else if (step.equals("12")){
            step = "Status Kontrak : " + getResources().getString(R.string.step_12);
        }else if (step.equals("13")){
            step = "Nomor Kontrak : " + dataContract.getNokontrak();
        } else {
            step = getResources().getString(R.string.step_0);
        }
        return step;
    }

    private void onClick() {
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (hide){
                    hide = false;
                }
                else {
                    hide = true;
                }
                if (i == 3){
                    imgArrow.setImageResource(R.drawable.ic_down_white);
                } else if (i == 4){
                    imgArrow.setImageResource(R.drawable.ic_up_white);
                } else{
                    imgArrow.setImageResource(R.drawable.ic_bot_top_white);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hide){
                    hide = false;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    hide = true;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }
}
