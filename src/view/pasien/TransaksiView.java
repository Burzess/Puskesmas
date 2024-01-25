package view.pasien;

import controller.PoliController;
import controller.TransaksiController;
import node.Antrian;
import view.Frame;

import javax.swing.*;
import java.awt.*;

public class TransaksiView extends Frame {
    TransaksiController transaksiController;
    PoliController poliController;
    JTextField nikField, nominalField, deskripsiField;
    JButton submitBt;

    public TransaksiView() {
        super("Transaksi", 400, 300);
        setLayout(new BorderLayout());
        transaksiController = new TransaksiController();
        poliController = new PoliController();
    }

    @Override
    protected void component() {
        System.out.println("RUN");
        nikField = new JTextField();
        nominalField = new JTextField();
        deskripsiField = new JTextField();
        submitBt = new JButton("Submit");

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("NIK:"));
        panel.add(nikField);
        panel.add(new JLabel("Nominal:"));
        panel.add(nominalField);
        panel.add(new JLabel("Deskripsi Pembayaran:"));
        panel.add(deskripsiField);
        panel.add(new JLabel());
        panel.add(submitBt);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    protected void event() {
        submitBt.addActionListener(e -> submitTransaksi());
    }

    private void submitTransaksi() {
        String nik = nikField.getText();
        int nominal = Integer.parseInt(nominalField.getText());
        String deskripsi = deskripsiField.getText();
        String poli = "Umum";

        if (isValidInput(nik, nominal, deskripsi)) {
            Antrian antrian = poliController.getNextAntrian(poli);
            transaksiController.addTransaksi(antrian);
            JOptionPane.showMessageDialog(this, "Transaksi berhasil!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Input tidak valid. Pastikan semua field terisi dengan benar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidInput(String nik, int nominal, String deskripsi) {
        return !nik.isEmpty() && nominal > 0 && !deskripsi.isEmpty();
    }

    private void clearFields() {
        nikField.setText("");
        nominalField.setText("");
        deskripsiField.setText("");
    }

    public static void main(String[] args) {
        new TransaksiView().setVisible(true);
    }
}
