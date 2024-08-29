/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.ThongKe;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.DriverManager;

/**
 *
 * @author 84347
 */
public class ThongKeService {
     private Connection connect() {
        // Database connection details
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DB_BANNUOCHOANAM_DA11;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "123456";
        
        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

     public ArrayList<ThongKe> getAllThongKe(String filterType) {
        ArrayList<ThongKe> filteredThongKe = new ArrayList<>();
        String query;

        if (filterType.equals("Theo ngày")) {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            query = "SELECT * FROM THONGKE_DOANHSO WHERE CAST(NGAYTHONGKE AS DATE) = ?";
            executeQuery(query, filteredThongKe, today);
        } else if (filterType.equals("Theo tháng")) {
            String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
            query = "SELECT * FROM THONGKE_DOANHSO WHERE FORMAT(NGAYTHONGKE, 'yyyy-MM') = ?";
            executeQuery(query, filteredThongKe, thisMonth + "-01"); // Appending '-01' to match the date format
        } else {
            query = "SELECT * FROM THONGKE_DOANHSO";
            executeQuery(query, filteredThongKe, null);
        }
        return filteredThongKe;
    }
      
     private void executeQuery(String query, ArrayList<ThongKe> filteredThongKe, String dateParam) {
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            if (dateParam != null) {
                pstmt.setString(1, dateParam);
            }
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ThongKe tk = new ThongKe(
                    rs.getInt("MATHONGKE"), 
                    rs.getDate("NGAYTHONGKE"),     
                    rs.getInt("MASP"),                     
                    rs.getInt("SOLUONGBAN"), 
                    rs.getInt("TONGDOANHSO")
                );
                filteredThongKe.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

    public int getTongSoHoaDon() {
        int tongSoHoaDon = 0;
        try {
            // Tạo kết nối đến database
            Connection cn = Connect.DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS tongSoHoaDon FROM HOADON";
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tongSoHoaDon = rs.getInt("tongSoHoaDon");
            }

            rs.close();
            stmt.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSoHoaDon;
    }

    public int getTongSoHoaDonThanhCong() {
        return getTongSoHoaDonTheoTrangThai(1); // Trạng thái thành công là 1
    }

    public int getTongSoHoaDonHuy() {
        return getTongSoHoaDonTheoTrangThai(0); // Trạng thái hủy là 0
    }

    private int getTongSoHoaDonTheoTrangThai(int trangThai) {
        int tongSo = 0;
        try {
            Connection cn = Connect.DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS tongSo FROM HOADON WHERE TRANGTHAI = " + trangThai;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tongSo = rs.getInt("tongSo");
            }

            rs.close();
            stmt.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSo;
    }

}
