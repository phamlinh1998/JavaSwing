/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.EmployeeDAO;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class PasswordChange extends javax.swing.JFrame {

    /**
     * Creates new form PasswordChange
     */
    boolean flag1 = false, flag2 = false, flag3 = false;
    EmployeeDAO ed;

    public PasswordChange(String name) {
        initComponents();
        ed = new EmployeeDAO();
        lbName.setText(name);
        lbName.setVisible(false);
        btnOK.setEnabled(false);
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
        jLabel3 = new javax.swing.JLabel();
        txtPassCu = new javax.swing.JPasswordField();
        txtPassNew = new javax.swing.JPasswordField();
        txtConfirmPass = new javax.swing.JPasswordField();
        btnOK = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbName = new javax.swing.JLabel();
        lbPassCu = new javax.swing.JLabel();
        lbPassNew = new javax.swing.JLabel();
        lbConfirmPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thay đổi mật khẩu");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Mật khẩu cũ:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Mật khẩu mới:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Nhập lại mật khẩu:");

        txtPassCu.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPassCuCaretUpdate(evt);
            }
        });

        txtPassNew.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPassNewCaretUpdate(evt);
            }
        });

        txtConfirmPass.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConfirmPassCaretUpdate(evt);
            }
        });
        txtConfirmPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPassActionPerformed(evt);
            }
        });

        btnOK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOK.setForeground(new java.awt.Color(102, 0, 102));
        btnOK.setText("Đồng Ý");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 204));
        jButton2.setText("Làm Mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbName)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(btnOK)
                        .addGap(54, 54, 54)
                        .addComponent(jButton2)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbPassCu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPassCu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbConfirmPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbPassNew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassNew))
                                .addGap(36, 36, 36))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPassCu)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(lbPassNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lbConfirmPass)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOK)
                            .addComponent(jButton2))
                        .addContainerGap(75, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbName)
                        .addGap(28, 28, 28))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtConfirmPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmPassActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtPassCu.setText("");
        txtPassNew.setText("");
        txtConfirmPass.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPassCuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPassCuCaretUpdate
        if (txtPassCu.getText().trim().equals("")) {
            lbPassCu.setText(" ");
            btnOK.setEnabled(false);
        } else {
            if (!ed.getPassword(lbName.getText(), txtPassCu.getText())) {

                lbPassCu.setText("Sai mật khẩu.");
                lbPassCu.setForeground(Color.red);
                flag1 = false;
                btnOK.setEnabled(false);
            } else {
                lbPassCu.setText("Đúng mật khẩu.");
                lbPassCu.setForeground(Color.blue);
                flag1 = true;
                btnOK.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtPassCuCaretUpdate

    private void txtPassNewCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPassNewCaretUpdate
        if (txtPassNew.getText().trim().equals("")) {
            lbPassNew.setText(" ");
            btnOK.setEnabled(false);
        } else if (txtPassNew.getText().equals(txtPassCu.getText())) {
            lbPassNew.setText("Mật khẩu mới phải khác mật khẩu cũ.");
            lbPassNew.setForeground(Color.red);
            btnOK.setEnabled(false);
        } else {
            char x;
            for (int i = 0; i < txtPassNew.getText().length(); i++) {
                x = txtPassNew.getText().charAt(i);
                if (x == ' ') {
                    lbPassNew.setText("Mật khẩu không thể chứa khoảng trắng.");
                    lbPassNew.setForeground(Color.red);
                    flag2 = false;
                    btnOK.setEnabled(false);
                    return;
                }
            }
            while (true) {
                if (txtPassNew.getText().length() < 6 || txtPassNew.getText().length() > 18) {
                    lbPassNew.setText("Độ dài mật khẩu trong khoảng 6-18 kí tự.");
                    lbPassNew.setForeground(Color.red);
                    flag2 = false;
                    btnOK.setEnabled(false);
                    return;
                } else {
                    lbPassNew.setText("Mật khẩu hợp lệ.");
                    lbPassNew.setForeground(Color.blue);
                    flag2 = true;
                    btnOK.setEnabled(false);
                    break;
                }
            }
        }
    }//GEN-LAST:event_txtPassNewCaretUpdate

    private void txtConfirmPassCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConfirmPassCaretUpdate
       if (txtConfirmPass.getText().trim().equals("")) {
            lbConfirmPass.setText(" ");
            btnOK.setEnabled(false);
        } else {
            while (true) {
                if (!txtConfirmPass.getText().equals(txtPassNew.getText())) {
                    lbConfirmPass.setText("Nhập lại mật khẩu phải giống mật khẩu.");
                    lbConfirmPass.setForeground(Color.red);
                    flag3 = false;
                    btnOK.setEnabled(false);
                    return;
                } else {
                    lbConfirmPass.setText("Hợp lệ.");
                    lbConfirmPass.setForeground(Color.blue);
                    flag3 = true;
                    btnOK.setEnabled(true);
                    break;
                }
            }
        }
    }//GEN-LAST:event_txtConfirmPassCaretUpdate

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (flag1 == true && flag2 == true && flag3 == true) {
            ed.capnhat_password(txtConfirmPass.getText(), lbName.getText());
            JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công.");
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
        }
    }//GEN-LAST:event_btnOKActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbConfirmPass;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPassCu;
    private javax.swing.JLabel lbPassNew;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtPassCu;
    private javax.swing.JPasswordField txtPassNew;
    // End of variables declaration//GEN-END:variables
}
