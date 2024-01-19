package view.pasien;

import com.toedter.calendar.JDateChooser;
import controller.PasienController;
import view.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static assets.Assets.colorButton;

public class FormDataPasienView extends Frame {
    PasienController pasienController;
    JButton btnKembali, btnSimpan;
    private JTextField tfNama, tfAlamat, tfNIK, tfNoBPJS, tfTempatLahir;
    private JDateChooser dateChooser;
    private JComboBox<String> cbKelamin;

    public FormDataPasienView() {
        super("Form Data Pasien", 600, 540);
        pasienController = new PasienController();
    }

    public void component() {
        JLabel header = new JLabel("Silahkan mengisi Form");
        header.setBounds(72, 26, 400, 44);
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        add(header);

        Font fontLabel = new Font("Comic Sans MS", Font.BOLD, 20);

        JLabel labelNama = new JLabel("Nama");
        labelNama.setBounds(72, 87, 55, 24);
        labelNama.setFont(fontLabel);
        add(labelNama);

        tfNama = new JTextField();
        tfNama.setBounds(323, 87, 230, 30);
        add(tfNama);

        JLabel labelAlamat = new JLabel("Alamat");
        labelAlamat.setFont(fontLabel);
        labelAlamat.setBounds(72, 127, 80, 24);
        add(labelAlamat);

        tfAlamat = new JTextField();
        tfAlamat.setBounds(323, 127, 230, 30);
        add(tfAlamat);

        JLabel labelKelamin = new JLabel("Kelamin");
        labelKelamin.setFont(fontLabel);
        labelKelamin.setBounds(72, 167, 80, 24);
        add(labelKelamin);

        String[] jenisKelamin = {"Laki-laki", "Perempuan"};
        cbKelamin = new JComboBox<>(jenisKelamin);
        cbKelamin.setBounds(323, 167, 230, 30);
        add(cbKelamin);

        JLabel labelTempatLahir = new JLabel("Tempat Lahir");
        labelTempatLahir.setFont(fontLabel);
        labelTempatLahir.setBounds(72, 207, 150, 24);
        add(labelTempatLahir);

        tfTempatLahir = new JTextField();
        tfTempatLahir.setBounds(323, 207, 230, 30);
        add(tfTempatLahir);

        JLabel labelTTL = new JLabel("Tanggal Lahir");
        labelTTL.setFont(fontLabel);
        labelTTL.setBounds(72, 247, 200, 24);
        add(labelTTL);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(323, 247, 230, 30);
        add(dateChooser);

        JLabel labelNIK = new JLabel("NIK");
        labelNIK.setFont(fontLabel);
        labelNIK.setBounds(72, 287, 80, 24);
        add(labelNIK);

        tfNIK = new JTextField();
        tfNIK.setBounds(323, 287, 230, 30);
        add(tfNIK);

        JLabel labelNoBPJS = new JLabel("Nomor BPJS");
        labelNoBPJS.setFont(fontLabel);
        labelNoBPJS.setBounds(72, 327, 200, 24);
        add(labelNoBPJS);

        tfNoBPJS = new JTextField();
        tfNoBPJS.setBounds(323, 327, 230, 30);
        add(tfNoBPJS);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(200, 367, 100, 30);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBackground(colorButton);
        add(btnSimpan);

        btnKembali = new JButton("Kembali");
        btnKembali.setBounds(80, 367, 100, 30);
        btnKembali.setFocusPainted(false);
        btnKembali.setBackground(colorButton);
        add(btnKembali);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();

                ImageIcon logoIcon = new ImageIcon("src/assets/logo-upt-puskesmas.png");
                Image logoImage = logoIcon.getImage();
                g2d.drawImage(logoImage, 130, 40, 300, 380, this);

                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255, 220),
                        0, height, new Color(255, 255, 255, 220));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);

                g2d.dispose();
            }
        };

        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 600, 480);
        add(backgroundPanel);
    }

    public void event(){
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanDataPasien();
                dispose();
                new HomePasien().setVisible(true);
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePasien().setVisible(true);
            }
        });
    }

    private void simpanDataPasien() {
        String nama = tfNama.getText();
        String alamat = tfAlamat.getText();
        String kelamin = (String) cbKelamin.getSelectedItem();

        if (nama.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi semua form (kecuali No BPJS) sebelum menyimpan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            Date tanggalLahir = dateChooser.getDate();
            if (tanggalLahir == null)
                JOptionPane.showMessageDialog(this, "Format tanggal tidak sesuai, contoh 15 Jun 2000", "Peringatan", JOptionPane.WARNING_MESSAGE);
            else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String ttl = tfTempatLahir.getText() + ", " + dateFormat.format(tanggalLahir);

                String nik = tfNIK.getText();
                String noBPJS = tfNoBPJS.getText();
                if (nik.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Harap lengkapi semua form (kecuali No BPJS) sebelum menyimpan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    String hasil = "Data Pasien:\n" +
                            "Nama: " + nama + "\n" +
                            "Alamat: " + alamat + "\n" +
                            "Kelamin: " + kelamin + "\n" +
                            "TTL: " + ttl + "\n" +
                            "NIK: " + nik + "\n" +
                            "No BPJS: " + noBPJS;

                    int result = JOptionPane.showConfirmDialog(null, hasil + "\nApakah data anda sudah sesuai?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        PasienController pasienController = new PasienController();
                        pasienController.tambahPasien(nama, alamat, kelamin, ttl, nik, noBPJS);
                        JOptionPane.showMessageDialog(this, hasil, "Data Pasien", JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                        new HomePasien().setVisible(true);
                    } else {
                        dispose();
                        new FormDataPasienView().setVisible(true);
                    }
                }
            }
        }
    }
}