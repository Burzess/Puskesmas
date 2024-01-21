package view.admin;

import view.Frame;

import javax.swing.*;
import java.awt.*;

import static assets.Assets.fontButton;
import static assets.Assets.fontHeader;

public class HomeAdmin extends Frame {
    JButton bt1, bt2, bt3, bt4;
    JLabel header;
    JPanel backgroundPanel;

    public HomeAdmin() {
        super("View Admin", 400,500);
    }

    @Override
    protected void component() {
        header = new JLabel("<html><center>PUSKESMAS</center><center>HAHA HIHI</center</html>");
        header.setBounds(121, 50, 220, 96);
        header.setFont(fontHeader);
        add(header);

        bt1 = new JButton("Tambah List Poli");
        bt1.setFont(fontButton);
        bt1.setBounds(80, 220, 230, 24);
        bt1.setFocusPainted(false);
        bt1.setBackground(new Color(135, 206, 250));
        bt1.setForeground(Color.WHITE);
        add(bt1);

        bt2 = new JButton("Manage Jadwal Praktek Dokter");
        bt2.setFont(fontButton);
        bt2.setBounds(80, 260, 230, 24);
        bt2.setFocusPainted(false);
        bt2.setBackground(new Color(135, 206, 250));
        bt2.setForeground(Color.WHITE);
        add(bt2);

        bt3 = new JButton("Cetak Detail Transaksi");
        bt3.setFont(fontButton);
        bt3.setBounds(80, 300, 230, 24);
        bt3.setFocusPainted(false);
        bt3.setBackground(new Color(135, 206, 250));
        bt3.setForeground(Color.WHITE);
        add(bt3);

        bt4 = new JButton("Panggil Antrian");
        bt4.setFont(fontButton);
        bt4.setBounds(80, 340, 230, 24);
        bt4.setFocusPainted(false);
        bt4.setBackground(new Color(135, 206, 250));
        bt4.setForeground(Color.WHITE);
        add(bt4);

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();

                ImageIcon logoIcon = new ImageIcon("src/assets/logo-upt-puskesmas.png");
                Image logoImage = logoIcon.getImage();
                g2d.drawImage(logoImage, 120, 20, 150, 215, this);

                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255, 220),
                        0, height, new Color(255, 255, 255, 220));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);

                g2d.dispose();
            }
        };

        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 400, 500);
        add(backgroundPanel);
    }

    @Override
    protected void event() {

        bt1.addActionListener(e -> {
            dispose();
            new PoliManagementView();
        });

        bt2.addActionListener(e -> {
            dispose();
            new ManageJadwalPraktekView();
        });

        bt3.addActionListener(e -> {
            dispose();
            new CetakTransaksiView();
        });

        bt4.addActionListener(e -> {
            dispose();
            new PanggilAntrian().setVisible(true);});
    }

//    public static void main(String[] args) {
//        new HomeAdmin().setVisible(true);
//    }
}
