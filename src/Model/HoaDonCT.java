/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class HoaDonCT {
    private String maHoaDonct,mahd,maSanPhamct,ngaygiaodich,tensp;
    private int soluong;
    private  int tongtien;
    private Double giaban;
    private boolean trangthai;

    public HoaDonCT() {
    }

    public HoaDonCT(String maHoaDonct, String mahd, String maSanPhamct, String ngaygiaodich, String tensp, int soluong, int tongtien, Double giaban, boolean trangthai) {
        this.maHoaDonct = maHoaDonct;
        this.mahd = mahd;
        this.maSanPhamct = maSanPhamct;
        this.ngaygiaodich = ngaygiaodich;
        this.tensp = tensp;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.giaban = giaban;
        this.trangthai = trangthai;
    }

    public String getMaHoaDonct() {
        return maHoaDonct;
    }

    public void setMaHoaDonct(String maHoaDonct) {
        this.maHoaDonct = maHoaDonct;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMaSanPhamct() {
        return maSanPhamct;
    }

    public void setMaSanPhamct(String maSanPhamct) {
        this.maSanPhamct = maSanPhamct;
    }

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public Double getGiaban() {
        return giaban;
    }

    public void setGiaban(Double giaban) {
        this.giaban = giaban;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public String getmahd() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}