package view.pasien;

import controller.PoliController;
import entity.Antrian;
import entity.Pasien;
import entity.Poli;
import view.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static assets.Assets.colorButton;

public class SelectPoliView extends Frame {
    JLabel titleLabel;
    Pasien pasien;
    JButton btnKembali;
    PoliController poliController;
    public SelectPoliView(Pasien pasien) {
        super("Pilih Poli", 350, 400);
        this.pasien = pasien;
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
            button.setBackground(colorButton);
            poliPanel.add(button);
            button.addActionListener(e -> {
                    String selectedPoli = button.getText();
                    if (poliController.addAtnrianPoli(selectedPoli, pasien)) {
                        Antrian antrian = poliController.getAntrian(selectedPoli, pasien.NIK);
                        String detail = "<html><center>" +
                                "<b>Detail Antrian</b><br>" +
                                "no antrian " + antrian.index +
                                "<br>di Poli " + selectedPoli +
                                "<br>Terima Kasih :)" +
                                "</center></html>";

                        JOptionPane.showMessageDialog(null, detail, "detail antrian", JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                        new HomePasien().setVisible(true);
                    } else {
                        Antrian antrian = poliController.getAntrian(selectedPoli, pasien.NIK);
                        String message = "<html><center>Anda telah mendapatkan" +
                                "<br>no antrian " + antrian.index + " di Poli " + selectedPoli +
                                "<br>Silahkan ke poli " + selectedPoli +
                                "<br>dan menunggu giliran anda" + "<br>TERIMAKASIH :)</center></html>";
                        JOptionPane.showMessageDialog(null, message, "informasi", JOptionPane.INFORMATION_MESSAGE );
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
                new HomePasien().setVisible(true);
        });
    }
}
