package com.example.duanmau_ph39815.model;

public class ThuThu {
    private String maTT;
    private String hoTenTT;
    private String matKhau;

    public ThuThu() {
    }

    public ThuThu(String maTT, String hoTenTT, String matKhau) {
        this.maTT = maTT;
        this.hoTenTT = hoTenTT;
        this.matKhau = matKhau;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTenTT() {
        return hoTenTT;
    }

    public void setHoTenTT(String hoTenTT) {
        this.hoTenTT = hoTenTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
