/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import entities.Promotion_Class;
import DAO.PromotionDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Promotions extends javax.swing.JFrame {

    /**
     * Creates new form Promotions
     */
    PromotionDAO pd;
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

    public Promotions() {
        initComponents();
        pd = new PromotionDAO();
        ImageIcon img = new ImageIcon("image//Promotions.jpg");
        this.setIconImage(img.getImage());
        btnAdd.setSize(20, 20);
        new setImage().setImageButton(btnAdd, "image//floating-toucher_icon.png");
        btnUpdate.setSize(20, 20);
        new setImage().setImageButton(btnUpdate, "image//Setting-icon.png");
        btnDel.setSize(20, 20);
        new setImage().setImageButton(btnDel, "image//deleted.png");
        btnReset.setSize(20, 20);
        new setImage().setImageButton(btnReset, "image//3a4a6aea2a19c76c632a9092e7fd9a3f.png");
        loadTTtblPromo();
        btnDel.setEnabled(false);
        btnUpdate.setEnabled(false);
    }

    public void loadTTtblPromo() {
        DefaultTableModel model = (DefaultTableModel) tblPromo.getModel();
        model.setNumRows(0);
        for (Promotion_Class pc : pd.getAll()) {
            model.addRow(new Object[]{pc.getIDPromo(), pc.getNamePromo(), pc.getDiscountPromo(), pc.getStartPromo(), pc.getEndPromo(), pc.getDescription()});
            tblPromo.setModel(model);
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
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        txtDis = new javax.swing.JTextField();
        lbID1 = new javax.swing.JLabel();
        DateStart = new com.toedter.calendar.JDateChooser();
        DateEnd = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPromo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý chương trình khuyến mại");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin chương trình");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Tên chương trình (có thể ghi dấu):");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Chiếu khấu (%):");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Ngày bắt đầu:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Ngày kết thúc:");

        lbID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbID.setForeground(new java.awt.Color(0, 0, 255));
        lbID.setText("Mã:");

        lbID1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbID1.setForeground(new java.awt.Color(255, 0, 0));
        lbID1.setText("Tự động");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Mô tả:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tblPromo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên chương trình", "Chiết khấu", "Ngày bắt đầu", "Ngày kết thúc", "Mô tả"
            }
        ));
        tblPromo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPromoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPromo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbID1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDis)
                                            .addComponent(DateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(DateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(35, 35, 35)
                                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(DateStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbID1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDel)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int click = JOptionPane.showConfirmDialog(null, "Bạn muốn cập nhật chương trình này?");
        if (click == 0) {
            while (true) {
                if (txtDis.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Chiết khấu không được bỏ trống.");
                    return;
                } else if (!txtDis.getText().trim().matches("[0-9]+") || Integer.parseInt(txtDis.getText().trim()) > 100) {
                    JOptionPane.showMessageDialog(null, "Chiết khấu phải là số nguyên dương và <= 100.");
                    return;
                } else {

                    break;
                }
            }
            while (true) {
                if (DateStart.getCalendar() == null) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn thời gian bắt đầu");
                    return;
                } else if (DateEnd.getCalendar() == null) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn thời gian thúc");
                    return;
                }
                String start = ft.format(DateStart.getDate());
                String end = ft.format(DateEnd.getDate());
                if (start.compareTo(end) >= 0) {
                    JOptionPane.showMessageDialog(null, "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc.");
                    return;
                } else {
                    break;
                }
            }
            while (true) {
                if (txtDescription.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mô tả chương trình không được bỏ trống.");
                    txtDescription.grabFocus();
                    return;
                } else {
                    break;
                }
            }
            pd.capnhat(Integer.parseInt(txtDis.getText()), ft.format(DateStart.getDate()), ft.format(DateEnd.getDate()), txtDescription.getText(), lbID1.getText());
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            loadTTtblPromo();
            txtName.setText("");
            txtDis.setText("");
            txtDescription.setText("");
            btnDel.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnAdd.setEnabled(true);
            lbID1.setVisible(false);
            lbID.setVisible(false);
            txtName.setEnabled(true);
            DateStart.setDate(null);
            DateEnd.setDate(null);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtName.setText("");
        txtDis.setText("");
        txtDescription.setText("");
        btnDel.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnAdd.setEnabled(true);
        lbID1.setVisible(false);
        lbID.setVisible(false);
        txtName.setEnabled(true);
        DateStart.setDate(null);
        DateEnd.setDate(null);
        loadTTtblPromo();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int click = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm Chương trình khuyến mại không ?");
        if (click == 0) {
            int line = tblPromo.getRowCount();
            String name = txtName.getText();
            int chietkhau;
            String descript = txtDescription.getText();
            String start;
            String end;

            while (true) {
                if (name.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Tên chương trình không được bỏ trống.");
                    return;
                } else if (name.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Độ dài tối đa của tên chương trình là 50 ký tự.");
                    return;
                } else {
                    break;
                }
            }
            while (true) {
                if (txtDis.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Chiết khấu không được bỏ trống.");
                    return;
                } else if (!txtDis.getText().trim().matches("[0-9]+") || Integer.parseInt(txtDis.getText().trim()) > 100) {
                    JOptionPane.showMessageDialog(null, "Chiết khấu phải là số nguyên dương và <= 100.");
                    return;
                } else {
                    chietkhau = Integer.parseInt(txtDis.getText().trim());
                    break;
                }
            }
            while (true) {
                if (DateStart.getCalendar() == null) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn thời gian bắt đầu");
                    return;
                } else if (DateEnd.getCalendar() == null) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn thời gian thúc");
                    return;
                }
                start = ft.format(DateStart.getDate());
                end = ft.format(DateEnd.getDate());
                if (start.compareTo(end) >= 0) {
                    JOptionPane.showMessageDialog(null, "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc.");
                    return;
                } else {
                    break;
                }
            }
            while (true) {
                if (descript.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mô tả chương trình không được bỏ trống.");
                    return;
                } else {
                    break;
                }
            }
            pd.them(name, chietkhau, start, end, descript);
            loadTTtblPromo();
            JOptionPane.showMessageDialog(null, "Thêm thông tin thành công");

        } else {
            JOptionPane.showMessageDialog(null, "Thêm chương trình khuyến mãi thất bại");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblPromoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPromoMouseClicked
        try {
            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnDel.setEnabled(true);
            txtName.setEnabled(false);
            lbID1.setVisible(true);
            lbID.setVisible(true);
            int line = tblPromo.getSelectedRow();
            String ID = String.valueOf(tblPromo.getValueAt(line, 0));
            String ten = (String) tblPromo.getValueAt(line, 1);
            String ck = String.valueOf(tblPromo.getValueAt(line, 2));
            Date start = ft.parse((String) tblPromo.getValueAt(line, 3));
            Date end = ft.parse((String) tblPromo.getValueAt(line, 4));
            String mota = (String) tblPromo.getValueAt(line, 5);
            //gan du lieu vao textfile
            txtName.setText(ten);
            txtDescription.setText(mota);
            txtDis.setText(ck);
            DateStart.setDate(start);
            DateEnd.setDate(end);
            lbID.setEnabled(true);
            lbID1.setText(ID);
        } catch (ParseException ex) {
            Logger.getLogger(Promotions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblPromoMouseClicked

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int click = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa chương trình này?");
        if (click == 0) {
            pd.xoa(lbID1.getText());
            loadTTtblPromo();
            JOptionPane.showMessageDialog(null, "Xóa chương trình thành công!");
            txtName.setText("");
            txtDis.setText("");
            txtDescription.setText("");
            btnDel.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnAdd.setEnabled(true);
            lbID1.setVisible(false);
            lbID.setVisible(false);
            txtName.setEnabled(true);
            DateStart.setDate(null);
            DateEnd.setDate(null);
        }
    }//GEN-LAST:event_btnDelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Promotions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Promotions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Promotions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Promotions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Promotions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateEnd;
    private com.toedter.calendar.JDateChooser DateStart;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbID1;
    private javax.swing.JTable tblPromo;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtDis;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
