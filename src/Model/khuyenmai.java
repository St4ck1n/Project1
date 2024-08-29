/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class khuyenmai {
    private int makm,giatri;
    
    private String tenkm,ngaybatdau,ngaykeythuc;
    private boolean trangthai;

    public khuyenmai() {
    }

    public int getMakm() {
        return makm;
    }

    public void setMakm(int makm) {
        this.makm = makm;
    }

    public int getGiatri() {
        return giatri;
    }

    public void setGiatri(int giatri) {
        this.giatri = giatri;
    }

    public String getTenkm() {
        return tenkm;
    }

    public void setTenkm(String tenkm) {
        this.tenkm = tenkm;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgaykeythuc() {
        return ngaykeythuc;
    }

    public void setNgaykeythuc(String ngaykeythuc) {
        this.ngaykeythuc = ngaykeythuc;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public khuyenmai(int makm, int giatri, String tenkm, String ngaybatdau, String ngaykeythuc, boolean trangthai) {
        this.makm = makm;
        this.giatri = giatri;
        this.tenkm = tenkm;
        this.ngaybatdau = ngaybatdau;
        this.ngaykeythuc = ngaykeythuc;
        this.trangthai = trangthai;
    }

    public khuyenmai(int giatri, String tenkm, String ngaybatdau, String ngaykeythuc, boolean trangthai) {
        this.giatri = giatri;
        this.tenkm = tenkm;
        this.ngaybatdau = ngaybatdau;
        this.ngaykeythuc = ngaykeythuc;
        this.trangthai = trangthai;
    }
    

   public Object[]toDatarow(){
       return new  Object[]{this.makm,this.tenkm,this.ngaybatdau,this.ngaykeythuc,this.giatri,this.trangthai?"đang hoạt động":"kết thúc"};
   }
   
}

