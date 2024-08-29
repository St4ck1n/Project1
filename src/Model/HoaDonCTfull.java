/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class HoaDonCTfull {
//     public static int getmaHd() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    private int  mahd,makh,manv,mahttt;
//    private Date ngaygd;
    private boolean trangthai;
    

    public HoaDonCTfull() {}
//    public void addMatHang(GioHang gioHang) {
//        if (danhSachMatHang == null) {
//            danhSachMatHang = new ArrayList<>(); // Khởi tạo danh sách nếu chưa tồn tại
//        }
//        danhSachMatHang.add(gioHang); // Thêm mặt hàng vào danh sách
//    }

    public HoaDonCTfull(int mahd, int makh, int manv, int mahttt, boolean trangthai) {
        this.mahd = mahd;
        this.makh = makh;
        this.manv = manv;
        this.mahttt = mahttt;
//        this.ngaygd = ngaygd;
        this.trangthai = trangthai;
//        this.danhSachMatHang = danhSachMatHang;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public int getMahttt() {
        return mahttt;
    }

    public void setMahttt(int mahttt) {
        this.mahttt = mahttt;
    }

//    public Date getNgaygd() {
//        return ngaygd;
//    }
//
//    public void setNgaygd(Date ngaygd) {
//        this.ngaygd = ngaygd;
//    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

//    public List<GioHang> getDanhSachMatHang() {
//        return danhSachMatHang;
//    }

//    public void setDanhSachMatHang(List<GioHang> danhSachMatHang) {
//        this.danhSachMatHang = danhSachMatHang;
//    }

   

    
    

    

    
//     private double calculateTotalPrice() {
//        return soLuong * gia;
//    }

    public Object[] toDatarow() {
        return new Object[]{mahd, makh, manv, mahttt, trangthai ?"hủy đơn":"đã thanh toán"};
    }
    
}
