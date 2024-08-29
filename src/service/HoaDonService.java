/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connect.DBConnect;

import java.sql.*;
import java.util.ArrayList;

import Model.HoaDonCuaBang;

public class HoaDonService {

    private static Connection c = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public HoaDonService() {
        c = DBConnect.getConnection();
    }

    public ArrayList<HoaDonCuaBang> GetAll() {
        sql = "SELECT \n"
                + "    HD.MAHD,\n"
                + "    HD.MAKH,\n"
                + "    HD.MANV,\n"
                + "    HD.MAHTTT,\n"
                + "    HD.TRANGTHAI,\n"
                + "    HD.NGAYTAO,\n"
                + "    HD.NGAYSUA\n"
                + "FROM \n"
                + "    HOADON HD";  // Sửa điều kiện join
        ArrayList<HoaDonCuaBang> LISThd = new ArrayList<>();  // Sửa kiểu dữ liệu của LISThd
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHd, makh, manv, mahttt, mahdct, soluong, dongia, tongtien, masp;
                String tensp, ngaytaosp, ngaysuahd, ngaysuasp, trangthaihd, trangthaisp;

                Date ngaytaohd;

                maHd = rs.getInt(1);
                makh = rs.getInt(2);
                manv = rs.getInt(3);
                mahttt = rs.getInt(4);
                boolean trangthaihdvalue = rs.getBoolean(5);
                if (trangthaihdvalue) {
                    trangthaihd = "đã thanh toán";

                } else {
                    trangthaihd = "đã hủy";
                }
                ngaytaohd = rs.getDate(6);
                ngaysuahd = rs.getString(7);

                HoaDonCuaBang chiTiet = new HoaDonCuaBang(maHd, makh, manv, mahttt, ngaytaohd, ngaysuahd, trangthaihd);
                LISThd.add(chiTiet);
            }
            return LISThd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDonCuaBang> Getallsp() {
        sql = "	SELECT \n"
                + "   \n"
                + "    SP.MASP,\n"
                + "   \n"
                + "    SP.TENSP, \n"
                + "    SP.TRANGTHAI AS TRANGTHAISP,\n"
                + "    SP.NGAYTAO AS NGAYTAOSP,\n"
                + "    SP.NGAYSUA AS NGAYSUASP,\n"
                + "    HDCT.MAHDCT,\n"
                + "    HDCT.SOLUONG,\n"
                + "    HDCT.DONGIA,\n"
                + "    HDCT.TONGTIEN\n"
                + "FROM \n"
                + "    HOADONCT HDCT\n"
                + "JOIN\n"
                + "    SANPHAM SP ON SP.MASP = SP.MASP";  // Sửa điều kiện join
        ArrayList<HoaDonCuaBang> LISTsp = new ArrayList<>();  // Sửa kiểu dữ liệu của LISThd
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHd, makh, manv, mahttt, mahdct, soluong, dongia, tongtien, masp;
                String ngaytaohd, tensp, ngaysuahd, ngaysuasp, trangthaihd, trangthaisp;

                Date ngaytaosp;

                masp = rs.getInt(1);
                tensp = rs.getString(2);
                boolean trangThaispValue = rs.getBoolean(3);
                if (trangThaispValue) {
                    trangthaisp = "còn hàng";
                } else {
                    trangthaisp = "hết hàng";
                }
                ngaytaosp = rs.getDate(4);
                ngaysuasp = rs.getString(5);
                mahdct = rs.getInt(6);
                soluong = rs.getInt(7);
                dongia = rs.getInt(8);
                tongtien = rs.getInt(9);

                HoaDonCuaBang chiTiet = new HoaDonCuaBang(masp, tensp, trangthaisp, ngaytaosp, ngaysuasp, mahdct, soluong, dongia, tongtien);
                LISTsp.add(chiTiet);
            }
            return LISTsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDonCuaBang> timkiem(String idcantim) {

        sql = " 	SELECT \n"
                + "    HD.MAHD,\n"
                + "    HD.MAKH,\n"
                + "    HD.MANV,\n"
                + "    HD.MAHTTT,\n"
                + "    HD.TRANGTHAI,\n"
                + "    HD.NGAYTAO,\n"
                + "    HD.NGAYSUA\n"
                + "FROM \n"
                + "    HOADON HD where HD.MAHD = ?";
        ArrayList<HoaDonCuaBang> getList = new ArrayList<>();
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement(sql);
            ps.setObject(1, idcantim);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHd, makh, manv, mahttt, mahdct, soluong, dongia, tongtien, masp;
                String tensp, ngaytaosp, ngaysuahd, ngaysuasp, trangthaihd, trangthaisp;

                Date ngaytaohd;

                maHd = rs.getInt(1);
                makh = rs.getInt(2);
                manv = rs.getInt(3);
                mahttt = rs.getInt(4);
                boolean trangthaihdvalue = rs.getBoolean(5);
                if (trangthaihdvalue) {
                    trangthaihd = "đã thanh toán";

                } else {
                    trangthaihd = "đã hủy";
                }
                ngaytaohd = rs.getDate(6);
                ngaysuahd = rs.getString(7);

                HoaDonCuaBang chiTiet = new HoaDonCuaBang(maHd, makh, manv, mahttt, ngaytaohd, ngaysuahd, trangthaihd);

                getList.add(chiTiet);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<HoaDonCuaBang> GetHoaDonCTByMaHD(int maHD) {
        ArrayList<HoaDonCuaBang> danhSachChiTiet = new ArrayList<>();
        try {
            sql = " SELECT \n"
                    + "    HD.MAHD,\n"
                    + "    HD.MAKH,\n"
                    + "    HD.MANV,\n"
                    + "    HD.MAHTTT,\n"
                    + "    HD.TRANGTHAI,\n"
                    + "    HD.NGAYTAO,\n"
                    + "    HD.NGAYSUA,\n"
                    + "    SP.MASP,\n"
                    + "    SP.TENSP,\n"
                    + "    SP.TRANGTHAI,\n"
                    + "    SP.NGAYTAO,\n"
                    + "    SP.NGAYSUA,\n"
                    + "    HDCT.MAHDCT,\n"
                    + "    HDCT.SOLUONG,\n"
                    + "    HDCT.DONGIA,\n"
                    + "    HDCT.TONGTIEN\n"
                    + "FROM \n"
                    + "    HOADON HD\n"
                    + "JOIN \n"
                    + "    HOADONCT HDCT ON HD.MAHD = HDCT.MAHD\n"
                    + "JOIN \n"
                    + "    SANPHAMCT SPCT ON HDCT.MASPCT = SPCT.MASPCT\n"
                    + "JOIN\n"
                    + "    SANPHAM SP ON SPCT.MASP = SP.MASP where HD.MAHD = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, maHD); // Thiết lập giá trị cho tham số trong câu lệnh SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet và tạo đối tượng HoaDonCuaBang

                int masp = rs.getInt("MASP");
                String tensp = rs.getString("TENSP");
                String trangthaiSP = rs.getBoolean("TRANGTHAI") ? "còn hàng" : "hết hàng";
                Date ngaytaoSP = rs.getDate("NGAYTAO");
                String ngaysuaSP = rs.getString("NGAYSUA");
                int mahdct = rs.getInt("MAHDCT");
                int soluong = rs.getInt("SOLUONG");
                int dongia = rs.getInt("DONGIA");
                int tongtien = rs.getInt("TONGTIEN");

                HoaDonCuaBang chiTietHD = new HoaDonCuaBang(
                        masp, tensp, trangthaiSP, ngaytaoSP, ngaysuaSP, mahdct, soluong, dongia, tongtien
                );

                danhSachChiTiet.add(chiTietHD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi ở đây
        } finally {
            // Đóng ResultSet và PreparedStatement nếu không còn sử dụng
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                // Không đóng Connection ở đây nếu nó được quản lý ở nơi khác
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý lỗi đóng tài nguyên ở đây
            }
        }

        return danhSachChiTiet;
    }

}
