package view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import DAO.HistoryDAO;
import entities.Employee;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import entities.History_Class;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class History extends javax.swing.JFrame {

    /**
     * Creates new form History
     */
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");
    HistoryDAO hd;

    public History() {
        initComponents();
        hd = new HistoryDAO();
        ImageIcon img = new ImageIcon("image//history-icon-68319.png");
        this.setIconImage(img.getImage());
        btnSearch.setSize(30, 30);
        new setImage().setImageButton(btnSearch, "image//search-512.png");
        btnReset.setSize(30, 30);
        new setImage().setImageButton(btnReset, "image//3a4a6aea2a19c76c632a9092e7fd9a3f.png");
        btnPrint.setSize(50, 50);
        new setImage().setImageButton(btnPrint, "image//Printer-icon.png");

        loadcbEmpty();
        loadtblHistory();
        LoadlbTotal();
        btnPrint.setEnabled(false);
    }

    public void loadcbEmpty() {
        java.util.List<Employee> data = hd.getUsernameEmp();
        DefaultComboBoxModel<String> models = new DefaultComboBoxModel<>();
        for (Employee item : data) {
            models.addElement(item.getUsernameEmp());
        }
        cbEmp.setModel(models);
    }

    public void LoadlbTotal() {
        int total = 0;
        int line = tblHistory.getRowCount();
        for (int i = 0; i < line; i++) {
            String ThanhTien = (String) tblHistory.getValueAt(i, 10);
            total += Integer.parseInt(ThanhTien.replaceAll(",", ""));
        }
        lbTotal.setText(formatter.format(total) + " VNĐ");
    }

    public void loadtblHistory() {
        //khách hàng vip
        DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
        model.setNumRows(0);
        for (History_Class hc : hd.getByCustomer()) {
            int quanKHV = hc.getQuantity();
            int priceKHV = hc.getPrice();
            int discountKHV = hc.getDiscount();
            int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
            int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
            model.addRow(new Object[]{hc.getMaDH(), hc.getMaSP(), hc.getQuantity(), hc.getPrice(), hc.getNamepromotion(), hc.getMaKH(), hc.getDiscount(), hc.getThoigian(), hc.getNgay(), hc.getUsernameEmp(), formatter.format(totalKHV)});
            tblHistory.setModel(model);
        }
        //Theo chương trình khuyến mại
        DefaultTableModel model1 = (DefaultTableModel) tblHistory.getModel();
        model1.setNumRows(0);
        for (History_Class hc : hd.getAllByCTKM()) {
            int quanKHV = hc.getQuantity();
            int priceKHV = hc.getPrice();
            int discountKHV = hc.getDiscount();
            int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
            int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
            model1.addRow(new Object[]{hc.getMaDH(), hc.getMaSP(), hc.getQuantity(), hc.getPrice(), hc.getNamepromotion(), hc.getMaKH(), hc.getDiscount(), hc.getThoigian(), hc.getNgay(), hc.getUsernameEmp(), formatter.format(totalKHV)});
            tblHistory.setModel(model1);
        }
        //Không áp dụng CTKM
        DefaultTableModel model2 = (DefaultTableModel) tblHistory.getModel();
        model2.setNumRows(0);
        for (History_Class hc : hd.getAll()) {
            int quanKHV = hc.getQuantity();
            int priceKHV = hc.getPrice();
            int discountKHV = hc.getDiscount();
            int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
            int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
            model2.addRow(new Object[]{hc.getMaDH(), hc.getMaSP(), hc.getQuantity(), hc.getPrice(), hc.getNamepromotion(), hc.getMaKH(), hc.getDiscount(), hc.getThoigian(), hc.getNgay(), hc.getUsernameEmp(), formatter.format(totalKHV)});
            tblHistory.setModel(model2);
        }
    }

    public void timkiemTheoTen() {
        String ten = String.valueOf(cbEmp.getSelectedItem());
        while (true) {
            if (cbEmp.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Tài khoản nhân viên không được để trống.");
                btnPrint.setEnabled(false);
                return;
            } else if (txtdate.getCalendar() == null) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn ngày để tìm kiếm");
                return;
            } else {
                break;
            }
        }
        if (hd.getOrder(ten, ft.format(txtdate.getDate()))) {
            {
                //khách hàng vip
                DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
                model.setNumRows(0);
                for (History_Class hc : hd.TK_getByCustomer(ten, ft.format(txtdate.getDate()))) {
                    int quanKHV = hc.getQuantity();
                    int priceKHV = hc.getPrice();
                    int discountKHV = hc.getDiscount();
                    int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                    int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                    model.addRow(new Object[]{hc.getMaDH(), hc.getMaSP(), hc.getQuantity(), hc.getPrice(), hc.getNamepromotion(), hc.getMaKH(), hc.getDiscount(), hc.getThoigian(), hc.getNgay(), hc.getUsernameEmp(), formatter.format(totalKHV)});
                    tblHistory.setModel(model);
                }
                //Theo chương trình khuyến mại
                DefaultTableModel model1 = (DefaultTableModel) tblHistory.getModel();
                model1.setNumRows(0);
                for (History_Class hc : hd.TK_getAllByCTKM(ten, ft.format(txtdate.getDate()))) {
                    int quanKHV = hc.getQuantity();
                    int priceKHV = hc.getPrice();
                    int discountKHV = hc.getDiscount();
                    int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                    int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                    model1.addRow(new Object[]{hc.getMaDH(), hc.getMaSP(), hc.getQuantity(), hc.getPrice(), hc.getNamepromotion(), hc.getMaKH(), hc.getDiscount(), hc.getThoigian(), hc.getNgay(), hc.getUsernameEmp(), formatter.format(totalKHV)});
                    tblHistory.setModel(model1);
                }
                //Không áp dụng CTKM
                DefaultTableModel model2 = (DefaultTableModel) tblHistory.getModel();
                model2.setNumRows(0);
                for (History_Class hc : hd.TK_getall(ten, ft.format(txtdate.getDate()))) {
                    int quanKHV = hc.getQuantity();
                    int priceKHV = hc.getPrice();
                    int discountKHV = hc.getDiscount();
                    int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                    int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                    model2.addRow(new Object[]{hc.getMaDH(), hc.getMaSP(), hc.getQuantity(), hc.getPrice(), hc.getNamepromotion(), hc.getMaKH(), hc.getDiscount(), hc.getThoigian(), hc.getNgay(), hc.getUsernameEmp(), formatter.format(totalKHV)});
                    tblHistory.setModel(model2);
                }
                btnPrint.setEnabled(true);
            }
        } else {
            btnPrint.setEnabled(false);
            DefaultTableModel model3 = (DefaultTableModel) tblHistory.getModel();
            model3.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Nhân viên " + ten + " chưa bán được sản phẩm nào trong ngày " + ft.format(txtdate.getDate()) + ".");

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbEmp = new javax.swing.JComboBox();
        txtdate = new com.toedter.calendar.JDateChooser();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lịch sử bán hàng");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("TK Nhân Viên :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Ngày Lập Đơn Hàng:");

        cbEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbEmpMouseClicked(evt);
            }
        });

        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("Tổng Số Tiền:");

        lbTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 51, 51));
        lbTotal.setText("0 VND");

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn hàng", "Mã sản phẩm", "Số lượng (Ly)", "Đơn giá (VNĐ)", "Tên CTKM", "Mã khách hàng", "Chiết khấu (%)", "Thời gian", "Ngày", "TK nhân viên", "Thành tiền (VNĐ)"
            }
        ));
        jScrollPane1.setViewportView(tblHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset)
                        .addContainerGap(145, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearch)
                        .addComponent(btnReset))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(lbTotal))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cbEmp.setSelectedIndex(0);
        loadtblHistory();
        LoadlbTotal();
        btnPrint.setEnabled(false);
        txtdate.setCalendar(null);
    }//GEN-LAST:event_btnResetActionPerformed

    private void cbEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbEmpMouseClicked

    }//GEN-LAST:event_cbEmpMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        timkiemTheoTen();
        LoadlbTotal();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        //xóa file txt
        File file = new File("History.txt");
        file.delete();
        //Viết vào file txt
        String ten = String.valueOf(cbEmp.getSelectedItem());
        try {
            Date now = new Date();
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History.txt"), "UTF8"));
            b.write("THE GARDEN COFFEE\r\n\r\n");
            b.write("Địa chỉ: Hà Nội\r\n");
            b.write("SĐT: 19008198\r\n");
            b.write("Thời gian in: " + time.format(now) + "\r\n\r\n");
            b.write("\t\t\t\t\tBẢNG THỐNG KÊ BÁN HÀNG NGÀY " + ft.format(txtdate.getDate()) + "\r\n");
            b.write("Tài khoản: " + cbEmp.getSelectedItem() + "\r\n");
            Employee e = new Employee();
            e = hd.getNameEmployee(ten);
            b.write("Tên nhân viên: " + e.getNameEmp() + "\r\n\r\n");
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Mã ĐH\tMã SP\tSố lượng (ly)\tĐơn giá (VNĐ)\tTên CTKM\tMã khách hàng\tChiết khấu (%)\tThời gian\tThành tiền (VNĐ)\r\n");
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");

            int line = tblHistory.getRowCount();
            for (int i = 0; i < line; i++) {
                String s1 = (String) tblHistory.getValueAt(i, 0);
                String s2 = (String) tblHistory.getValueAt(i, 1);
                String s3 = String.valueOf(tblHistory.getValueAt(i, 2));
                String s4 = String.valueOf(tblHistory.getValueAt(i, 3));
                String s5 = (String) tblHistory.getValueAt(i, 4);
                String MKH = (String) tblHistory.getValueAt(i, 5);
                String s6;
                if (!MKH.equals("Khách vãng lai")) {
                    s6 = (String) tblHistory.getValueAt(i, 5) + "\t";
                } else {
                    s6 = (String) tblHistory.getValueAt(i, 5);
                }
                String s7 = String.valueOf(tblHistory.getValueAt(i, 6));
                String s8 = (String) tblHistory.getValueAt(i, 7);
                String s11 = (String) tblHistory.getValueAt(i, 10);
                b.write(s1 + "\t" + s2 + "\t" + s3 + "\t\t" + s4 + "\t\t" + s5 + "\t" + s6 + "\t" + s7 + "\t\t" + s8 + "\t" + s11 + "\r\n");
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Tổng tiền: " + lbTotal.getText());
            b.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad History.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        btnResetActionPerformed(evt);
    }//GEN-LAST:event_btnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new History().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblHistory;
    private com.toedter.calendar.JDateChooser txtdate;
    // End of variables declaration//GEN-END:variables
}
