package com.example.manhdqph20768_assignment_duanmau.Model;

public class ThuThu {
    private String maTT;
    private String hoTen;
    private String matkhau;

    public ThuThu() {
    }

    public ThuThu(String maTT, String hoTen, String matkhau) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matkhau = matkhau;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
