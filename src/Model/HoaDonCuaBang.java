/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class HoaDonCuaBang {
    
    private int maHd,makh,manv,mahttt,mahdct,soluong,dongia,tongtien,masp;
    String tensp,ngaysuahd,ngaysuasp,trangthaihd,trangthaisp;
    Date ngaytaohd,ngaytaosp;
    

    public HoaDonCuaBang() {
    }

    public HoaDonCuaBang(int maHd, int makh, int manv, int mahttt, Date ngaytaohd, String ngaysuahd, String trangthaihd) {
        this.maHd = maHd;
        this.makh = makh;
        this.manv = manv;
        this.mahttt = mahttt;
        this.ngaytaohd = ngaytaohd;
        this.ngaysuahd = ngaysuahd;
        this.trangthaihd = trangthaihd;
    }

    public HoaDonCuaBang(int masp,  String tensp,String trangthaisp, Date ngaytaosp, String ngaysuasp,int mahdct, int soluong, int dongia, int tongtien) {
       
        this.mahdct = mahdct;
        this.soluong = soluong;
        this.dongia = dongia;
        this.tongtien = tongtien;
        this.masp = masp;
        this.tensp = tensp;
        this.ngaysuasp = ngaysuasp;
        this.trangthaisp = trangthaisp;
        this.ngaytaosp = ngaytaosp;
    }

    
    

   

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
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

    public int getMahdct() {
        return mahdct;
    }

    public void setMahdct(int mahdct) {
        this.mahdct = mahdct;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public Date getNgaytaohd() {
        return ngaytaohd;
    }

    public void setNgaytaohd(Date ngaytaohd) {
        this.ngaytaohd = ngaytaohd;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Date getNgaytaosp() {
        return ngaytaosp;
    }

    public void setNgaytaosp(Date ngaytaosp) {
        this.ngaytaosp = ngaytaosp;
    }

    public String getNgaysuahd() {
        return ngaysuahd;
    }

    public void setNgaysuahd(String ngaysuahd) {
        this.ngaysuahd = ngaysuahd;
    }

    public String getNgaysuasp() {
        return ngaysuasp;
    }

    public void setNgaysuasp(String ngaysuasp) {
        this.ngaysuasp = ngaysuasp;
    }

    public String getTrangthaihd() {
        return trangthaihd;
    }

    public void setTrangthaihd(String trangthaihd) {
        this.trangthaihd = trangthaihd;
    }

    public String getTrangthaisp() {
        return trangthaisp;
    }

    public void setTrangthaisp(String trangthaisp) {
        this.trangthaisp = trangthaisp;
    }

    

   

   
    
    
    
    
    
    
    public Object[] toDatarow() {
        return new Object[]{this.maHd, this.makh, this.manv, this.mahttt,this.trangthaihd, this.ngaytaohd,this.ngaysuahd,this.masp,this.tensp,this.trangthaisp,this.ngaytaosp,this.ngaysuasp,this.mahdct,this.soluong,this.dongia,this.tongtien };
    }
    public Object[] toData() {
        return new Object[]{this.masp,this.tensp,this.trangthaisp,this.ngaytaosp,this.ngaysuasp,this.mahdct,this.soluong,this.dongia,this.tongtien };
    }
}



    

