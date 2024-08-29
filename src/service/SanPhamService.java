/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Model.SanPham;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connect.DBConnect;

/**
 *
 * @author 84347
 */
public class SanPhamService {

    public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> lstSP = new ArrayList<>();
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
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MASPCT"));
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

}
