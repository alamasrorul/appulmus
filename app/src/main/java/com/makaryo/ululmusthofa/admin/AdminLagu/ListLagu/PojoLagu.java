package com.makaryo.ululmusthofa.admin.AdminLagu.ListLagu;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class PojoLagu implements Serializable{


    private String JudulLagu;
    private String Mp3Url;
    private String PdfUrl;
    private String key;

    public String getJudulLagu() {
        return JudulLagu;
    }

    public void setJudulLagu(String judulLagu) {
        JudulLagu = judulLagu;
    }

    public String getMp3Url() {
        return Mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        Mp3Url = mp3Url;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PojoLagu(){

    }

    @Override
    public String toString() {
        return " "+ JudulLagu +"\n" +
                " "+Mp3Url +"\n" +
                " "+PdfUrl;
    }

    public PojoLagu(String nm, String mrk, String hrg){
        JudulLagu = nm;
        Mp3Url = mrk;
        PdfUrl = hrg;
    }
}