package com.example.timekeeping;

public class CongViec {

    private int IdCongViec;
    private String TenCV;
    private int HinhThuc;
    private int IdNguoiDung;
    private String NgayBatDau;
    private int TienLuong;
    private int TongLuong;
    private int GioThongBao;
    private int PhutThongBao;
    private String GhiChu;

    public CongViec(int idCongViec, String tenCV, int hinhThuc, int idNguoiDung, String ngayBatDau, int tienLuong, int tongLuong, int gioThongBao, int phutThongBao, String ghiChu) {
        IdCongViec = idCongViec;
        TenCV = tenCV;
        HinhThuc = hinhThuc;
        IdNguoiDung = idNguoiDung;
        NgayBatDau = ngayBatDau;
        TienLuong = tienLuong;
        TongLuong = tongLuong;
        GioThongBao = gioThongBao;
        PhutThongBao = phutThongBao;
        GhiChu = ghiChu;
    }

    public int getIdCongViec() {
        return IdCongViec;
    }

    public void setIdCongViec(int idCongViec) {
        IdCongViec = idCongViec;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }

    public int getHinhThuc() {
        return HinhThuc;
    }

    public void setHinhThuc(int hinhThuc) {
        HinhThuc = hinhThuc;
    }

    public int getIdNguoiDung() {
        return IdNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        IdNguoiDung = idNguoiDung;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public int getTienLuong() {
        return TienLuong;
    }

    public void setTienLuong(int tienLuong) {
        TienLuong = tienLuong;
    }

    public int getTongLuong() {
        return TongLuong;
    }

    public void setTongLuong(int tongLuong) {
        TongLuong = tongLuong;
    }

    public int getGioThongBao() {
        return GioThongBao;
    }

    public void setGioThongBao(int gioThongBao) {
        GioThongBao = gioThongBao;
    }

    public int getPhutThongBao() {
        return PhutThongBao;
    }

    public void setPhutThongBao(int phutThongBao) {
        PhutThongBao = phutThongBao;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
