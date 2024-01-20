package view.admin;

import controller.PoliController;
import node.Antrian;
import node.Pasien;
import node.Poli;
import view.Frame;
import view.pasien.HomePasien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanggilAntrian extends Frame {
    JLabel titleLabel;
    JButton btnKembali;
    PoliController poliController;
    public PanggilAntrian() {
        super("Pilih Poli", 350, 400);
        poliController = new PoliController();
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
            button.setBackground(new Color(135, 206, 250));
            poliPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedPoli = button.getText();
                    Antrian nextAntrian = poliController.getNextAntrian(selectedPoli);
                    if (nextAntrian != null) {
                        String massage = "PANGGILAN UNTUK NO ANTRIAN " + nextAntrian.index + " DI TUNGGUN DI POLI " +  nextAntrian.poli.toUpperCase();
                        JOptionPane.showMessageDialog(null, massage, "Detail Antrian", JOptionPane.INFORMATION_MESSAGE);
                        HomePasien.setInformation(massage);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak ada antrian tersisa", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    }
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
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomdeAdmin().setVisible(true);
            }
        });
    }
}
