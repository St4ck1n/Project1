/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Connect.DBConnect;
import java.sql.Connection;
import Model.khuyenmai;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class KhuyenmaiService {
    private static Connection con = null;
    private Connection c = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    public ArrayList<khuyenmai> getAll(){
          
          sql = "SELECT * FROM KHUYENMAI";
          ArrayList<khuyenmai>getlist = new ArrayList<>();
          try {
              c= DBConnect.getConnection();
              ps = c.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {
              int makm, giatri;
              String tenkm,ngaybd,ngaykt;
              boolean trangthai;
              makm = rs.getInt(1);
              tenkm = rs.getString(2);
              ngaybd =rs.getString(3);
              ngaykt = rs.getString(4);
              giatri = rs.getInt(5);
              trangthai = rs.getBoolean(6);
              khuyenmai km = new khuyenmai(makm, giatri, tenkm, ngaybd, ngaykt, trangthai);
              getlist.add(km);
                  
              }
              return getlist;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
   public int them(khuyenmai km) {
        sql = "insert into KHUYENMAI(TEN,NGAYBATDAU,NGAYKETTHUC,GIATRI,TRANGTHAI) values (?,?,?,?,?)";
        try {

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getTenkm());
            ps.setObject(2, km.getNgaybatdau());
            ps.setObject(3, km.getNgaykeythuc());
            ps.setObject(4, km.getGiatri());
            ps.setObject(5, km.isTrangthai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int xoa(khuyenmai km) {
        sql = "delete from KHUYENMAI where MAKHUYENMAI = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getMakm());
            return ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int sua(int makm, khuyenmai km) {
        sql = "update KHUYENMAI set TEN=?,NGAYBATDAU=?,NGAYKETTHUC=?,TRANGTHAI=? where MAKHUYENMAI=?";
        try {
            con=DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getTenkm());
            ps.setObject(2, km.getNgaybatdau());
            ps.setObject(3, km.getNgaykeythuc());
            ps.setObject(4, km.isTrangthai());
            ps.setObject(5, makm);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
