/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Connect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.NhanVienBang;
public class NhanVienService {

    public ArrayList<NhanVienBang> Getall() {
        ArrayList<NhanVienBang> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("select * from NHANVIEN");
            while (r.next()) {
                NhanVienBang nv = new NhanVienBang();
                nv.setManv(r.getInt(1));
                nv.setTen(r.getString(2));
                nv.setNgaysinh(r.getString(3));
                nv.setGioitinh(r.getString(4));
                nv.setSdt(r.getInt(5));
                nv.setDiachi(r.getString(6));
                nv.setTrangthai(r.getBoolean(7));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public boolean add(NhanVienBang nv) {
    PreparedStatement ps = null;
    Connection c = null;
    try {
        c = DBConnect.getConnection();
        ps = c.prepareStatement("INSERT INTO NHANVIEN (TENNV,NGAYSINH,GIOITINH,SDT,DIACHI,TRANGTHAI) VALUES (?,?,?,?,?,?)");
        ps.setString(1, nv.getTen());
        ps.setString(2, nv.getNgaysinh());
        ps.setString(3, nv.getGioitinh());
        ps.setInt(4, nv.getSdt());
        ps.setString(5, nv.getDiachi());
        ps.setBoolean(6, nv.isTrangthai());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0; // Trả về true nếu có ít nhất một hàng được tác động
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Đảm bảo đóng kết nối và tài nguyên khi không cần thiết
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return false;
}
public int Delete(NhanVienBang nv) {
    try {
        Connection c = DBConnect.getConnection();
        PreparedStatement p = c.prepareStatement("DELETE FROM NHANVIEN WHERE MANV=?");
        p.setInt(1, nv.getManv());
        return p.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
    public void Edit(NhanVienBang a) {
    try {
        Connection c = DBConnect.getConnection();
        PreparedStatement ps = c.prepareCall("UPDATE NHANVIEN SET TENNV=?,NGAYSINH=?,GIOITINH=?,SDT=?,DIACHI=?,TRANGTHAI=? WHERE MANV =?");        
        ps.setString(1, a.getTen()); // Sửa thông tin Tên khách hàng
        ps.setString(2, a.getNgaysinh()); // Sửa thông tin Ngày sinh
        ps.setString(3, a.getGioitinh()); // Sửa thông tin Giới tính
        ps.setInt(4, a.getSdt()); // Sửa thông tin Số điện thoại
        ps.setString(5, a.getDiachi()); // Sửa thông tin Địa chỉ
        ps.setBoolean(6, a.isTrangthai());
        ps.setInt(7, a.getManv()); // Sửa thông tin Mã khách hàng
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
