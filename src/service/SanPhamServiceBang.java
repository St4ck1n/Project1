/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connect.DBConnect;

import Model.SanPhamBang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class SanPhamServiceBang {
    
public ArrayList<SanPhamBang> getAllSanPham() {
        ArrayList<SanPhamBang> lstSP = new ArrayList<>();
        Connection cn = DBConnect.getConnection();
        String sql = "SELECT *\n"
                + "FROM SANPHAMCT AS spct\n"
                + "INNER JOIN SANPHAM AS sp ON spct.MASP = sp.MASP\n"
                + "INNER JOIN THUONGHIEU AS th ON spct.MATHUONGHIEU = th.MATHUONGHIEU\n"
                + "INNER JOIN MUIHUONG AS mh ON spct.MAMUIHUONG = mh.MAMUIHUONG\n"
                + "INNER JOIN HINHANH AS ha ON spct.IDHinhAnh = ha.ID;";
        Statement stm;
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPhamBang sp = new SanPhamBang();
                sp.setMaSP(rs.getInt("MASPCT"));
                sp.setTenSp(rs.getString("TENSP"));
                sp.setDonGia(rs.getDouble("GIA"));
                sp.setSoLuong(rs.getInt("SOLUONG"));
                sp.setDungTich(rs.getInt("DUNGTICH"));
                sp.setMuiHuong(rs.getString("TENMUIHUONG"));
                sp.setThuongHieu(rs.getString("TENTHUONGHIEU"));
                sp.setHinhAnh(rs.getString("IMG"));
                sp.setTrangThai(rs.getBoolean("TRANGTHAI"));
                sp.setNgayTao(rs.getDate("NGAYTAO"));
                sp.setNgaySua(rs.getDate("NGAYSUA"));

                lstSP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstSP;
    }

public DefaultComboBoxModel<String> getAllTenSp() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            String query = "SELECT TENSP FROM SANPHAM";
            Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String thuongHieu = rs.getString("TENSP");
                model.addElement(thuongHieu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }
    
    public DefaultComboBoxModel<String> getAllMuiHuong() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            String query = "SELECT TENMUIHUONG FROM MUIHUONG";
            Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String muiHuong = rs.getString("TENMUIHUONG");
                model.addElement(muiHuong);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }
    
    public DefaultComboBoxModel<String> getAllThuongHieu() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            String query = "SELECT TENTHUONGHIEU FROM THUONGHIEU";
            Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String thuongHieu = rs.getString("TENTHUONGHIEU");
                model.addElement(thuongHieu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }
    
    public DefaultComboBoxModel<String> getAllHinhAnh() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            String query = "SELECT IMG FROM HINHANH";
            Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String thuongHieu = rs.getString("IMG");
                model.addElement(thuongHieu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }
    
   

    
   public boolean add(SanPhamBang sanPhamCT) {
        boolean success = false;
        Connection cn = DBConnect.getConnection();
        String sql = "INSERT INTO SANPHAMCT (MASP, GIA, MATHUONGHIEU, MAMUIHUONG, IDHinhAnh, SOLUONG, DUNGTICH, TRANGTHAI, NGAYTAO, NGAYSUA) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        
        try {
            // Get MASP from SANPHAM table
            String getMASPQuery = "SELECT MASP FROM SANPHAM WHERE TENSP = ?";
            PreparedStatement getMASPStatement = cn.prepareStatement(getMASPQuery);
            getMASPStatement.setString(1, sanPhamCT.getTenSp());
            ResultSet maspResult = getMASPStatement.executeQuery();
            if (!maspResult.next()) {
                throw new SQLException("Cannot find MASP for TENSP: " + sanPhamCT.getTenSp());
            }
            int masp = maspResult.getInt("MASP");

            // Get MATHUONGHIEU from THUONGHIEU table
            String getMATHUONGHIEUQuery = "SELECT MATHUONGHIEU FROM THUONGHIEU WHERE TENTHUONGHIEU = ?";
            PreparedStatement getMATHUONGHIEUStatement = cn.prepareStatement(getMATHUONGHIEUQuery);
            getMATHUONGHIEUStatement.setString(1, sanPhamCT.getThuongHieu());
            ResultSet mathuonghieuResult = getMATHUONGHIEUStatement.executeQuery();
            if (!mathuonghieuResult.next()) {
                throw new SQLException("Cannot find MATHUONGHIEU for TENTHUONGHIEU: " + sanPhamCT.getThuongHieu());
            }
            int mathuonghieu = mathuonghieuResult.getInt("MATHUONGHIEU");

            // Get MAMUIHUONG from MUIHUONG table
            String getMAMUIHUONGQuery = "SELECT MAMUIHUONG FROM MUIHUONG WHERE TENMUIHUONG = ?";
            PreparedStatement getMAMUIHUONGStatement = cn.prepareStatement(getMAMUIHUONGQuery);
            getMAMUIHUONGStatement.setString(1, sanPhamCT.getMuiHuong());
            ResultSet mamuihuongResult = getMAMUIHUONGStatement.executeQuery();
            if (!mamuihuongResult.next()) {
                throw new SQLException("Cannot find MAMUIHUONG for TENMUIHUONG: " + sanPhamCT.getMuiHuong());
            }
            int mamuihuong = mamuihuongResult.getInt("MAMUIHUONG");

            // Get IDHinhAnh from HINHANH table
            String getIDHinhAnhQuery = "SELECT ID FROM HINHANH WHERE IMG = ?";
            PreparedStatement getIDHinhAnhStatement = cn.prepareStatement(getIDHinhAnhQuery);
            getIDHinhAnhStatement.setString(1, sanPhamCT.getHinhAnh());
            ResultSet idHinhAnhResult = getIDHinhAnhStatement.executeQuery();
            if (!idHinhAnhResult.next()) {
                throw new SQLException("Cannot find IDHinhAnh for IMG: " + sanPhamCT.getHinhAnh());
            }
            int idHinhAnh = idHinhAnhResult.getInt("ID");

            // Insert into SANPHAMCT
            PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, masp);
            ps.setDouble(2, sanPhamCT.getDonGia());
            ps.setInt(3, mathuonghieu);
            ps.setInt(4, mamuihuong);
            ps.setInt(5, idHinhAnh);
            ps.setInt(6, sanPhamCT.getSoLuong());
            ps.setInt(7, sanPhamCT.getDungTich());
            ps.setBoolean(8, sanPhamCT.isTrangThai());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating SANPHAMCT failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int maspct = generatedKeys.getInt(1);
                sanPhamCT.setMaSP(maspct);
                success = true;
            } else {
                throw new SQLException("Creating SANPHAMCT failed, no ID obtained.");
            }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return success;
    }

    public boolean update(SanPhamBang sanPhamCT) {
    boolean success = false;
    Connection cn = DBConnect.getConnection();
    String sql = "UPDATE SANPHAMCT SET GIA = ?, MATHUONGHIEU = ?, MAMUIHUONG = ?, IDHinhAnh = ?, SOLUONG = ?, DUNGTICH = ?, TRANGTHAI = ?, NGAYSUA = CURRENT_TIMESTAMP WHERE MASP = ?";
    
    try {
        // Lấy MASP từ bảng SANPHAM
        String getMASPQuery = "SELECT MASP FROM SANPHAM WHERE TENSP = ?";
        PreparedStatement getMASPStatement = cn.prepareStatement(getMASPQuery);
        getMASPStatement.setString(1, sanPhamCT.getTenSp());
        ResultSet maspResult = getMASPStatement.executeQuery();
        if (!maspResult.next()) {
            throw new SQLException("Không tìm thấy MASP cho TENSP: " + sanPhamCT.getTenSp());
        }
        int masp = maspResult.getInt("MASP");

        // Lấy MATHUONGHIEU từ bảng THUONGHIEU
        String getMATHUONGHIEUQuery = "SELECT MATHUONGHIEU FROM THUONGHIEU WHERE TENTHUONGHIEU = ?";
        PreparedStatement getMATHUONGHIEUStatement = cn.prepareStatement(getMATHUONGHIEUQuery);
        getMATHUONGHIEUStatement.setString(1, sanPhamCT.getThuongHieu());
        ResultSet mathuonghieuResult = getMATHUONGHIEUStatement.executeQuery();
        if (!mathuonghieuResult.next()) {
            throw new SQLException("Không tìm thấy MATHUONGHIEU cho TENTHUONGHIEU: " + sanPhamCT.getThuongHieu());
        }
        int mathuonghieu = mathuonghieuResult.getInt("MATHUONGHIEU");

        // Lấy MAMUIHUONG từ bảng MUIHUONG
        String getMAMUIHUONGQuery = "SELECT MAMUIHUONG FROM MUIHUONG WHERE TENMUIHUONG = ?";
        PreparedStatement getMAMUIHUONGStatement = cn.prepareStatement(getMAMUIHUONGQuery);
        getMAMUIHUONGStatement.setString(1, sanPhamCT.getMuiHuong());
        ResultSet mamuihuongResult = getMAMUIHUONGStatement.executeQuery();
        if (!mamuihuongResult.next()) {
            throw new SQLException("Không tìm thấy MAMUIHUONG cho TENMUIHUONG: " + sanPhamCT.getMuiHuong());
        }
        int mamuihuong = mamuihuongResult.getInt("MAMUIHUONG");

        // Lấy IDHinhAnh từ bảng HINHANH
        String getIDHinhAnhQuery = "SELECT ID FROM HINHANH WHERE IMG = ?";
        PreparedStatement getIDHinhAnhStatement = cn.prepareStatement(getIDHinhAnhQuery);
        getIDHinhAnhStatement.setString(1, sanPhamCT.getHinhAnh());
        ResultSet idHinhAnhResult = getIDHinhAnhStatement.executeQuery();
        if (!idHinhAnhResult.next()) {
            throw new SQLException("Không tìm thấy IDHinhAnh cho IMG: " + sanPhamCT.getHinhAnh());
        }
        int idHinhAnh = idHinhAnhResult.getInt("ID");

        // Thực hiện câu lệnh UPDATE trong bảng SANPHAMCT
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setDouble(1, sanPhamCT.getDonGia());
        ps.setInt(2, mathuonghieu);
        ps.setInt(3, mamuihuong);
        ps.setInt(4, idHinhAnh);
        ps.setInt(5, sanPhamCT.getSoLuong());
        ps.setInt(6, sanPhamCT.getDungTich());
        ps.setBoolean(7, sanPhamCT.isTrangThai());
        ps.setInt(8, masp);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            success = true;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    return success;
}

public boolean updateSoLuong(int maSP, int soLuong) {
        Connection cn = null;
        PreparedStatement ps = null;
        boolean success = false;
        try {
            cn = DBConnect.getConnection();
            String sql = "UPDATE SANPHAM SET SoLuong = SoLuong - ? WHERE MaSP = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setInt(2, maSP);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }
}

