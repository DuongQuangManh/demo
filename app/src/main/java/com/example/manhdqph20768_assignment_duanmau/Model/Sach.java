package com.example.manhdqph20768_assignment_duanmau.Model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private int soLuong;
    private int maLoai;


    public Sach(int maSach, String tenSach, int giaThue, int soLuong, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
    }

    public Sach() {
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
