/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

/**
 *
 * @author PC
 */
public class NhanVien {
    private int manv;
    private String ten;
    private String ngaysinh;
    private String gioitinh;
    private int sdt;
    private String diachi;

    public NhanVien() {
    }

    public NhanVien(String ten, String ngaysinh, String gioitinh, int sdt, String diachi) {
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public NhanVien(int manv, String ten, String ngaysinh, String gioitinh, int sdt, String diachi ) {
        this.manv = manv;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
       
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }  
    public  Object[] todatarow(){
        return new Object[]{
            this.getManv(),this.getTen(),this.getNgaysinh(),this.getGioitinh(),this.getSdt(),this.getDiachi()
        };
    }
}
