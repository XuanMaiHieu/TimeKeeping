package com.example.timekeeping;

public class NguoiDung {
    private int IdNguoiDung;
    private String TenDangNhap;
    private String MatKhau;


    public NguoiDung(int idNguoiDung, String tenDangNhap, String matKhau) {
        IdNguoiDung = idNguoiDung;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
    }

    public int getIdNguoiDung() {
        return IdNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        IdNguoiDung = idNguoiDung;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
