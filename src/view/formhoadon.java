/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import service.HoaDonService;
import Model.HoaDonCTfull;
import Model.HoaDonCuaBang;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class formhoadon extends javax.swing.JPanel {

  private HoaDonService shd = new HoaDonService();
    
    private  DefaultTableModel dtm = new DefaultTableModel();
    private  int a = -1;
    private  int b = -1;
    public formhoadon() {
        initComponents();
        this.fill1(shd.GetAll());
        this.fill2(shd.Getallsp());
      
    }
    void  fill1(ArrayList<HoaDonCuaBang>list){
        dtm = (DefaultTableModel) tblhd.getModel();
        dtm.setRowCount(0);
        for (HoaDonCuaBang hoaDon : list) {
            dtm.addRow(hoaDon.toDatarow());
            
        }
    }
void  fill2(ArrayList<HoaDonCuaBang>list){
        dtm = (DefaultTableModel) tblhdct.getModel();
        dtm.setRowCount(0);
        for (HoaDonCuaBang hoaDon : list) {
            dtm.addRow(hoaDon.toData());
            
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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblhdct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btntim2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblhd = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("hóa đơn chi tiết");

        tblhdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "mã sản phẩm", " tên sản phẩm ", "trạng thái sản phẩm ", "ngày tạo ", " ngày sửa ", "mã hóa đơn chi tiết ", "số lượng ", "đơn giá", "tổng tiền"
            }
        ));
        tblhdct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhdctMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblhdct);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1457, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Quản Lý Hóa Đơn");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Hóa Đơn");

        btntim2.setText("tìm kiếm");
        btntim2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntim2MouseClicked(evt);
            }
        });
        btntim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntim2ActionPerformed(evt);
            }
        });

        tblhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã KH", "Mã NV", "Mã HDCT", "Trạng thái", "Ngày tạo", "Ngày sửa"
            }
        ));
        tblhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhdMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblhd);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btntim2))
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntim2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(654, 654, 654)
                .addComponent(jLabel1)
                .addContainerGap(729, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(15, 15, 15)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(306, 306, 306)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(268, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblhdctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhdctMouseClicked
        // TODO add your handling code here:
        b = tblhdct.getSelectedRow();
       

    }//GEN-LAST:event_tblhdctMouseClicked

    private void btntim2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntim2MouseClicked
        // TODO add your handling code here:
         String id = JOptionPane.showInputDialog(this, "mời bận nhập mã hóa đơn cần tìm", "for tìm kiếm", 3);
        if (shd.timkiem(id).isEmpty()) {
            JOptionPane.showMessageDialog(this, "không tìm thấy gì");

        } else {
            JOptionPane.showMessageDialog(this, "có tìm thấy dữ liệu");
            this.fill1(shd.timkiem(id));}
    }//GEN-LAST:event_btntim2MouseClicked

    private void tblhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhdMouseClicked
        // TODO add your handling code here:
         a = tblhd.getSelectedRow();
          int selectedRowIndex = tblhd.getSelectedRow();
    if (selectedRowIndex != -1) {
        // Lấy mã hóa đơn từ dòng được chọn, giả sử nó nằm ở cột số 0
        int maHD = (int) tblhd.getValueAt(selectedRowIndex, 0);
        
        // Lấy danh sách chi tiết hóa đơn từ mã hóa đơn
        ArrayList<HoaDonCuaBang> danhSachChiTiet = shd.GetHoaDonCTByMaHD(maHD);
        
        // Cập nhật bảng chi tiết hóa đơn
        fill2(danhSachChiTiet);
    }
    }//GEN-LAST:event_tblhdMouseClicked

    private void btntim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntim2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btntim2ActionPerformed
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntim2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblhd;
    private javax.swing.JTable tblhdct;
    // End of variables declaration//GEN-END:variables
}