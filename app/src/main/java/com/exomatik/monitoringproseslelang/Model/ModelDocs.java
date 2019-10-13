package com.exomatik.monitoringproseslelang.Model;

public class ModelDocs {
    String nama, tanggal;

    public ModelDocs() {
    }

    public ModelDocs(String nama, String tanggal) {
        this.nama = nama;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
