package view.pasien;

import controller.PasienController;
import node.Pasien;
import view.Frame;

import javax.swing.*;
import java.awt.*;

import static assets.Assets.fontButton;

public class DaftarAntrian extends Frame {
    JLabel header, nikLabel;
    JTextField nikField;
    JButton tombolDaftarAntrian, btnKembali;
    PasienController pasienController;

    public DaftarAntrian() {
        super("Daftar Antrian", 600, 200);
        pasienController = new PasienController();
    }

    @Override
    protected void component() {
        header = new JLabel("Pendaftaran");
        header.setBounds(33, 32, 200, 30);
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        add(header);

        nikLabel = new JLabel("NIK/No BPJS");
        nikLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        nikLabel.setBounds(33, 62, 200, 30);
        add(nikLabel);

        nikField = new JTextField();
        nikField.setBounds(250, 63, 200, 24);
        add(nikField);

        tombolDaftarAntrian = new JButton("Daftar Antrian");
        tombolDaftarAntrian.setBounds(170, 100, 130, 24);
        tombolDaftarAntrian.setFont(fontButton);
        tombolDaftarAntrian.setBackground(new Color(135, 206, 250));
        tombolDaftarAntrian.setFocusPainted(false);
        add(tombolDaftarAntrian);

        btnKembali = new JButton("Kembali");
        btnKembali.setBounds(35, 100, 100, 24);
        btnKembali.setFont(fontButton);
        btnKembali.setBackground(new Color(135, 206, 250));
        btnKembali.setFocusPainted(false);
        add(btnKembali);
    }

    @Override
    protected void event() {
        btnKembali.addActionListener(e ->{
                dispose();
                new HomePasien().setVisible(true);
        });

        tombolDaftarAntrian.addActionListener(e -> {
                if (nikField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "NIK Tidak Boleh Kosong");
                } else {
                    Pasien pasien1 = pasienController.getPasien(nikField.getText());
                    if (pasien1 == null) {
                        JOptionPane.showMessageDialog(null, "NIK Tidak/No BPJS Tidak Terdaftar :V");
                    } else {
                        dispose();
                        new SelectPoliView(pasien1).setVisible(true);
                    }
                }
        });
    }
}
