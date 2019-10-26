
package com.exomatik.monitoringproseslelang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelContract {

    @SerializedName("id_proyek")
    @Expose
    private String idProyek;
    @SerializedName("judulproyek")
    @Expose
    private String judulproyek;
    @SerializedName("nokontrak")
    @Expose
    private String nokontrak;
    @SerializedName("no_rak")
    @Expose
    private String noRak;
    @SerializedName("lokasikerja")
    @Expose
    private String lokasikerja;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("step")
    @Expose
    private String step;
    @SerializedName("sub_step")
    @Expose
    private String subStep;
    @SerializedName("komentar")
    @Expose
    private String komentar;
    @SerializedName("tgl_permintaanproyek")
    @Expose
    private String tglPermintaanproyek;
    @SerializedName("tgl_prakualifikasi")
    @Expose
    private String tglPrakualifikasi;
    @SerializedName("tgl_aanwizijing")
    @Expose
    private Object tglAanwizijing;
    @SerializedName("tgl_pembukaansampul1")
    @Expose
    private String tglPembukaansampul1;
    @SerializedName("tgl_negosiasi")
    @Expose
    private String tglNegosiasi;
    @SerializedName("tgl_penetapanpemenang")
    @Expose
    private String tglPenetapanpemenang;
    @SerializedName("tgl_penunjukanpemenang")
    @Expose
    private String tglPenunjukanpemenang;
    @SerializedName("response")
    @Expose
    private String response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModelContract() {
    }

    /**
     * 
     * @param judulproyek
     * @param tglPrakualifikasi
     * @param nokontrak
     * @param idProyek
     * @param komentar
     * @param tglPenunjukanpemenang
     * @param tglPenetapanpemenang
     * @param tglPermintaanproyek
     * @param tglNegosiasi
     * @param tglPembukaansampul1
     * @param lokasikerja
     * @param subStep
     * @param datetime
     * @param tglAanwizijing
     * @param response
     * @param noRak
     * @param step
     */
    public ModelContract(String idProyek, String judulproyek, String nokontrak, String noRak, String lokasikerja, String datetime, String step, String subStep, String komentar, String tglPermintaanproyek, String tglPrakualifikasi, Object tglAanwizijing, String tglPembukaansampul1, String tglNegosiasi, String tglPenetapanpemenang, String tglPenunjukanpemenang, String response) {
        super();
        this.idProyek = idProyek;
        this.judulproyek = judulproyek;
        this.nokontrak = nokontrak;
        this.noRak = noRak;
        this.lokasikerja = lokasikerja;
        this.datetime = datetime;
        this.step = step;
        this.subStep = subStep;
        this.komentar = komentar;
        this.tglPermintaanproyek = tglPermintaanproyek;
        this.tglPrakualifikasi = tglPrakualifikasi;
        this.tglAanwizijing = tglAanwizijing;
        this.tglPembukaansampul1 = tglPembukaansampul1;
        this.tglNegosiasi = tglNegosiasi;
        this.tglPenetapanpemenang = tglPenetapanpemenang;
        this.tglPenunjukanpemenang = tglPenunjukanpemenang;
        this.response = response;
    }

    public String getIdProyek() {
        return idProyek;
    }

    public void setIdProyek(String idProyek) {
        this.idProyek = idProyek;
    }

    public String getJudulproyek() {
        return judulproyek;
    }

    public void setJudulproyek(String judulproyek) {
        this.judulproyek = judulproyek;
    }

    public String getNokontrak() {
        return nokontrak;
    }

    public void setNokontrak(String nokontrak) {
        this.nokontrak = nokontrak;
    }

    public String getNoRak() {
        return noRak;
    }

    public void setNoRak(String noRak) {
        this.noRak = noRak;
    }

    public String getLokasikerja() {
        return lokasikerja;
    }

    public void setLokasikerja(String lokasikerja) {
        this.lokasikerja = lokasikerja;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getSubStep() {
        return subStep;
    }

    public void setSubStep(String subStep) {
        this.subStep = subStep;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getTglPermintaanproyek() {
        return tglPermintaanproyek;
    }

    public void setTglPermintaanproyek(String tglPermintaanproyek) {
        this.tglPermintaanproyek = tglPermintaanproyek;
    }

    public String getTglPrakualifikasi() {
        return tglPrakualifikasi;
    }

    public void setTglPrakualifikasi(String tglPrakualifikasi) {
        this.tglPrakualifikasi = tglPrakualifikasi;
    }

    public Object getTglAanwizijing() {
        return tglAanwizijing;
    }

    public void setTglAanwizijing(Object tglAanwizijing) {
        this.tglAanwizijing = tglAanwizijing;
    }

    public String getTglPembukaansampul1() {
        return tglPembukaansampul1;
    }

    public void setTglPembukaansampul1(String tglPembukaansampul1) {
        this.tglPembukaansampul1 = tglPembukaansampul1;
    }

    public String getTglNegosiasi() {
        return tglNegosiasi;
    }

    public void setTglNegosiasi(String tglNegosiasi) {
        this.tglNegosiasi = tglNegosiasi;
    }

    public String getTglPenetapanpemenang() {
        return tglPenetapanpemenang;
    }

    public void setTglPenetapanpemenang(String tglPenetapanpemenang) {
        this.tglPenetapanpemenang = tglPenetapanpemenang;
    }

    public String getTglPenunjukanpemenang() {
        return tglPenunjukanpemenang;
    }

    public void setTglPenunjukanpemenang(String tglPenunjukanpemenang) {
        this.tglPenunjukanpemenang = tglPenunjukanpemenang;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
