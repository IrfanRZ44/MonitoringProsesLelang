
package com.exomatik.monitoringproseslelang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelCekImei {

    @SerializedName("response")
    @Expose
    private String response;

    /**
     * No args constructor for use in serialization
     *
     */
    public ModelCekImei() {
    }

    /**
     *
     * @param response
     */
    public ModelCekImei(String response) {
        super();
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
