/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.*;
import java.awt.Dimension;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import DAO.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class BillForm extends javax.swing.JFrame {

    /**
     * Creates new form BillForm
     */
    SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    banhangDAO bhd;
    OrderDAO od;
    RevenueDAO rd;
    CustomerDAO cd;
    EmployeeDAO ed;
    NumberFormat formatter = new DecimalFormat("#,###");

    public BillForm(String EmpName) {
        initComponents();
        clock();
        bhd = new banhangDAO();
        od = new OrderDAO();
        rd = new RevenueDAO();
        cd = new CustomerDAO();
        ed = new EmployeeDAO();
        spQuantity.setValue(1);
        txtEmpName.setText(EmpName);
        lbtt.setSize(30, 30);
        new setImage().setImagelable(lbtt, "image//item_s11553.png");
        btnPrint.setSize(40, 40);
        new setImage().setImageButton(btnPrint, "image//Printer_Picture.png");
        btnAdd.setSize(20, 20);
        new setImage().setImageButton(btnAdd, "image//addtocart.png");
        btnDel.setSize(40, 40);
        new setImage().setImageButton(btnDel, "image//delete.png");
//        lbnen.setSize(304, 367);
        new setImage().setImagelable(lbnen, "image//anhnencafe1.jpg");
        spQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        loadTenSP();
        loadkichthuoc();
        loadTenPromotion();
        lbmathe.setVisible(false);
        txtID.setVisible(false);
        panelTTKh.setVisible(false);
        Panelthanhtoan(false);
        txtGuest.setEnabled(false);
        btnPrint.setEnabled(false);
    }

    public void Panelthanhtoan(boolean b) {
        lbTT.setEnabled(false);
        lbTC.setEnabled(false);
        txtTotal.setDisabledTextColor(Color.BLUE);
        lbCK.setEnabled(false);
        lbthanhtien.setEnabled(false);
        lbphantram.setEnabled(false);
        txtDis1.setEnabled(false);
        txtDis1.setDisabledTextColor(Color.BLUE);
        txtDis2.setEnabled(false);
        txtDis2.setDisabledTextColor(Color.BLUE);
        txtTotal.setEnabled(false);
        txtPay.setDisabledTextColor(Color.red);
        txtPay.setEnabled(false);
    }

    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Date t = new Date();
                        lbTime.setText(ft.format(t));
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        clock.start();
    }

    public void tongtien() {
        int price, totalprice = 0;
        int count = tblBill.getRowCount();
        for (int i = 0; i < count; i++) {
            price = Integer.parseInt(String.valueOf(tblBill.getValueAt(i, 6)));
            totalprice += price;
        }
        txtTotal.setText(formatter.format(totalprice));
    }

    public void UpdatetxtDis1() {
        int Dis;
        if (cbCTKM.getSelectedIndex() > 0) {
            int discount = bhd.getDis(String.valueOf(cbCTKM.getSelectedItem()));
            txtDis1.setText(String.valueOf(discount));
            String Order = txtTotal.getText().replaceAll(",", "");
            Dis = (Integer.parseInt(txtDis1.getText()) * Integer.parseInt(Order)) / 100;
            txtDis2.setText(formatter.format(Dis));
            //tính total
            int total = Integer.parseInt(Order) - Dis;
            txtPay.setText(formatter.format(total));
        } else {
            txtDis1.setText("0");
            //tính Discount
            String Order = txtTotal.getText().replaceAll(",", "");
            Dis = (Integer.parseInt(txtDis1.getText()) * Integer.parseInt(Order)) / 100;
            txtDis2.setText(formatter.format(Dis));
            //tính total
            int total = Integer.parseInt(Order) - Dis;
            txtPay.setText(formatter.format(total));
        }

    }

    public void loadBill() {
        DefaultTableModel model = (DefaultTableModel) tblBill.getModel();
        String ten = String.valueOf(cbProduct.getSelectedItem());
        String kichthuoc = String.valueOf(cbSize.getSelectedItem());
        for (Product_CLass p : bhd.getAll(ten, kichthuoc)) {
            int price, thanhtien, quantity;
            price = p.getPrice();
            quantity = Integer.parseInt(spQuantity.getValue().toString());
            thanhtien = price * quantity;
            model.addRow(new Object[]{p.getIDProduct(), p.getProductName(), p.getIDType(), p.getPrice(), p.getSize(), quantity, thanhtien});
            tblBill.setModel(model);
        }
    }

    public void loadTenPromotion() {
        Date t = new Date();
        String ngay = format.format(t);
        java.util.List<Promotion_Class> data = bhd.getNamepromotion(ngay);
        DefaultComboBoxModel<String> models = new DefaultComboBoxModel<>();
        models.addElement("Không có");
        models.addElement("Khách hàng vip");
        for (Promotion_Class item : data) {
            models.addElement(item.getNamePromo());
        }
        cbCTKM.setModel(models);
    }

    public void loadTenSP() {
        java.util.List<Product_CLass> data = bhd.getNamePro();
        DefaultComboBoxModel<String> models = new DefaultComboBoxModel<>();
        for (Product_CLass item : data) {
            models.addElement(item.getProductName());
        }
        cbProduct.setModel(models);
    }

    public void loadkichthuoc() {
        java.util.List<ProductType_Class> data = bhd.getSize();
        DefaultComboBoxModel<String> models = new DefaultComboBoxModel<>();
        for (ProductType_Class item : data) {
            models.addElement(item.getSize());
        }
        cbSize.setModel(models);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new java.awt.Panel(){
            ImageIcon icon = new ImageIcon("image//background.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
            }
        };
        jLabel2 = new javax.swing.JLabel();
        cbCTKM = new javax.swing.JComboBox<String>();
        lbmathe = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lbIDError = new javax.swing.JLabel();
        panelTTKh = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbIDCus = new javax.swing.JLabel();
        lbNameCus = new javax.swing.JLabel();
        lbDateCus = new javax.swing.JLabel();
        lbQuantityCus = new javax.swing.JLabel();
        lbDisCus = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JTextField();
        cbProduct = new javax.swing.JComboBox<String>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbSize = new javax.swing.JComboBox<String>();
        spQuantity = new javax.swing.JSpinner();
        panelThanhToan = new javax.swing.JPanel();
        lbTT = new javax.swing.JLabel();
        lbTC = new javax.swing.JLabel();
        lbCK = new javax.swing.JLabel();
        lbthanhtien = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtPay = new javax.swing.JTextField();
        txtGuest = new javax.swing.JTextField();
        txtRepay = new javax.swing.JTextField();
        txtIDBill = new javax.swing.JTextField();
        txtDis1 = new javax.swing.JTextField();
        lbphantram = new javax.swing.JLabel();
        txtDis2 = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        lbtt = new javax.swing.JLabel();
        lbLoiGia = new javax.swing.JLabel();
        lbloiMaHD = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        lbTime = new javax.swing.JLabel();
        lbnen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miInformation = new javax.swing.JMenuItem();
        miPassChange = new javax.swing.JMenuItem();
        miLogout = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý bán hàng");

        jPanel1.setForeground(new java.awt.Color(255, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Chương trình khuyến mãi:");

        cbCTKM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCTKMActionPerformed(evt);
            }
        });

        lbmathe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbmathe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbmathe.setText("Nhập mã thẻ:");

        txtID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtID.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIDCaretUpdate(evt);
            }
        });
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Thông tin khách hàng:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Mã thẻ:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Họ và tên:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Ngày đăng ký:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Số ly đã mua:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Được giảm:");

        lbIDCus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbIDCus.setText("................................");

        lbNameCus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNameCus.setText("................................");

        lbDateCus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDateCus.setText("................................");

        lbQuantityCus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbQuantityCus.setText("................................");

        lbDisCus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDisCus.setText("................................");

        javax.swing.GroupLayout panelTTKhLayout = new javax.swing.GroupLayout(panelTTKh);
        panelTTKh.setLayout(panelTTKhLayout);
        panelTTKhLayout.setHorizontalGroup(
            panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTTKhLayout.createSequentialGroup()
                .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTTKhLayout.createSequentialGroup()
                        .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbDateCus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNameCus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbIDCus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTTKhLayout.createSequentialGroup()
                        .addComponent(lbDisCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(lbQuantityCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelTTKhLayout.setVerticalGroup(
            panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTKhLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIDCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDateCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQuantityCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDisCus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbmathe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(cbCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbIDError, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelTTKh, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbmathe, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIDError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTTKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        jLabel15.setText("Tên nhân viên:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("Tên sản phẩm");

        txtEmpName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEmpName.setDisabledTextColor(new java.awt.Color(255, 0, 0));

        txtEmpName.setEnabled(false);

        txtEmpName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpNameActionPerformed(evt);
            }
        });
        txtEmpName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpNameActionPerformed(evt);
            }
        });

        cbProduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 0, 204));
        jLabel17.setText("Số lượng:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 204));
        jLabel18.setText("Kích thước:");

        cbSize.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbTT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTT.setForeground(new java.awt.Color(255, 0, 0));
        lbTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTT.setText("Thanh Toán");

        lbTC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTC.setForeground(new java.awt.Color(0, 0, 255));
        lbTC.setText("Tổng cộng:");

        lbCK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCK.setForeground(new java.awt.Color(0, 0, 255));
        lbCK.setText("Chiết khấu:");

        lbthanhtien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbthanhtien.setForeground(new java.awt.Color(255, 0, 0));
        lbthanhtien.setText("Thành tiền:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 255));
        jLabel24.setText("Tiền khách đưa:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 0, 51));
        jLabel25.setText("Tiền trả lại:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 255));
        jLabel26.setText("Mã hóa đơn:");

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 0, 255));
        txtTotal.setText("0");

        txtPay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPay.setForeground(new java.awt.Color(255, 0, 102));
        txtPay.setText("0");
        txtPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPayActionPerformed(evt);
            }
        });

        txtGuest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGuest.setForeground(new java.awt.Color(0, 0, 255));
        txtGuest.setText("0");
        txtGuest.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGuestCaretUpdate(evt);
            }
        });

        txtRepay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtRepay.setForeground(new java.awt.Color(255, 0, 0));
        txtRepay.setText("0");
        txtRepay.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRepayCaretUpdate(evt);
            }
        });

        txtIDBill.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDBill.setForeground(new java.awt.Color(0, 0, 255));
        txtIDBill.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIDBillCaretUpdate(evt);
            }
        });

        txtDis1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDis1.setForeground(new java.awt.Color(0, 0, 255));
        txtDis1.setText("0");
        txtDis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDis1ActionPerformed(evt);
            }
        });

        lbphantram.setForeground(new java.awt.Color(255, 0, 0));
        lbphantram.setText("%");

        txtDis2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 255, 51));
        btnPrint.setText("Lưu và In");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        lbLoiGia.setForeground(new java.awt.Color(255, 0, 51));

        lbloiMaHD.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout panelThanhToanLayout = new javax.swing.GroupLayout(panelThanhToan);
        panelThanhToan.setLayout(panelThanhToanLayout);
        panelThanhToanLayout.setHorizontalGroup(
            panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThanhToanLayout.createSequentialGroup()
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThanhToanLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lbtt, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelThanhToanLayout.createSequentialGroup()
                        .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbthanhtien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbloiMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbLoiGia, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addComponent(txtRepay)
                                .addComponent(txtGuest)
                                .addComponent(txtPay)
                                .addGroup(panelThanhToanLayout.createSequentialGroup()
                                    .addComponent(txtDis1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbphantram, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDis2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTotal)
                                .addComponent(txtIDBill, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        panelThanhToanLayout.setVerticalGroup(
            panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThanhToanLayout.createSequentialGroup()
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelThanhToanLayout.createSequentialGroup()
                        .addComponent(lbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(panelThanhToanLayout.createSequentialGroup()
                        .addComponent(lbtt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTC, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCK, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDis2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbphantram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 52, Short.MAX_VALUE)
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGuest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLoiGia)
                .addGap(4, 4, 4)
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRepay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lbloiMaHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        tblBill.setBackground(new java.awt.Color(0, 255, 0));
        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Nhóm", "Đơn giá (VNĐ)", "kích thước", "Số lượng (ly)", "Thành tiền (VNĐ)"
            }
        ));
        jScrollPane1.setViewportView(tblBill);

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 0, 51));
        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        lbTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmpName))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spQuantity)
                            .addComponent(cbSize, 0, 106, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbnen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(panelThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbnen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtEmpName, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spQuantity))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(cbSize)))
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Contact-icon.png"))); // NOI18N
        jMenu1.setText("Tài Khoản");

        miInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/070537-glossy-black-3d-button-icon-alphanumeric-information2-ps.png"))); // NOI18N
        miInformation.setText("Thông Tin Tài Khoản");
        miInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miInformationActionPerformed(evt);
            }
        });
        jMenu1.add(miInformation);

        miPassChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_pass.png"))); // NOI18N
        miPassChange.setText("Đổi Mật Khẩu");
        miPassChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPassChangeActionPerformed(evt);
            }
        });
        jMenu1.add(miPassChange);

        miLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logout.png"))); // NOI18N
        miLogout.setText("Đăng Xuất");
        miLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(miLogout);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Information.png"))); // NOI18N
        jMenu2.setText("Giới Thiệu\n");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Health_Insurance-512.png"))); // NOI18N
        jMenu4.setText("Chi Tiết Hóa Đơn");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miPassChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPassChangeActionPerformed
        new PasswordChange(txtEmpName.getText()).setVisible(true);
    }//GEN-LAST:event_miPassChangeActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtDis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDis1ActionPerformed

    private void txtPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPayActionPerformed

    private void txtEmpNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpNameActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        loadBill();
        tongtien();
        UpdatetxtDis1();
        cbProduct.setSelectedIndex(0);
        spQuantity.setValue(1);
        cbSize.setSelectedIndex(0);
        btnPrint.setEnabled(false);
        txtIDBill.setEnabled(false);
        txtPay.setEnabled(false);
        txtRepay.setEnabled(false);
        txtGuest.setEnabled(true);
        txtIDBill.setEnabled(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCTKMActionPerformed
        UpdatetxtDis1();
        if (String.valueOf(cbCTKM.getSelectedItem()).equals("Khách hàng vip")) {
            lbmathe.setVisible(true);
            txtID.setVisible(true);
            panelTTKh.setVisible(true);

        } else {
            panelTTKh.setVisible(false);
            lbmathe.setVisible(false);
            txtID.setVisible(false);
        }
    }//GEN-LAST:event_cbCTKMActionPerformed

    private void miLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLogoutActionPerformed
        int click = JOptionPane.showConfirmDialog(null, "Đăng xuất ngay bây giờ?");
        if (click == 0) {
            this.setVisible(false);
            new LoginForm().setVisible(true);
        }
    }//GEN-LAST:event_miLogoutActionPerformed

    private void miInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miInformationActionPerformed
        new Information(txtEmpName.getText()).setVisible(true);
    }//GEN-LAST:event_miInformationActionPerformed

    private void txtIDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDCaretUpdate
        if (txtID.getText().trim().equals("")) {
            txtDis1.setText("0");
            lbIDError.setText("Vui lòng nhập mã thẻ.");
            lbIDError.setForeground(Color.red);
            ResetPnInfor();
        } else {
            while (true) {
                if (!txtID.getText().trim().matches("\\d+")) {
                    lbIDError.setText("Mã thẻ dạng số.");
                    lbIDError.setForeground(Color.red);
                    txtDis1.setText("0");
                    ResetPnInfor();
                    return;
                } else {
                    break;
                }
            }

            Customer_Class cl = new Customer_Class();
            cl = bhd.getThongTinKH(Integer.parseInt(txtID.getText()));
            lbIDCus.setText(String.valueOf(cl.getIDCus()));
            lbNameCus.setText(cl.getCusName());
            lbDateCus.setText(cl.getDate());
            lbQuantityCus.setText(String.valueOf(cl.getQuantity()));
            lbDisCus.setText(String.valueOf(cl.getDiscount()) + " %");
            txtDis1.setText(String.valueOf(cl.getDiscount()));

            if (lbIDCus.getText().trim().equals("0")) {
                lbIDError.setText("Mã thẻ không tồn tại!");
                lbIDError.setForeground(Color.red);
            } else {
                lbIDError.setText("Thành công.");
                txtRepay.setEnabled(false);
                txtIDBill.setEnabled(false);
                lbIDError.setForeground(Color.BLUE);
            }
        }
    }//GEN-LAST:event_txtIDCaretUpdate

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int line = tblBill.getSelectedRow();
        if (line > -1) {
            DefaultTableModel tblModel = (DefaultTableModel) tblBill.getModel();;
            tblModel.removeRow(line);
            if (tblBill.getRowCount() > 0) {
                txtGuest.setEnabled(true);
            } else {
                txtGuest.setEnabled(false);
            }
            txtPay.setText("0");
            txtTotal.setText("0");
            txtGuest.setText("0");
            txtGuest.setEnabled(false);
            txtRepay.setText("0");
            btnPrint.setEnabled(false);
            txtIDBill.setEnabled(false);
            txtIDBill.setText("");
            txtDis2.setText("0");
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn hàng cần xóa");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void txtRepayCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRepayCaretUpdate

    }//GEN-LAST:event_txtRepayCaretUpdate

    private void txtGuestCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGuestCaretUpdate
        int repay;
        while (true) {
            if (txtGuest.getText().trim().equals("")) {
                lbLoiGia.setText("Khách hàng chưa đưa tiền.");
                txtRepay.setText("0");
                txtIDBill.setEnabled(false);
                return;
            } else if (!txtGuest.getText().trim().matches("\\d+")) {
                lbLoiGia.setText("Tiền có dạng số.");
                txtRepay.setText("0");
                txtIDBill.setEnabled(false);
                return;
            } else {
                lbLoiGia.setText("");
                txtIDBill.setEnabled(false);
                break;
            }
        }
        String total = txtPay.getText().replaceAll(",", "");
        repay = Integer.parseInt(txtGuest.getText()) - Integer.parseInt(total);
        txtRepay.setText(formatter.format(repay));
        if (repay < 0) {
            lbLoiGia.setText("Khách hàng chưa đưa đủ tiền.");
            txtIDBill.setEnabled(false);
            txtRepay.setText("0");
        } else if (Integer.parseInt(txtGuest.getText()) == 0) {
            txtIDBill.setEnabled(false);
            txtRepay.setText("0");
        } else {
            lbLoiGia.setText("");
            txtIDBill.setEnabled(true);
        }
    }//GEN-LAST:event_txtGuestCaretUpdate

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        new AboutUs().setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        new History().setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed

        luu();
        updateCustomer();
        updateRenevue();
        inBill();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtIDBillCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDBillCaretUpdate
        while (true) {
            if (txtIDBill.getText().trim().equals("")) {
                lbloiMaHD.setText("Mã hóa đơn không được để trống.");
                return;
            } else if (!txtIDBill.getText().trim().matches("HD[0-9]{4}")) {
                lbloiMaHD.setText("Mã hóa đơn có dạng HDxxxx");
                return;
            } else if (!bhd.getIDOrder(txtIDBill.getText().trim())) {
                lbloiMaHD.setText("Mã hóa đơn đã tồn tại.");
                btnPrint.setEnabled(false);
                return;
            } else {
                lbloiMaHD.setText("Thành công");
                btnPrint.setEnabled(true);
                break;
            }

        }

    }//GEN-LAST:event_txtIDBillCaretUpdate

    public void luu() {
        if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
            while (true) {
                if (txtID.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mã thẻ VIP không được để trống!");
                    return;
                } else if (!txtID.getText().trim().equals("") && !lbIDError.getText().equals("Thành công.")) {
                    JOptionPane.showMessageDialog(null, "Mã thẻ VIP chưa đúng, vui lòng nhập lại!");
                    return;
                } else {
                    break;
                }
            }
        }
        while (true) {

            if (!bhd.getIDOrder(txtIDBill.getText().trim())) {
                return;
            } else {

                int line = tblBill.getRowCount();
                int quantity = 0;
                Employee e = new Employee();
                e = ed.getByName(txtEmpName.getText());
                String ma = e.getUsernameEmp();
                if (od.SaveOrder(txtIDBill.getText().trim(), ma)) {
                    for (int i = 0; i < line; i++) {
                        String IDProduct = (String) tblBill.getValueAt(i, 0);
                        int ttquantity = Integer.parseInt(String.valueOf(tblBill.getValueAt(i, 5))) + quantity;
                        String tenpro = String.valueOf(cbCTKM.getSelectedItem());
                        if (tenpro.equals("Khách hàng VIP")) {
                            od.SaveOrderDetails(txtIDBill.getText(), IDProduct, lbIDCus.getText(), ttquantity, tenpro);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "thêm đơn hàng thất bại");
                }
                break;
            }
        }

    }

    public void updateRenevue() {
        String pay = txtPay.getText().replaceAll(",", "");
        if (rd.getMoney1() == null) {
            rd.addRevenue(pay);
        } else {
            Revenue_Class rc = new Revenue_Class();
            rc = rd.getMoney1();
            int money = Integer.parseInt(rc.getMoney());
            int total = money + Integer.parseInt(pay);
            rd.updateRevenue(String.valueOf(total));
        }
    }

    public void updateCustomer() {
        int line = tblBill.getRowCount();
        int quantity = Integer.parseInt(lbQuantityCus.getText()), ttquantity = 0;

        for (int i = 0; i < line; i++) {
            ttquantity += Integer.parseInt(String.valueOf(tblBill.getValueAt(i, 5)));
        }

        int quannew = quantity + ttquantity;
        if (quannew < 10) {
            cd.updateQuanDis(quannew, 0, Integer.parseInt(lbIDCus.getText()));
        } else if (quannew >= 10 && quannew < 20) {
            cd.updateQuanDis(quannew, 5, Integer.parseInt(lbIDCus.getText()));
        } else if (quannew >= 20 && quannew < 30) {
            cd.updateQuanDis(quannew, 10, Integer.parseInt(lbIDCus.getText()));
        } else if (quannew >= 30 && quannew < 40) {
            cd.updateQuanDis(quannew, 15, Integer.parseInt(lbIDCus.getText()));
        } else if (quannew >= 40 && quannew < 50) {
            cd.updateQuanDis(quannew, 20, Integer.parseInt(lbIDCus.getText()));
        } else if (quannew >= 50) {
            cd.updateQuanDis(quannew, 25, Integer.parseInt(lbIDCus.getText()));
        }
    }

    public void inBill() {
        try {
            int guest = Integer.parseInt(txtGuest.getText());
            Date now = new Date();
            File file = new File("LichSuBanHang.txt");
            file.delete();
            Writer bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("LichSuBanHang.txt"), "UTF8"));
            bw.write("\t\t\tTHE GARDEN COFFEE\r\n\r\n");
            bw.write("\t\tHà Nội\r\n");
            bw.write("\t\t\tSĐT: 19008198\r\n\r\n");
            bw.write("\t\t\tHÓA ĐƠN BÁN HÀNG\r\n\r\n");
            bw.write("Mã hóa đơn: " + txtIDBill.getText() + "\r\n");
            bw.write("Thời gian: " + ft.format(now) + "\r\n");
            bw.write("NHÂN VIÊN: " + txtEmpName.getText() + "\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Mã\tTên\tKích thước\tSố lượng\tĐơn giá\tThành tiền\r\n");
            bw.write("-----------------------------------------------------------\r\n");
            //Ghi sản phẩm
            int line = tblBill.getRowCount();
            int quantotal = 0;
            for (int i = 0; i < line; i++) {
                String id = (String) tblBill.getValueAt(i, 0);
                String name = (String) tblBill.getValueAt(i, 1);
                String size = (String) tblBill.getValueAt(i, 4);
                String price = String.valueOf(tblBill.getValueAt(i, 3));
                String quantity = String.valueOf(tblBill.getValueAt(i, 5));
                String intomoney = String.valueOf(tblBill.getValueAt(i, 6));
                bw.write((i + 1) + ". " + name + "\r\n");
                bw.write(id + "\t" + size + "\t\t" + quantity + "\t\t" + price + "\t" + intomoney + "\r\n\r\n");
                quantotal += Integer.parseInt(quantity);
            }
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Tổng cộng:\t\t" + quantotal + "\t\t\t" + txtTotal.getText() + " VNĐ\r\n");
            bw.write("\t\tChiết khấu:\t" + txtDis1.getText() + "%\t\t-" + txtDis2.getText() + " VNĐ\r\n");
            bw.write("\t\t--------------------------------------------\r\n");
            bw.write("\t\tThành tiền:\t\t\t" + txtPay.getText() + " VNĐ\r\n");
            bw.write("\t\t--------------------------------------------\r\n");
            bw.write("\t\tTiền khách đưa:\t\t\t" + formatter.format(guest) + " VNĐ\r\n");
            bw.write("\t\tTiền trả lại:\t\t\t" + txtRepay.getText() + " VNĐ\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Chương trình khuyến mãi: ");
            if (cbCTKM.getSelectedItem().equals("Không có")) {
                bw.write("Không có.\r\n");
            } else if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
                bw.write("Thành viên quán.\r\n");
                bw.write("-----Thông tin thành viên-----\r\n");
                bw.write("Mã thẻ: " + lbIDCus.getText() + "\r\n");
                bw.write("Tên thành viên: " + lbNameCus.getText() + "\r\n");
                bw.write("Ngày đăng ký: " + lbDateCus.getText() + "\r\n");
                bw.write("Số lượng cũ: " + lbQuantityCus.getText() + " ly.\r\n");
                bw.write("Số ly mới mua: " + quantotal + " ly.\r\n");
                bw.write("Chiết khấu (tính theo số lượng cũ): " + lbDisCus.getText() + "\r\n");
            } else {
                bw.write((String) cbCTKM.getSelectedItem() + "\r\n");
            }
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Mật khẩu Wifi: motdentam\r\n");
            bw.write("---------------------CÁM ƠN QUÝ KHÁCH!----------------------");
            bw.close();
            Runtime run = Runtime.getRuntime();
            try {
                run.exec("notepad LichSuBanHang.txt");
            } catch (IOException e) {
            }
        } catch (IOException ex) {
            Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtIDBill.setEnabled(false);
        txtIDBill.setText("");
        cbCTKM.setSelectedIndex(0);
        txtPay.setText("0");
        txtTotal.setText("0");
        txtGuest.setText("0");
        txtGuest.setEnabled(false);
        txtRepay.setText("0");
        ResetPnInfor();
        txtID.setText("");
        lbIDError.setText("");
        lbloiMaHD.setText("");
        btnPrint.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) tblBill.getModel();
        model.setRowCount(0);
    }

    private void ResetPnInfor() {
        lbIDCus.setText("................................");
        lbNameCus.setText("................................");
        lbDateCus.setText("................................");
        lbQuantityCus.setText("................................");
        lbDisCus.setText("................................");
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> cbCTKM;
    private javax.swing.JComboBox<String> cbProduct;
    private javax.swing.JComboBox<String> cbSize;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCK;
    private javax.swing.JLabel lbDateCus;
    private javax.swing.JLabel lbDisCus;
    private javax.swing.JLabel lbIDCus;
    private javax.swing.JLabel lbIDError;
    private javax.swing.JLabel lbLoiGia;
    private javax.swing.JLabel lbNameCus;
    private javax.swing.JLabel lbQuantityCus;
    private javax.swing.JLabel lbTC;
    private javax.swing.JLabel lbTT;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbloiMaHD;
    private javax.swing.JLabel lbmathe;
    private javax.swing.JLabel lbnen;
    private javax.swing.JLabel lbphantram;
    private javax.swing.JLabel lbthanhtien;
    private javax.swing.JLabel lbtt;
    private javax.swing.JMenuItem miInformation;
    private javax.swing.JMenuItem miLogout;
    private javax.swing.JMenuItem miPassChange;
    private java.awt.Panel panel1;
    private javax.swing.JPanel panelTTKh;
    private javax.swing.JPanel panelThanhToan;
    private javax.swing.JSpinner spQuantity;
    private javax.swing.JTable tblBill;
    private javax.swing.JTextField txtDis1;
    private javax.swing.JTextField txtDis2;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtGuest;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDBill;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtRepay;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
