package view.pasien;
import controller.JadwalPraktekController;
import node.JadwalPraktek;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JadwalPraktekView extends JFrame {
    private DefaultTableModel jadwalTableModel;
    private JTable jadwalTable;

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
        String[] columnNames = {"Nama Dokter", "Hari", "Jam Praktek"};
        List<JadwalPraktek> jadwalList = jadwalPraktekController.getAllJadwalPraktek();
        Object[][] data = new Object[jadwalList.size()][3];
        for (int i = 0; i < jadwalList.size(); i++) {
            JadwalPraktek jadwal = jadwalList.get(i);
            data[i][0] = jadwal.getNamaDokter();
            data[i][1] = jadwal.getHari();
            data[i][2] = jadwal.getJamPraktek();
        }

        jadwalTableModel = new DefaultTableModel(data, columnNames);
        jadwalTable = new JTable(jadwalTableModel);

        JButton kembali = new JButton("Kembali");
        kembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePasien().setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(kembali);

        JScrollPane scrollPane = new JScrollPane(jadwalTable);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}