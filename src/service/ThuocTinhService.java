/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.SanPham;
import Model.ThuocTinh;
import Connect.DBConnect;

/**
 *
 * @author 84347
 */
public class ThuocTinhService {
    public ArrayList<ThuocTinh> getAllTenSP() {
        ArrayList<ThuocTinh> lstTSP = new ArrayList<>();
        Connection cn = DBConnect.getConnection();
        String sql = "SELECT * FROM SANPHAM";
        Statement stm;
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThuocTinh tsp = new ThuocTinh();
                tsp.setStt(rs.getInt("MASP"));
                tsp.setLoaiTt("Tên sản phẩm");
                tsp.setTenTt(rs.getString("TENSP"));
                
                lstTSP.add(tsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTSP;
    }
    
    public ArrayList<ThuocTinh> getAllThuongHieu() {
        ArrayList<ThuocTinh> lstTSP = new ArrayList<>();
        Connection cn = DBConnect.getConnection();
        String sql = "SELECT *FROM THUONGHIEU";
        Statement stm;
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThuocTinh tsp = new ThuocTinh();
                tsp.setStt(rs.getInt("MATHUONGHIEU"));
                tsp.setLoaiTt("Thương hiệu");
                tsp.setTenTt(rs.getString("TENTHUONGHIEU"));
                
                lstTSP.add(tsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTSP;
    }
    
    public ArrayList<ThuocTinh> getAllMuiHuong() {
        ArrayList<ThuocTinh> lstTSP = new ArrayList<>();
        Connection cn = DBConnect.getConnection();
        String sql = "SELECT *FROM MUIHUONG";
        Statement stm;
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThuocTinh tsp = new ThuocTinh();
                tsp.setStt(rs.getInt("MAMUIHUONG"));
                tsp.setLoaiTt("Mùi hương");
                tsp.setTenTt(rs.getString("TENMUIHUONG"));
                
                lstTSP.add(tsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTSP;
    }
    
    public ArrayList<ThuocTinh> getAllHinhAnh() {
        ArrayList<ThuocTinh> lstTSP = new ArrayList<>();
        Connection cn = DBConnect.getConnection();
        String sql = "SELECT *FROM HINHANH";
        Statement stm;
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThuocTinh tsp = new ThuocTinh();
                tsp.setStt(rs.getInt("ID"));
                tsp.setLoaiTt("Hình ảnh");
                tsp.setTenTt(rs.getString("IMG"));
                
                lstTSP.add(tsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTSP;
    }
    
    
    
    public boolean themTenSP(String tenSP) {
        Connection cn = DBConnect.getConnection();
        String sql = "INSERT INTO SANPHAM (TENSP, TRANGTHAI) VALUES (?, 1)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenSP);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Phương thức thêm thông tin mùi hương vào bảng MUIHUONG
    public boolean themMuiHuong(String tenMuiHuong) {
        Connection cn = DBConnect.getConnection();
        String sql = "INSERT INTO MUIHUONG (TENMUIHUONG, TRANGTHAI) VALUES (?, 1)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenMuiHuong);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Phương thức thêm thông tin thương hiệu vào bảng THUONGHIEU
    public boolean themThuongHieu(String tenThuongHieu) {
        Connection cn = DBConnect.getConnection();
        String sql = "INSERT INTO THUONGHIEU (TENTHUONGHIEU, TRANGTHAI) VALUES (?, 1)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenThuongHieu);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Phương thức thêm thông tin hình ảnh vào bảng HINHANH
    public boolean themHinhAnh(String tenHinhAnh) {
        Connection cn = DBConnect.getConnection();
        String sql = "INSERT INTO HINHANH (IMG, TRANGTHAI) VALUES (?, 1)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenHinhAnh);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    
    public boolean suaTenSP(int maSP, String tenSP) {
        Connection cn = DBConnect.getConnection();
        String sql = "UPDATE SANPHAM SET TENSP = ? WHERE MASP = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenSP);
            ps.setInt(2, maSP);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Phương thức sửa thông tin mùi hương trong bảng MUIHUONG
    public boolean suaMuiHuong(int maMuiHuong, String tenMuiHuong) {
        Connection cn = DBConnect.getConnection();
        String sql = "UPDATE MUIHUONG SET TENMUIHUONG = ? WHERE MAMUIHUONG = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenMuiHuong);
            ps.setInt(2, maMuiHuong);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Phương thức sửa thông tin thương hiệu trong bảng THUONGHIEU
    public boolean suaThuongHieu(int maThuongHieu, String tenThuongHieu) {
        Connection cn = DBConnect.getConnection();
        String sql = "UPDATE THUONGHIEU SET TENTHUONGHIEU = ? WHERE MATHUONGHIEU = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenThuongHieu);
            ps.setInt(2, maThuongHieu);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Phương thức sửa thông tin hình ảnh trong bảng HINHANH
    public boolean suaHinhAnh(int maHinhAnh, String tenHinhAnh) {
        Connection cn = DBConnect.getConnection();
        String sql = "UPDATE HINHANH SET IMG = ? WHERE ID = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tenHinhAnh);
            ps.setInt(2, maHinhAnh);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThuocTinhService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
