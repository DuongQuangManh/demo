package com.example.manhdqph20768_assignment_duanmau.Model;

public class ThanhVien {
    private int maTV;
    private String hoTen;
    private String namSinh;
    private String soTaiKhoan;

    public ThanhVien() {
    }


    public ThanhVien(int maTV, String hoTen, String namSinh, String soTaiKhoan) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.soTaiKhoan = soTaiKhoan;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }
}
