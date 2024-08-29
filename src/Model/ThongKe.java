/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author 84347
 */
public class ThongKe {
        private int maTK;
        private Date NgayTK;
        private int maSP;
        private int soLuongBan;
        private int tongDoanhSo;

    public ThongKe() {
    }

    public ThongKe(int maTK, Date NgayTK, int maSP, int soLuongBan, int tongDoanhSo) {
        this.maTK = maTK;
        this.NgayTK = NgayTK;
        this.maSP = maSP;
        this.soLuongBan = soLuongBan;
        this.tongDoanhSo = tongDoanhSo;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public Date getNgayTK() {
        return NgayTK;
    }

    public void setNgayTK(Date NgayTK) {
        this.NgayTK = NgayTK;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public int getTongDoanhSo() {
        return tongDoanhSo;
    }

    public void setTongDoanhSo(int tongDoanhSo) {
        this.tongDoanhSo = tongDoanhSo;
    }

    
}
