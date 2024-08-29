/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connect.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.DangNhap;
import java.util.HashMap;
import java.util.Map;
import main.FormDangnhap;

/**
 *
 * @author LENOVO
 */
public class UserDao {

    private static Map<String, String> users = new HashMap<>();
    private static Map<String, String> roles = new HashMap<>();
    private static Map<String, String> fullNames = new HashMap<>();

    static {
        // Thêm người dùng và mật khẩu vào bản đồ
        users.put("nv1", "123");
        users.put("nv2", "456");
        users.put("nv3", "888");
        users.put("admin", "admin123");

        // Thêm người dùng và vai trò vào bản đồ
        roles.put("nv1", "user");
        roles.put("nv2", "user");
        roles.put("nv3", "user");
        roles.put("admin", "admin");
        // Thêm người dùng và tên đầy đủ vào bản đồ
        fullNames.put("admin", "Nguyễn Đình Bách");
        fullNames.put("nv1", "Nguyễn Đình A");
        fullNames.put("nv2", "Nguyễn Đình B");
        fullNames.put("nv3", "Nguyễn Đình C");
    }

    public static boolean checkLogin(String username, String password) {
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public static String getUserRole(String username) {
        return roles.get(username);
    }
    public static String getUserFullName(String username) {
        return fullNames.get(username);
    }

   //
////    }
//    public static boolean checkLogin(String username, String password) {
//        int row = 0;
//        try {
//            Connection con = DBConnect.getConnection();
//            Statement stm = con.createStatement();
//            String sql = "select TENTK,MATKHAU from TAIKHOANNV  where TENTK = '"+username+"' and MATKHAU='"+ password+"'";
//            ResultSet rs = stm.executeQuery(sql);
//            while(rs.next()){
//                row = 1;
//                String un = rs.getString(1);
//                String pwd = rs.getString(2);
//                
//                FormDangnhap.User = new DangNhap(un, pwd);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return (row>0);
//    }
//    
//    public static String getUserRole(String username) {
//        // Logic để lấy quyền của người dùng dựa trên username
//        // Ví dụ:
//        if (username.equals("admin")) {
//            return "admin";
//        } else {
//            return "user";
//        }

}
