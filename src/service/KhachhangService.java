/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connect.DBConnect;
import Model.khachhang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class KhachhangService {
ArrayList<khachhang> list = new ArrayList<>();
    public ArrayList<khachhang> Getall() {
        ArrayList<khachhang> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("select * from KHACHHANG");
            while (r.next()) {
                khachhang a = new khachhang();
                a.setMakh(r.getInt(1));
                a.setTen(r.getString(2));
                a.setNgaysinh(r.getString(3));
                a.setGioitinh(r.getString(4));
                a.setSdt(r.getInt(5));
                a.setDiachi(r.getString(6));
                a.setEmail(r.getString(7));

                list.add(a);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public boolean add(khachhang a) {
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement("INSERT INTO KHACHHANG (TENKH,NGAYSINH,GIOITINH,SDT,DIACHI,EMAIL) VALUES (?,?,?,?,?,?)");
            ps.setString(1, a.getTen());
            ps.setString(2, a.getNgaysinh());
            ps.setString(3, a.getGioitinh());
            ps.setInt(4, a.getSdt());
            ps.setString(5, a.getDiachi());
            ps.setString(6, a.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
public int Delete( khachhang a) {
    try {
        Connection c = DBConnect.getConnection();
        PreparedStatement p = c.prepareStatement("DELETE FROM KHACHHANG WHERE MAKH = ?");
        p.setInt(1, a.getMakh());
        return p.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;

}
//    public void Delete(int makh) {
//    for (khachhang kh : list) {
//        if (kh.getMakh() == makh) {
//            list.remove(kh);
//            break;
//        }
//    }
//}

    
    public void Edit(khachhang a) {
    try {
        Connection c = DBConnect.getConnection();
        PreparedStatement ps = c.prepareCall("UPDATE KHACHHANG SET TENKH=?,NGAYSINH=?,GIOITINH=?,SDT=?,DIACHI=?,EMAIL=? WHERE MAKH=?");        
        ps.setString(1, a.getTen()); // Sửa thông tin Tên khách hàng
        ps.setString(2, a.getNgaysinh()); // Sửa thông tin Ngày sinh
        ps.setString(3, a.getGioitinh()); // Sửa thông tin Giới tính
        ps.setInt(4, a.getSdt()); // Sửa thông tin Số điện thoại
        ps.setString(5, a.getDiachi()); // Sửa thông tin Địa chỉ
        ps.setString(6, a.getEmail()); // Sửa thông tin Email
        ps.setInt(7, a.getMakh()); // Sửa thông tin Mã khách hàng

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
