package view.pasien;

import view.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assets.Assets.*;

public class HomePasien extends Frame {
    JLabel header;
    JButton bt1, bt2, bt3;
    JPanel backgroundPanel;

    public HomePasien() {
        super("Puskesmas", 300, 400);
    }

    protected void component() {
        header = new JLabel("<html><center>PUSKESMAS</center><center>HAHA HIHI</center></html>");
        header.setBounds(71, 50, 200, 96);
        header.setFont(fontHeader);
        add(header);

        bt1 = new JButton("Pasien Baru");
        bt1.setFont(fontButton);
        bt1.setBounds(80, 220, 130, 28);
        bt1.setFocusPainted(false);
        bt1.setBackground(colorButton);
        bt1.setForeground(Color.BLACK);
        add(bt1);

        bt2 = new JButton("Ambil Antrian");
        bt2.setFont(fontButton);
        bt2.setBounds(80, 260, 130, 28);
        bt2.setFocusPainted(false);
        bt2.setBackground(colorButton);
        bt2.setForeground(Color.BLACK);
        add(bt2);

        bt3 = new JButton("<html><center>Lihat Jadwal</center><center>Praktek Dokter</center></html>");
        bt3.setFont(fontButton);
        bt3.setBounds(80, 300, 130, 36);
        bt3.setFocusPainted(false);
        bt3.setBackground(colorButton);
        bt3.setForeground(Color.BLACK);
        add(bt3);

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();

                ImageIcon logoIcon = new ImageIcon("src/assets/logo-upt-puskesmas.png");
                Image logoImage = logoIcon.getImage();
                g2d.drawImage(logoImage, 70, 20, 150, 215, this);

                GradientPaint gradient = new GradientPaint(0, 0, new Color(3, 255, 255, 220),
                        0, height, new Color(3, 255, 255, 220));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);

                g2d.dispose();
            }
        };

        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 300, 430);
        add(backgroundPanel);
    }

    public void event(){
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pasien baru");
                dispose();
                new FormDataPasienView().setVisible(true);
            }
        });

        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ambil antrian");
                dispose();
                new DaftarAntrian().setVisible(true);
            }
        });

        bt3.addActionListener( e -> {
//            dispose();
//            new JadwalPraktekView().setVisible(true);
        });
    }


//
//    public static void main(String[] args) {
////        SwingUtilities.invokeLater(() -> new HomePasien().setVisible(true));
//        new HomePasien().setVisible(true);
//    }
}
