/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dell Vostro
 */
public class setImage {

    public void setImageButton(JButton button, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));//đọc ảnh dùng BufferedImage

            int w = button.getSize().width;
            int h = button.getSize().height;
            int iw = image.getWidth();
            int ih = image.getHeight();
            int dw = 0, dh = 0;

            if (w / h > iw / ih) {
                dw = w;
                dh = dh * iw / ih;
            } else {
                dw = w;
                dh = dw * ih / iw;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dw, dh,
                    Image.SCALE_SMOOTH));
            button.setIcon(icon);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    
    public void setImagelable(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));//đọc ảnh dùng BufferedImage

            int w = label.getSize().width;
            int h = label.getSize().height;
            int iw = image.getWidth();
            int ih = image.getHeight();
            int dw = 0, dh = 0;

            if (w / h > iw / ih) {
                dh = h;
                dw = dh * iw / ih;
            } else {
                dw = w;
                dh = dw * ih / iw;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dw, dh,
                    Image.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    
}
