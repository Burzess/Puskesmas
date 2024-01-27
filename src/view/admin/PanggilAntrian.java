package view.admin;

import controller.PoliController;
import controller.TransaksiController;
import entity.Antrian;
import entity.Poli;
import view.Frame;
import view.pasien.HomePasien;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static assets.Assets.colorButton;

public class PanggilAntrian extends Frame {
    JLabel titleLabel;
    JButton btnKembali;
    PoliController poliController;
    TransaksiController transaksiController;
    public PanggilAntrian() {
        super("Pilih Poli", 350, 400);
        poliController = new PoliController();
        transaksiController = new TransaksiController();
        setLayout(new BorderLayout());
    }

    private JPanel createPoliPanel() {
        ArrayList<String> daftarPoli = new ArrayList<>();

        for (Poli poli : poliController.getAll()) {
            daftarPoli.add(poli.namaPoli);
        }

        JPanel poliPanel = new JPanel();
        poliPanel.setLayout(new GridLayout(daftarPoli.size(), 1, 10, 10));

        JButton[] poliButtons = new JButton[daftarPoli.size()];
        for (int i = 0; i < daftarPoli.size(); i++) {
            poliButtons[i] = new JButton(daftarPoli.get(i));
            poliButtons[i].setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        }

        for (JButton button : poliButtons) {
            button.setMargin(new Insets(10, 10, 10, 10));
            button.setFocusPainted(false);
            button.setBackground(colorButton);
            poliPanel.add(button);
            button.addActionListener(e -> {
                    String selectedPoli = button.getText();
                    Antrian nextAntrian = poliController.getNextAntrian(selectedPoli);
                    if (nextAntrian != null) {
                        String massage = "PANGGILAN UNTUK NO ANTRIAN " + nextAntrian.index + " DI TUNGGUN DI POLI " +  nextAntrian.poli.toUpperCase();
                        HomePasien.setInformation(massage);
                        JOptionPane.showMessageDialog(null, massage, "Detail Antrian", JOptionPane.INFORMATION_MESSAGE);
                        transaksiController.addTransaksi(nextAntrian);
                    } else {
                        JOptionPane.showMessageDialog(null, "Antrian poli " + selectedPoli + " sudah habis", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    }
            });

        }

        return poliPanel;
    }

    @Override
    protected void component() {

        titleLabel = new JLabel("Pilih Poli");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(createPoliPanel());
        add(scrollPane, BorderLayout.CENTER);

        btnKembali = new JButton("Kembali");
        btnKembali.setFocusPainted(false);
        btnKembali.setMargin(new Insets(10, 30, 30, 30));

        add(btnKembali, BorderLayout.SOUTH);
    }

    @Override
    protected void event() {
        btnKembali.addActionListener(e -> {
                dispose();
                new HomeAdmin().setVisible(true);
        });
    }
}
