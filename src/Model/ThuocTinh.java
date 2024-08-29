/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author 84347
 */
public class ThuocTinh {
    private int stt;
    private String loaiTt;
    private String tenTt;

    public ThuocTinh() {
    }

    public ThuocTinh(int stt, String loaiTt, String tenTt) {
        this.stt = stt;
        this.loaiTt = loaiTt;
        this.tenTt = tenTt;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getLoaiTt() {
        return loaiTt;
    }

    public void setLoaiTt(String loaiTt) {
        this.loaiTt = loaiTt;
    }

    public String getTenTt() {
        return tenTt;
    }

    public void setTenTt(String tenTt) {
        this.tenTt = tenTt;
    }
    
}
