/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Connect.DBConnect;
import Model.HoaDonCT;
import Model.HoaDonCTfull;
import Model.HoaDonCuaBang;
import Model.HoaDonTest;
import Model.SanPham;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import service.SanPhamService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;
import service.HoaDonService;
import service.UserDao;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class formbanhang extends javax.swing.JPanel {

    private HoaDonService hds = new HoaDonService();
    private List<HoaDonTest> HDT = new ArrayList<>();
    private List<HoaDonCT> HDCT = new ArrayList<>();
    private SanPhamService sps = new SanPhamService();
    private DefaultTableModel model = new DefaultTableModel();
    private List<SanPham> SP_REPO = sps.getAllSanPham();
    private List<HoaDonCuaBang> HD_REPO = hds.GetAll();
    private Map<String, List<SanPham>> gioHangMap = new HashMap<>();
    private UserDao Dao = new UserDao();
    
    /**
     * Creates new form fromHoadon
     */
    public formbanhang() {
        initComponents();
        loadHoadon();
        loadSanPham();
        LoadGioHang(selectedMaHoaDon);
    }
public void setNhanVienFullName(String fullName) {
        txtnhanvien.setText(fullName);
    }

    

    public static int themHoaDonVaoDatabase(boolean trangThai) {
        String sql = "INSERT INTO HOADON (TrangThai) VALUES (?)";
        int generatedId = -1;

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setBoolean(1, trangThai);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Lấy mã hóa đơn vừa tạo
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return generatedId;

    }

    public void loadHoadon() {

        DefaultTableModel dtm = (DefaultTableModel) tblhoadon.getModel();
        dtm.setRowCount(0);

        HDT.clear();

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement("SELECT MAHD, TrangThai, FORMAT(NGAYTAO, 'dd/MM/yyyy') AS NgayTao FROM HOADON"); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int maHD = resultSet.getInt("MAHD");
                String ngaytao = resultSet.getString("NGAYTAO");
                String trangThai = "Chờ thanh toán";

                Object[] row = new Object[]{maHD, trangThai, ngaytao};
                dtm.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadSanPham() {
        model = (DefaultTableModel) tblSanPham.getModel();
        ArrayList<SanPham> lstSanPham = sps.getAllSanPham();
        model.setRowCount(0);
        for (SanPham sp : lstSanPham) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSp(), sp.getDonGia(), sp.getSoLuong(), sp.getDungTich(), sp.getMuiHuong(), sp.getThuongHieu(), sp.isTrangThai() ? "Còn hàng" : "Hết hàng", sp.getHinhAnh(), sp.getNgayTao(), sp.getNgaySua()});
        }
    }
    private List<Object[]> gioHangItems = new ArrayList<>();

    private void capNhatTongTien() {
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        double tongTien = 0;
        for (int i = 0; i < dtm.getRowCount(); i++) {
            double giaBan = Double.parseDouble(dtm.getValueAt(i, 3).toString());
            int soLuong = Integer.parseInt(dtm.getValueAt(i, 4).toString());
            tongTien += giaBan * soLuong;
        }
        lbTongTien.setText(String.format("%.2f", tongTien));
    }

    public void LoadGioHang(String maHoaDon) {
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        // Load giỏ hàng của mã hóa đơn từ danh sách HDCT
        for (HoaDonCT hoaDonCT : HDCT) {
            if (hoaDonCT.getMahd().equals(maHoaDon)) {
                String maHoadon = hoaDonCT.getMahd();
                String maSanPham = hoaDonCT.getMaSanPhamct();
                String tenSanPham = getTenSanPham(maSanPham);
                Double giaBan = hoaDonCT.getGiaban();
                double soLuong = hoaDonCT.getSoluong();

                // Tính tổng tiền từ số lượng và giá bán
                double tongTien = soLuong * giaBan;

                Object[] row = new Object[]{maHoadon, maSanPham, tenSanPham, giaBan, soLuong, tongTien};
                dtm.addRow(row); // Thêm mục vào bảng tblgiohang
            }
        }

        double tongTienGioHang = tinhTongTienGioHang();
        lbTongTien.setText(String.valueOf(tongTienGioHang));
        lbTienThua.setText(String.valueOf(0));
    }

    private void themChiTietHoaDon(int maHoaDon, SanPham sp) {
        String sql = "INSERT INTO HOADONCT (MAHD, MASPCT, SOLUONG, DONGIA, TONGTIEN, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, maHoaDon);
            statement.setString(2, sp.getMaSP()); // Giả định rằng bạn có phương thức getMaSPCT() trong SanPham
            statement.setInt(3, sp.getSoLuong());
            statement.setDouble(4, sp.getDonGia());
            statement.setDouble(5, sp.getSoLuong() * sp.getDonGia());
            statement.setBoolean(6, true); // Giả định rằng trạng thái là true khi thanh toán
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void upgiohang(String maHoaDon) {
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);

        List<SanPham> gioHang = gioHangMap.get(maHoaDon);
        if (gioHang != null) {
            for (SanPham sp : gioHang) {
                int soLuong = sp.getSoLuong();
                double donGia = sp.getDonGia();
                double tongTien = soLuong * donGia; // Tính tổng tiền
                Object[] row = new Object[]{maHoaDon, sp.getMaSP(), sp.getTenSp(), donGia, soLuong, tongTien};
                dtm.addRow(row);
            }
        }
        capNhatTongTien(); // Cập nhật tổng tiền sau khi cập nhật giỏ hàng
    }

    private void xuLyChonHoaDon(String maHoaDon) {
        if (!gioHangMap.containsKey(maHoaDon)) {
            gioHangMap.put(maHoaDon, new ArrayList<>());
        }
        // Hiển thị giỏ hàng tương ứng
        upgiohang(maHoaDon);
    }

    public void thanhToanHoaDon() {
        int selectedRow = tblhoadon.getSelectedRow();
        if (selectedRow >= 0) {
            int maHoaDon = (int) tblhoadon.getValueAt(selectedRow, 0);

            // Cập nhật trạng thái hóa đơn
            updateTrangThaiHoaDon(maHoaDon, true);

            // Thêm chi tiết hóa đơn cho mỗi sản phẩm trong giỏ hàng
            List<SanPham> gioHang = gioHangMap.get(String.valueOf(maHoaDon));
            if (gioHang != null) {
                for (SanPham sp : gioHang) {
                    themChiTietHoaDon(maHoaDon, sp);
                }
            }

            // Xóa dòng trong bảng tblhoadon
            DefaultTableModel dtmHoaDon = (DefaultTableModel) tblhoadon.getModel();
            dtmHoaDon.removeRow(selectedRow);
        }
    }

    public int updateTrangThaiHoaDon(int maHoaDon, boolean trangThai) {
        String sql = "UPDATE HOADON SET TrangThai = ? WHERE MAHD = ?";
        int rowsAffected = 0;

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setBoolean(1, trangThai);
            statement.setInt(2, maHoaDon);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }

    public void huyHoaDon(int maHoaDon) {
        // Xóa dòng dữ liệu đã chọn trong bảng tblhoadon
        DefaultTableModel dtmHoaDon = (DefaultTableModel) tblhoadon.getModel();
        int selectedRow = tblhoadon.getSelectedRow();
        dtmHoaDon.removeRow(selectedRow);

        // Cập nhật trạng thái hóa đơn thành "Đã hủy"
        huyTrangThaiHoaDon(maHoaDon, true);
    }

    public void traHang() {
        int selectedRow = tblgiohang.getSelectedRow();
        if (selectedRow >= 0) {
            String maSanPham = tblgiohang.getValueAt(selectedRow, 1).toString();
            SanPham sp = SP_REPO.stream()
                    .filter(s -> s.getMaSP().equals(maSanPham))
                    .findFirst()
                    .orElse(null);

            if (sp == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selectedHoaDon = tblhoadon.getSelectedRow();
            if (selectedHoaDon == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn trước khi chọn sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String maHoaDon = tblhoadon.getValueAt(selectedHoaDon, 0).toString();

            List<SanPham> gioHang = gioHangMap.get(maHoaDon);

            SanPham gioHangSpToDecrement = gioHang.get(selectedRow);
            int soLuongHienCo = gioHangSpToDecrement.getSoLuong();

            if (soLuongHienCo > 1) {
                soLuongHienCo--;
                gioHangSpToDecrement.setSoLuong(soLuongHienCo);

                // Tăng số lượng sản phẩm trong bảng sản phẩm
                int rowCount = tblSanPham.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String maSP = tblSanPham.getValueAt(i, 0).toString();
                    if (maSP.equals(maSanPham)) {
                        int soLuongHienCoSP = Integer.parseInt(tblSanPham.getValueAt(i, 3).toString());
                        soLuongHienCoSP++; // Tăng số lượng hiện có lên 1
                        tblSanPham.setValueAt(soLuongHienCoSP, i, 3); // Cập nhật đúng cột số lượng
                        System.out.println("Updated quantity for product " + maSanPham + ": " + soLuongHienCoSP);
                        // Cập nhật số lượng trong cơ sở dữ liệu SQL
                        updateSanPhamSoLuong(maSanPham, soLuongHienCoSP);
                        break;
                    }
                }

                // Cập nhật lại bảng sản phẩm
                ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();

                // Cập nhật lại tổng tiền dans le giỏ hàng
                double donGia = gioHangSpToDecrement.getDonGia(); // Giả sử bạn có phương thức này
                double tongTienMoi = soLuongHienCo * donGia;
                tblgiohang.setValueAt(tongTienMoi, selectedRow, 5); // Cập nhật cột tổng tiền (giả sử cột tổng tiền là cột 5)
                // Cập nhật số lượng trong tblgiohang
                tblgiohang.setValueAt(soLuongHienCo, selectedRow, 4);

                // Cập nhật lại bảng giỏ hàng
                ((DefaultTableModel) tblgiohang.getModel()).fireTableDataChanged();
            } else {
// Trả lại toàn bộ sản phẩm về kho khi số lượng bằng 1 và xóa sản phẩm khỏi giỏ hàng
                int rowCount = tblSanPham.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String maSP = tblSanPham.getValueAt(i, 0).toString();
                    if (maSP.equals(maSanPham)) {
                        int soLuongHienCoSP = Integer.parseInt(tblSanPham.getValueAt(i, 3).toString());
                        soLuongHienCoSP += soLuongHienCo; // Tăng số lượng hiện có lên bằng số lượng trả lại
                        tblSanPham.setValueAt(soLuongHienCoSP, i, 3); // Cập nhật đúng cột số lượng
                        System.out.println("Updated quantity for product " + maSanPham + ": " + soLuongHienCoSP);
                        // Cập nhật số lượng trong cơ sở dữ liệu SQL
                        updateSanPhamSoLuong(maSanPham, soLuongHienCoSP);
                        break;
                    }
                }

                // Cập nhật lại bảng sản phẩm
                ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();

                // Xóa sản phẩm khỏi giỏ hàng
                gioHang.remove(selectedRow);

                // Cập nhật lại bảng giỏ hàng
                ((DefaultTableModel) tblgiohang.getModel()).removeRow(selectedRow);
                ((DefaultTableModel) tblgiohang.getModel()).fireTableDataChanged();

                JOptionPane.showMessageDialog(this, "Đã trả lại hết sản phẩm này về giỏ giỏ.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        capNhatTongTien();
    }

    public void huyTrangThaiHoaDon(int maHoaDon, boolean trangThai) {
        String sql = "UPDATE HOADON SET TRANGTHAI = ? WHERE MAHD = ?";
        int rowsAffected = 0;

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setBoolean(1, trangThai); // Sửa lại index parameter thành 1
            statement.setInt(2, maHoaDon); // Sửa lại index parameter thành 2
            rowsAffected = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (rowsAffected > 0) {
            // Cập nhật thành công
        } else {
            // Cập nhật thất bại
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        txtkhachhang = new javax.swing.JTextField();
        txtnhanvien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTienKhach = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbTienThua = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txttimkiemsanpham = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhoadon = new javax.swing.JTable();
        btntaohd = new javax.swing.JButton();
        btnthanhtoanfull = new javax.swing.JButton();
        btnhuydon = new javax.swing.JButton();

        jPanel1.setPreferredSize(new java.awt.Dimension(807, 630));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel14.setText("Số ĐT");

        jLabel15.setText("Tên Khách Hàng");

        jLabel16.setText("Nhân Viên Bán");

        txtnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnhanvienActionPerformed(evt);
            }
        });

        jLabel8.setText("Tổng Tiền");

        jLabel11.setText("Tiền Khách Đưa");

        jLabel12.setText("Tiền Thừa");

        lbTienThua.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(txtnhanvien)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel8))
                            .addGap(53, 53, 53)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(lbTongTien)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12))
                            .addGap(23, 23, 23)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTienKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addComponent(lbTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(99, 99, 99))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel15)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbTienThua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(56, 56, 56))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel4.setText("Tìm Kiếm");

        txttimkiemsanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemsanphamActionPerformed(evt);
            }
        });
        txttimkiemsanpham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemsanphamKeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Dung tích", "Mùi hương", "Thương hiệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPham);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã SP", "Tên SP", "Đơn Giá ", "Số Lượng", "Tổng Tiền"
            }
        ));
        tblgiohang.setShowGrid(true);
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblgiohang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addComponent(txttimkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttimkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn Chờ"));

        tblhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Trạng thái", "Ngày tạo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblhoadon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btntaohd.setBackground(new java.awt.Color(0, 255, 204));
        btntaohd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntaohd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btntaohd.setText("Tạo Hóa Đơn Chờ");
        btntaohd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btntaohd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohdActionPerformed(evt);
            }
        });

        btnthanhtoanfull.setBackground(new java.awt.Color(0, 204, 255));
        btnthanhtoanfull.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthanhtoanfull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon-cart.png"))); // NOI18N
        btnthanhtoanfull.setText("Thanh Toán");
        btnthanhtoanfull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoanfullActionPerformed(evt);
            }
        });

        btnhuydon.setBackground(new java.awt.Color(255, 51, 51));
        btnhuydon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnhuydon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-delete-24.png"))); // NOI18N
        btnhuydon.setText("Hủy đơn chờ");
        btnhuydon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuydonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btntaohd, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnthanhtoanfull, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnhuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btntaohd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnhuydon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnthanhtoanfull, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnhanvienActionPerformed

    }//GEN-LAST:event_txtnhanvienActionPerformed

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed

        themHoaDonVaoDatabase(true);
        loadHoadon();


    }//GEN-LAST:event_btntaohdActionPerformed
    private void updateSanPhamSoLuong(String maSanPham, int soLuongMoi) {
        String sql = "UPDATE SANPHAMCT SET SOLUONG = ? WHERE MASPCT = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, soLuongMoi);
            statement.setString(2, maSanPham);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật số lượng sản phẩm trong cơ sở dữ liệu.", "Lỗi cập nhật", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            String maSanPham = tblSanPham.getValueAt(selectedRow, 0).toString();
            SanPham sp = SP_REPO.stream()
                    .filter(s -> s.getMaSP().equals(maSanPham))
                    .findFirst()
                    .orElse(null);

            if (sp == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selectedHoaDon = tblhoadon.getSelectedRow();
            if (selectedHoaDon == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn trước khi chọn sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String maHoaDon = tblhoadon.getValueAt(selectedHoaDon, 0).toString();

            // Kiểm tra xem hóa đơn đã tồn tại trong giỏ hàng chưa
            if (!gioHangMap.containsKey(maHoaDon)) {
                gioHangMap.put(maHoaDon, new ArrayList<>());
            }

            List<SanPham> gioHang = gioHangMap.get(maHoaDon);

            // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
            boolean isExisted = false;
            for (SanPham gioHangSp : gioHang) {
                if (gioHangSp.getMaSP().equals(maSanPham)) {
                    gioHangSp.setSoLuong(gioHangSp.getSoLuong() + 1);
                    isExisted = true;
                    break;
                }
            }

            if (!isExisted) {
                sp.setSoLuong(1);
                gioHang.add(sp);
            }

// Trừ đi số lượng sản phẩm sau khi thêm vào giỏ hàng
            int soLuongHienCo = 0;
            try {
                soLuongHienCo = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 3).toString());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không hợp lệ.", "Lỗi định dạng số", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu xảy ra lỗi định dạng số
            }
            if (soLuongHienCo > 0) {
                soLuongHienCo--;
                tblSanPham.setValueAt(soLuongHienCo, selectedRow, 3);
                // Cập nhật số lượng trong cơ sở dữ liệu SQL
                updateSanPhamSoLuong(maSanPham, soLuongHienCo);
            } else {
                JOptionPane.showMessageDialog(this, "Sản phẩm này đã hết hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

            // Cập nhật lại bảng sản phẩm
            ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();

            // Hiển thị giỏ hàng trong bảng tblgiohang
            DefaultTableModel dtmGioHang = (DefaultTableModel) tblgiohang.getModel();
            dtmGioHang.setRowCount(0);

            for (SanPham gioHangSp : gioHang) {
                Object[] row = new Object[]{maHoaDon, gioHangSp.getMaSP(), gioHangSp.getTenSp(), gioHangSp.getDonGia(), gioHangSp.getSoLuong(), gioHangSp.getDonGia() * gioHangSp.getSoLuong()};
                dtmGioHang.addRow(row);
            }
            capNhatTongTien();
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private String selectedMaHoaDon = null;
    private void tblhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonMouseClicked

        int selectedRow = tblhoadon.getSelectedRow();
        if (selectedRow >= 0) {
            String maHoaDonStr = tblhoadon.getValueAt(selectedRow, 0).toString();
            xuLyChonHoaDon(maHoaDonStr);
        }
    }//GEN-LAST:event_tblhoadonMouseClicked

    private void btnhuydonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuydonActionPerformed
        int selectedRow = tblhoadon.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy đơn hàng?", "Xác nhận hủy đơn hàng", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int maHoaDon = (int) tblhoadon.getValueAt(selectedRow, 0);
                huyHoaDon(maHoaDon); // Xóa dòng dữ liệu của hóa đơn và cập nhật trạng thái hóa đơn trong phương thức này
                huyTrangThaiHoaDon(maHoaDon, false); // Đổi trạng thái thành 0 (false)
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được hủy.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để hủy.");
        }
    }//GEN-LAST:event_btnhuydonActionPerformed

    private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhtoanActionPerformed
        // TODO add your handling code here: lỗi

    }//GEN-LAST:event_btnThanhtoanActionPerformed
    private double tinhTongTienGioHang() {
//    DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
//    double tongTien = 0;
//    for (int i = 0; i < dtm.getRowCount(); i++) {
//        tongTien += (Double) dtm.getValueAt(i, 5); // Giả sử cột thứ 6 chứa tổng tiền của từng mặt hàng
//    }
//    return tongTien;
        double tongTien = 0;
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            Object value = tblgiohang.getValueAt(i, 5);
            if (value != null) {
                tongTien += Double.parseDouble(value.toString());
            }
        }
        return tongTien;
    }
    private void btnthanhtoanfullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoanfullActionPerformed
//         TODO add your handling code here:
        double tongTien = tinhTongTienGioHang();

        // Hiển thị tổng tiền lên label
        lbTongTien.setText(String.format("%.2f", tongTien));

        // Parse tiền khách đưa từ textField txtTienKhach
        double tienKhachDua = 0;
        try {
            tienKhachDua = Double.parseDouble(txtTienKhach.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tính tiền thừa và hiển thị lên label
        double tienThua = tienKhachDua - tongTien;
        if (tienThua < 0) {
            JOptionPane.showMessageDialog(this, "Khách chưa đưa đủ tiền!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            lbTienThua.setText(String.format("%.2f", tienThua));
            // Xử lý thanh toán và cập nhật dữ liệu vào cơ sở dữ liệu ở đây
            // ...
            JOptionPane.showMessageDialog(this, "Thanh toán thành công, tiền thừa là: " + String.format("%.2f", tienThua), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        int selectedRow = tblhoadon.getSelectedRow();
        if (selectedRow >= 0) {
            SwingUtilities.invokeLater(() -> {
                DefaultTableModel model = (DefaultTableModel) tblhoadon.getModel();
                if (selectedRow < model.getRowCount()) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thanh toán đơn hàng?", "Xác nhận hủy đơn hàng", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            int maHoaDon = (int) tblhoadon.getValueAt(selectedRow, 0);
                            thanhToanHoaDon();

                            if (selectedRow < model.getRowCount()) {
                                model.removeRow(selectedRow);
                                JOptionPane.showMessageDialog(null, "Hóa đơn đã được thanh toán.");
                            } else {
                                JOptionPane.showMessageDialog(null, "thanh toán thành công.");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý hóa đơn. Vui lòng thử lại.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Dữ liệu hóa đơn đã thay đổi, vui lòng thử lại.");
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để thanh toán.");
        }


    }//GEN-LAST:event_btnthanhtoanfullActionPerformed

    private void txttimkiemsanphamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemsanphamKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(dtm);
        tblSanPham.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txttimkiemsanpham.getText()));
    }//GEN-LAST:event_txttimkiemsanphamKeyReleased

    private void txttimkiemsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemsanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemsanphamActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked
        traHang();
    }//GEN-LAST:event_tblgiohangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhuydon;
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btnthanhtoanfull;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JTextField lbTongTien;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tblhoadon;
    private javax.swing.JTextField txtTienKhach;
    private javax.swing.JTextField txtkhachhang;
    private javax.swing.JTextField txtnhanvien;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttimkiemsanpham;
    // End of variables declaration//GEN-END:variables

    private String getTenSanPham(String maSanPham) {
        String tenSanPham = null;
        String sql = "SELECT TENSP FROM SANPHAM WHERE MASP = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, maSanPham);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tenSanPham = rs.getString("TENSP");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tenSanPham;
    }

}
