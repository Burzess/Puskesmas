package view.pasien;
import controller.JadwalPraktekController;
import entity.JadwalPraktek;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static assets.Assets.colorButton;

public class JadwalPraktekView extends JFrame {

    public JadwalPraktekView() {
        setTitle("Jadwal Praktek Dokter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());
        initialize();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initialize() {
        JadwalPraktekController jadwalPraktekController = new JadwalPraktekController();
        String[] columnNames = {"Nama Dokter", "Hari", "Poli", "Jam Praktek"};
        List<JadwalPraktek> jadwalList = jadwalPraktekController.getAllJadwalPraktek();

        if (jadwalList != null && !jadwalList.isEmpty()) {
            Object[][] data = new Object[jadwalList.size()][4];
            for (int i = 0; i < jadwalList.size(); i++) {
                JadwalPraktek jadwal = jadwalList.get(i);
                data[i][0] = jadwal.getNamaDokter();
                data[i][1] = jadwal.getHari();
                data[i][2] = jadwal.getPoli();
                data[i][3] = jadwal.getJamPraktek();
            }

            DefaultTableModel jadwalTableModel = new DefaultTableModel(data, columnNames);
            JTable jadwalTable = new JTable(jadwalTableModel);
            JScrollPane scrollPane = new JScrollPane(jadwalTable);
            add(scrollPane, BorderLayout.CENTER);
        } else {
            JLabel jadwalKosongLabel = new JLabel("Jadwal Praktek Kosong");
            jadwalKosongLabel.setHorizontalAlignment(JLabel.CENTER);
            add(jadwalKosongLabel, BorderLayout.CENTER);
        }

        JButton kembali = new JButton("Kembali");
        kembali.setBackground(colorButton);
        kembali.addActionListener(e ->{
                dispose();
                new HomePasien().setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(kembali);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}