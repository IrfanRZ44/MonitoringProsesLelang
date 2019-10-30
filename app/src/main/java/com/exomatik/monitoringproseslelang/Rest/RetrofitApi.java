package com.exomatik.monitoringproseslelang.Rest;


import com.exomatik.monitoringproseslelang.Model.ModelCekImei;
import com.exomatik.monitoringproseslelang.Model.ModelContract;
import com.exomatik.monitoringproseslelang.Model.ModelStepContract;
import com.exomatik.monitoringproseslelang.Model.ModelUser;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by IrfanRZ on 02/08/2019.
 */

public interface RetrofitApi {
    public String baseUrl = "http://dolby.mor7.com/";
    public String jsonProcess = baseUrl + "api/v1/Json_process/";

    @Headers("Accept:application/json")
    @POST("loginUser")
    Call<ArrayList<ModelUser>> signIn(@Body Map<String, String> input, @Header("Content-Type") String contentType);

    @Headers("Accept:application/json")
    @POST("getContract")
    Call<ArrayList<ModelContract>> getContract(@Body Map<String, String> input, @Header("Content-Type") String contentType);

    @Headers("Accept:application/json")
    @POST("getResultScanContract")
    Call<ArrayList<ModelContract>> getResultScanContract(@Body Map<String, String> input, @Header("Content-Type") String contentType);

    @Headers("Accept:application/json")
    @POST("getStepContract")
    Call<ArrayList<ModelStepContract>> getStepContract(@Body Map<String, String> input, @Header("Content-Type") String contentType);

    @Headers("Accept:application/json")
    @POST("cekImei")
    Call<ModelCekImei> cekImei(@Body Map<String, String> input, @Header("Content-Type") String contentType);
}
