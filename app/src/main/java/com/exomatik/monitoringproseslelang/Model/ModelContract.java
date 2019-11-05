
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
    @SerializedName("tgl_pratender")
    @Expose
    private String tglPratender;
    @SerializedName("tgl_bidderlist")
    @Expose
    private String tglBidderlist;
    @SerializedName("tgl_rfq")
    @Expose
    private String tglRfq;
    @SerializedName("tgl_prakualifikasi")
    @Expose
    private String tglPrakualifikasi;
    @SerializedName("tgl_aanwijizing")
    @Expose
    private String tglAanwijizing;
    @SerializedName("tgl_pembukaansampul1")
    @Expose
    private String tglPembukaansampul1;
    @SerializedName("tgl_pembukaansampul2")
    @Expose
    private String tglPembukaansampul2;
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
     * @param tglBidderlist
     * @param tglPrakualifikasi
     * @param nokontrak
     * @param tglPratender
     * @param idProyek
     * @param komentar
     * @param tglPenunjukanpemenang
     * @param tglPenetapanpemenang
     * @param tglPermintaanproyek
     * @param tglNegosiasi
     * @param tglPembukaansampul2
     * @param tglRfq
     * @param tglPembukaansampul1
     * @param lokasikerja
     * @param subStep
     * @param datetime
     * @param response
     * @param noRak
     * @param step
     * @param tglAanwijizing
     */
    public ModelContract(String idProyek, String judulproyek, String nokontrak, String noRak, String lokasikerja, String datetime, String step, String subStep, String komentar, String tglPermintaanproyek, String tglPratender, String tglBidderlist, String tglRfq, String tglPrakualifikasi, String tglAanwijizing, String tglPembukaansampul1, String tglPembukaansampul2, String tglNegosiasi, String tglPenetapanpemenang, String tglPenunjukanpemenang, String response) {
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
        this.tglPratender = tglPratender;
        this.tglBidderlist = tglBidderlist;
        this.tglRfq = tglRfq;
        this.tglPrakualifikasi = tglPrakualifikasi;
        this.tglAanwijizing = tglAanwijizing;
        this.tglPembukaansampul1 = tglPembukaansampul1;
        this.tglPembukaansampul2 = tglPembukaansampul2;
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

    public String getTglPratender() {
        return tglPratender;
    }

    public void setTglPratender(String tglPratender) {
        this.tglPratender = tglPratender;
    }

    public String getTglBidderlist() {
        return tglBidderlist;
    }

    public void setTglBidderlist(String tglBidderlist) {
        this.tglBidderlist = tglBidderlist;
    }

    public String getTglRfq() {
        return tglRfq;
    }

    public void setTglRfq(String tglRfq) {
        this.tglRfq = tglRfq;
    }

    public String getTglPrakualifikasi() {
        return tglPrakualifikasi;
    }

    public void setTglPrakualifikasi(String tglPrakualifikasi) {
        this.tglPrakualifikasi = tglPrakualifikasi;
    }

    public String getTglAanwijizing() {
        return tglAanwijizing;
    }

    public void setTglAanwijizing(String tglAanwijizing) {
        this.tglAanwijizing = tglAanwijizing;
    }

    public String getTglPembukaansampul1() {
        return tglPembukaansampul1;
    }

    public void setTglPembukaansampul1(String tglPembukaansampul1) {
        this.tglPembukaansampul1 = tglPembukaansampul1;
    }

    public String getTglPembukaansampul2() {
        return tglPembukaansampul2;
    }

    public void setTglPembukaansampul2(String tglPembukaansampul2) {
        this.tglPembukaansampul2 = tglPembukaansampul2;
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
