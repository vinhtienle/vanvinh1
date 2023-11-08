package com.example.lamthem2.model;

public class CongViec {
    private int id;
    private String tieuDe, noiDung, ngay, loai;
    private int trangThai;

    public CongViec() {
    }

    public CongViec(String tieuDe, String noiDung, String ngay, String loai) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngay = ngay;
        this.loai = loai;
    }

    public CongViec(int id, String tieuDe, String noiDung, String ngay, String loai, int trangThai) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngay = ngay;
        this.loai = loai;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
