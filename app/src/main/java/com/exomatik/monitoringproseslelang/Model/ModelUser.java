
package com.exomatik.monitoringproseslelang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelUser {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("nohp")
    @Expose
    private String nohp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("response")
    @Expose
    private String response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModelUser() {
    }

    /**
     * 
     * @param password
     * @param nama
     * @param level
     * @param response
     * @param imei
     * @param nohp
     * @param sessionId
     * @param email
     * @param username
     */
    public ModelUser(String username, String password, String level, String sessionId, String nohp, String email, String nama, String imei, String response) {
        super();
        this.username = username;
        this.password = password;
        this.level = level;
        this.sessionId = sessionId;
        this.nohp = nohp;
        this.email = email;
        this.nama = nama;
        this.imei = imei;
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
