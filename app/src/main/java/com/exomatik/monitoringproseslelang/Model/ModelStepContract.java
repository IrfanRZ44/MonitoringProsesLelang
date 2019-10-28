
package com.exomatik.monitoringproseslelang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelStepContract {

    @SerializedName("id_files")
    @Expose
    private String idFiles;
    @SerializedName("nama_file")
    @Expose
    private String namaFile;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id_proyek")
    @Expose
    private String idProyek;
    @SerializedName("id_dokumen")
    @Expose
    private String idDokumen;
    @SerializedName("datetime_create")
    @Expose
    private String datetimeCreate;
    @SerializedName("response")
    @Expose
    private String response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModelStepContract() {
    }

    /**
     * 
     * @param idFiles
     * @param namaFile
     * @param idProyek
     * @param idDokumen
     * @param response
     * @param datetimeCreate
     * @param url
     */
    public ModelStepContract(String idFiles, String namaFile, String url, String idProyek, String idDokumen, String datetimeCreate, String response) {
        super();
        this.idFiles = idFiles;
        this.namaFile = namaFile;
        this.url = url;
        this.idProyek = idProyek;
        this.idDokumen = idDokumen;
        this.datetimeCreate = datetimeCreate;
        this.response = response;
    }

    public String getIdFiles() {
        return idFiles;
    }

    public void setIdFiles(String idFiles) {
        this.idFiles = idFiles;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdProyek() {
        return idProyek;
    }

    public void setIdProyek(String idProyek) {
        this.idProyek = idProyek;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getDatetimeCreate() {
        return datetimeCreate;
    }

    public void setDatetimeCreate(String datetimeCreate) {
        this.datetimeCreate = datetimeCreate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
