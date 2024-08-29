/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Connect.DBConnect;
import Model.SanPhamBang;
import Model.ThuocTinh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.SanPhamServiceBang;
import service.ThuocTinhService;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class formsanpham extends javax.swing.JPanel {

    private ThuocTinhService tts = new ThuocTinhService();
     private final SanPhamServiceBang sps = new SanPhamServiceBang();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model1 = new DefaultTableModel();
    private int index;
    String strHinh = null;
    
    public formsanpham() {
         initComponents();
        addRadioButtonsListener();
        model1 = (DefaultTableModel) tblThuocTinh.getModel();
         
        fillSanPhamComboBox();
        fillMuiHuongComboBox();
        fillThuongHieuComboBox();
        fillHinhAnhComboBox();
        model = (DefaultTableModel) tblSanPham.getModel();
        loadDataToTable();
        if(tblSanPham.getRowCount()>0){
            index = 0;
            showsanpham();
        }

    }
  
public void loadDataToTable() {
        ArrayList<SanPhamBang> lstSanPham = sps.getAllSanPham();
        model.setRowCount(0);
        for (SanPhamBang sp : lstSanPham) {
            model.addRow(new Object[]{sp.getMaSP(),sp.getTenSp(),sp.getDonGia(),sp.getSoLuong(),sp.getDungTich(),sp.getMuiHuong(),sp.getThuongHieu(),sp.isTrangThai()?"Còn hàng":"Hết hàng",sp.getHinhAnh(),sp.getNgayTao(),sp.getNgaySua()});
        }
    }
    
    private void fillSanPhamComboBox(){
        DefaultComboBoxModel<String> thModel = sps.getAllTenSp();
        cboTenSp.setModel(thModel);
    }
    
    private void fillThuongHieuComboBox(){
        DefaultComboBoxModel<String> thModel = sps.getAllThuongHieu();
        cboThuongHieu.setModel(thModel);
    }
    
    
    private void fillMuiHuongComboBox(){
        DefaultComboBoxModel<String> mhModel = sps.getAllMuiHuong();
        cboMuiHuong.setModel(mhModel);
    }
    
    private void fillHinhAnhComboBox(){
        DefaultComboBoxModel<String> mhModel = sps.getAllHinhAnh();
        cboHinhAnh.setModel(mhModel);
    }
    
//    thuộc tính
    public void loadTenSpToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllTenSP();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    public void loadThuongHieuToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllThuongHieu();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    public void loadMuiHuongToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllMuiHuong();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    public void loadHinhAnhToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllHinhAnh();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    private void addRadioButtonsListener() {
        rdoTenSp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTenSpToTable();
            }
        });
        
        rdoThuongHieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadThuongHieuToTable();
            }
        });

        rdoMuiHuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMuiHuongToTable();
            }
        });
        
        rdoHinhAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHinhAnhToTable();
            }
        });
    }
    
    
        
    
    public void showsanpham(){
     txtMaSp.setText(tblSanPham.getValueAt(index, 0).toString());
        String TenSp = tblSanPham.getValueAt(index, 1).toString();
       cboTenSp.setSelectedItem(TenSp);
        txtDonGia.setText(tblSanPham.getValueAt(index, 2).toString());
        txtSoLuong.setText(tblSanPham.getValueAt(index, 3).toString());
        txtDungTich.setText(tblSanPham.getValueAt(index, 4).toString());
        String muiHuong = tblSanPham.getValueAt(index, 5).toString();
       cboMuiHuong.setSelectedItem(muiHuong);
       String thuongHieu = tblSanPham.getValueAt(index, 5).toString();
       cboThuongHieu.setSelectedItem(thuongHieu);
       String trangThai = tblSanPham.getValueAt(index, 7).toString();
       cboTrangThai.setSelectedItem(trangThai);
       String HinhAnh = tblSanPham.getValueAt(index, 8).toString();
       cboHinhAnh.setSelectedItem(trangThai);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtDungTich = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboMuiHuong = new javax.swing.JComboBox<>();
        cboTenSp = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboHinhAnh = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtThuocTinh = new javax.swing.JTextField();
        rdoThuongHieu = new javax.swing.JRadioButton();
        rdoMuiHuong = new javax.swing.JRadioButton();
        btnThem1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        rdoTenSp = new javax.swing.JRadioButton();
        rdoHinhAnh = new javax.swing.JRadioButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 12))); // NOI18N

        jLabel1.setText("Mã sản phẩm");

        jLabel2.setText("Tên sản phẩm");

        jLabel3.setText("Đơn giá");

        jLabel4.setText("Số lượng");

        jLabel5.setText("Dung Tích");

        jLabel6.setText("Mùi hương");

        jLabel7.setText("Thương hiệu");

        jLabel8.setText("Trạng thái");

        txtMaSp.setText("Mã sản phẩm tự sinh");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hàng", "Hết hàng" }));

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gucci", "Dior", " " }));

        cboMuiHuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hương gỗ", "Hương vani" }));

        cboTenSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Hình Ảnh");

        cboHinhAnh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(12, 12, 12)
                            .addComponent(cboTenSp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(44, 44, 44)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(txtDungTich, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cboHinhAnh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(cboThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(29, 29, 29)
                        .addComponent(cboMuiHuong, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDungTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboMuiHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cboHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 12))); // NOI18N

        jLabel10.setText("Tìm kiếm");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Dung tích", "Mùi hương", "Thương hiệu", "Trạng thái", "Hình ảnh", "Ngày tạo", "Ngày sửa"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(27, 27, 27)
                                .addComponent(btnSua)
                                .addGap(29, 29, 29)
                                .addComponent(btnMoi)))
                        .addGap(0, 558, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnMoi)
                    .addComponent(btnSua))
                .addGap(108, 108, 108))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Sản Phẩm", jPanel9);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 12))); // NOI18N

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Số thứ tự", "Loại thuộc tính", "Tên thuộc tính"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc tính sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 12))); // NOI18N

        jLabel12.setText("Tên thuộc tính");

        rdoThuongHieu.setText("Thương hiệu");

        rdoMuiHuong.setText("Mùi hương");

        btnThem1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnSua1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        rdoTenSp.setText("Tên sản phẩm");

        rdoHinhAnh.setText("Hình ảnh");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnThem1)
                        .addGap(46, 46, 46)
                        .addComponent(btnSua1)
                        .addContainerGap(745, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoMuiHuong, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoThuongHieu)
                    .addComponent(rdoMuiHuong)
                    .addComponent(rdoTenSp)
                    .addComponent(rdoHinhAnh))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1)
                    .addComponent(btnSua1))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thuộc Tính", jPanel10);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 505, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // Lấy thông tin từ các trường nhập liệu
        int maSP = Integer.parseInt(txtMaSp.getText());
        String tenSP = cboTenSp.getSelectedItem().toString();
        double donGia = Double.parseDouble(txtDonGia.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        int dungTich = Integer.parseInt(txtDungTich.getText());
        String muiHuong = cboMuiHuong.getSelectedItem().toString();
        String thuongHieu = cboThuongHieu.getSelectedItem().toString();
        boolean trangThai = cboTrangThai.getSelectedItem().toString().equals("Còn hàng"); // Chuyển đổi kiểu int sang boolean
        String hinhAnh = cboHinhAnh.getSelectedItem().toString(); // Đảm bảo hình ảnh đã được chọn trước khi thêm
        Date ngayTao = new Date(); // Sử dụng ngày hiện tại cho ngày tạo
        Date ngaySua = new Date(); // Sử dụng ngày hiện tại cho ngày sửa

        // Tạo một đối tượng SanPham mới với thông tin vừa nhập
        SanPhamBang sanPham = new SanPhamBang(maSP, tenSP, donGia, soLuong, dungTich, muiHuong, thuongHieu, trangThai, hinhAnh, null, null);

        // Gọi phương thức update để cập nhật thông tin sản phẩm
        boolean result = sps.update(sanPham);

        // Kiểm tra kết quả cập nhật và hiển thị thông báo tương ứng
        if (result) {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadDataToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            String tenSP = cboTenSp.getSelectedItem().toString();
            double donGia = Double.parseDouble(txtDonGia.getText());
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            int dungTich = Integer.parseInt(txtDungTich.getText());
            String muiHuong = cboMuiHuong.getSelectedItem().toString();
            String thuongHieu = cboThuongHieu.getSelectedItem().toString();
            boolean trangThai = cboTrangThai.getSelectedItem().toString().equals("Còn hàng"); // Chuyển đổi kiểu int sang boolean
            String hinhAnh = cboHinhAnh.getSelectedItem().toString(); // Đảm bảo hình ảnh đã được chọn trước khi thêm
            Date ngayTao = new Date(); // Sử dụng ngày hiện tại cho ngày tạo
            Date ngaySua = new Date(); // Sử dụng ngày hiện tại cho ngày sửa

            SanPhamBang sanPham = new SanPhamBang(0, tenSP, donGia, soLuong, dungTich, muiHuong, thuongHieu, trangThai, hinhAnh, ngayTao, ngaySua);

            // Thực hiện thêm sản phẩm và kiểm tra kết quả
            boolean result = sps.add(sanPham);
            if (result) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                loadDataToTable(); // Cập nhật lại dữ liệu trên bảng sau khi thêm
                btnMoiActionPerformed(evt); // Reset các trường nhập liệu sau khi thêm thành công
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho dữ liệu giá, số lượng và dung tích");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:DefaultTableModel dtm  = (DefaultTableModel) tblNhanVien.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(model);
        tblSanPham.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        index = tblSanPham.getSelectedRow();
        showsanpham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        txtMaSp.setText("");
        cboTenSp.setSelectedIndex(0);
        txtDonGia.setText("");
        txtSoLuong.setText("");
        txtDungTich.setText("");
        cboMuiHuong.setSelectedIndex(0);
        cboThuongHieu.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);
        cboHinhAnh.setSelectedIndex(0);
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
       index = tblThuocTinh.getSelectedRow();
    
    if (index != -1) { // Check if a row is indeed selected
        // Retrieve the value from the 'Tên thuộc tính' column of the selected row
        String tenThuocTinh = tblThuocTinh.getValueAt(index, 2).toString(); // Assuming column index for 'Tên thuộc tính' is 2
        
        // Set the retrieved value to the text field
        txtThuocTinh.setText(tenThuocTinh);
        
        // Optionally, if you want to check which radio button should be selected based on 'Loại thuộc tính'
        String loaiThuocTinh = tblThuocTinh.getValueAt(index, 1).toString(); // Assuming column index for 'Loại thuộc tính' is 1
        switch (loaiThuocTinh) {
            case "Tên sản phẩm":
                rdoTenSp.setSelected(true);
                break;
            case "Thương hiệu":
                rdoThuongHieu.setSelected(true);
                break;
            case "Mùi hương":
                rdoMuiHuong.setSelected(true);
                break;
            case "Hình ảnh":
                rdoHinhAnh.setSelected(true);
                break;
            default:
                
        }
    }
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed

        String tenThuocTinh = txtThuocTinh.getText().trim();
if (tenThuocTinh.isEmpty()) {
    JOptionPane.showMessageDialog(formsanpham.this, "Vui lòng nhập giá trị thuộc tính!");
    return; // Exit the method if validation fails
}
        // Kiểm tra xem radio button nào đang được chọn
        if (rdoTenSp.isSelected()) {
            // Thêm thuộc tính vào bảng SANPHAM
            boolean result = tts.themTenSP(tenThuocTinh);
            if (result) {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thành công!");
                // Reload bảng sau khi thêm thành công
                loadTenSpToTable();
            } else {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thất bại!");
            }

        } else if (rdoThuongHieu.isSelected()) {
            // Thêm thuộc tính vào bảng THUONGHIEU
            boolean result = tts.themThuongHieu(tenThuocTinh);
            if (result) {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thành công!");
                // Reload bảng sau khi thêm thành công
                loadThuongHieuToTable();
            } else {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thất bại!");
            }

        } else if (rdoMuiHuong.isSelected()) {
            // Thêm thuộc tính vào bảng MUIHUONG
            boolean result = tts.themMuiHuong(tenThuocTinh);
            if (result) {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thành công!");
                // Reload bảng sau khi thêm thành công
                loadMuiHuongToTable();
            } else {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thất bại!");
            }

        } else if (rdoHinhAnh.isSelected()) {
            // Thêm thuộc tính vào bảng HINHANH
            boolean result = tts.themHinhAnh(tenThuocTinh);
            if (result) {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thành công!");
                // Reload bảng sau khi thêm thành công
                loadHinhAnhToTable();
            } else {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thất bại!");
            }
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
      String tenThuocTinh = txtThuocTinh.getText().trim();

// Kiểm tra nếu chuỗi là trống, hiển thị thông báo và không thực hiện sửa
if (tenThuocTinh.isEmpty()) {
    JOptionPane.showMessageDialog(formsanpham.this, "Bạn phải nhập dữ liệu cho 'Tên thuộc tính'. Việc sửa không thể để trống.");
    return; // Thoát phương thức
}

// Lấy giá trị từ bảng và kiểm tra kiểu dữ liệu trước khi ép kiểu
Object cellValue = tblThuocTinh.getValueAt(index, 0); // Giả sử đây là cột 'Số thứ tự'
int maThuocTinh;

// Đoạn code sau giả định cellValue luôn là một số, nếu không, bạn cần kiểm tra và xử lý nó tương tự như đã làm ở trên
maThuocTinh = Integer.parseInt(cellValue.toString());

// Các đoạn kiểm tra radio button và sửa dữ liệu như trên
if (rdoTenSp.isSelected()) {
    // Sửa thuộc tính trong bảng SANPHAM...
    // Các đoạn code còn lại giống như đã cho ở trên
}

// Kiểm tra xem radio button nào đang được chọn
if (rdoTenSp.isSelected()) {
    // Sửa thuộc tính trong bảng SANPHAM
    boolean result = tts.suaTenSP(maThuocTinh, tenThuocTinh);
    if (result) {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thành công!");
        // Reload bảng sau khi sửa thành công
        loadTenSpToTable();
    } else {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thất bại!");
    }
} else if (rdoThuongHieu.isSelected()) {
    // Sửa thuộc tính trong bảng THUONGHIEU
    boolean result = tts.suaThuongHieu(maThuocTinh, tenThuocTinh);
    if (result) {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thành công!");
        // Reload bảng sau khi sửa thành công
        loadThuongHieuToTable();
    } else {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thất bại!");
    }
} else if (rdoMuiHuong.isSelected()) {
    // Sửa thuộc tính trong bảng MUIHUONG
    boolean result = tts.suaMuiHuong(maThuocTinh, tenThuocTinh);
    if (result) {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thành công!");
        // Reload bảng sau khi sửa thành công
        loadMuiHuongToTable();
    } else {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thất bại!");
    }
} else if (rdoHinhAnh.isSelected()) {
    // Sửa thuộc tính trong bảng HINHANH
    boolean result = tts.suaHinhAnh(maThuocTinh, tenThuocTinh);
    if (result) {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thành công!");
        // Reload bảng sau khi sửa thành công
        loadHinhAnhToTable();
    } else {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thất bại!");
    }
}
    }//GEN-LAST:event_btnSua1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboHinhAnh;
    private javax.swing.JComboBox<String> cboMuiHuong;
    private javax.swing.JComboBox<String> cboTenSp;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton rdoHinhAnh;
    private javax.swing.JRadioButton rdoMuiHuong;
    private javax.swing.JRadioButton rdoTenSp;
    private javax.swing.JRadioButton rdoThuongHieu;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDungTich;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThuocTinh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
