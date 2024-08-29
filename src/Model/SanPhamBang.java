/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class SanPhamBang {
    
private Integer maSP;
    private String tenSp;
    private double donGia;
    private int soLuong;
    private int dungTich;
    private String muiHuong;
    private String thuongHieu;
    private boolean trangThai;
    private String hinhAnh;
    private Date ngayTao;
    private Date ngaySua;

    public SanPhamBang() {
    }

    public SanPhamBang(Integer maSP, String tenSp, double donGia, int soLuong, int dungTich, String muiHuong, String thuongHieu, boolean trangThai, String hinhAnh, Date ngayTao, Date ngaySua) {
        this.maSP = maSP;
        this.tenSp = tenSp;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.dungTich = dungTich;
        this.muiHuong = muiHuong;
        this.thuongHieu = thuongHieu;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDungTich() {
        return dungTich;
    }

    public void setDungTich(int dungTich) {
        this.dungTich = dungTich;
    }

    public String getMuiHuong() {
        return muiHuong;
    }

    public void setMuiHuong(String muiHuong) {
        this.muiHuong = muiHuong;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    
}

