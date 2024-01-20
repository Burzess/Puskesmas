package view.admin;
import controller.JadwalPraktekController;
import node.JadwalPraktek;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageJadwalPraktekView extends JFrame {
    private DefaultTableModel jadwalTableModel;
    private JTable jadwalTable;

    private JadwalPraktekController jadwalPraktekController;
    private String[] daftarHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};

    public ManageJadwalPraktekView() {
        jadwalPraktekController = new JadwalPraktekController();
        setTitle("Manage Jadwal Praktek Dokter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());
        initialize();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initialize() {
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

        JButton addButton = new JButton("Tambah Jadwal");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahJadwal();
            }
        });

        JButton updateButton = new JButton("Update Jadwal");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateJadwal();
            }
        });

        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.addActionListener( e -> {
            dispose();
            new HomdeAdmin().setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(kembaliButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);

        JScrollPane scrollPane = new JScrollPane(jadwalTable);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void tambahJadwal() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField namaDokterField = new JTextField();
        JComboBox<String> hariComboBox = new JComboBox<>(daftarHari);
        JTextField jamPraktekField = new JTextField();

        panel.add(new JLabel("Nama Dokter:"));
        panel.add(namaDokterField);
        panel.add(new JLabel("Hari:"));
        panel.add(hariComboBox);
        panel.add(new JLabel("Jam Praktek:"));
        panel.add(jamPraktekField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Tambah Jadwal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String namaDokter = namaDokterField.getText();
            String hari = hariComboBox.getSelectedItem().toString();
            String jamPraktek = jamPraktekField.getText();

            if (namaDokter.isEmpty() || hari.isEmpty() || jamPraktek.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                jadwalPraktekController.tambahJadwalPraktek(namaDokter,hari,jamPraktek);
                Object[] rowData = {namaDokter, hari, jamPraktek};
                jadwalTableModel.addRow(rowData);
            }
        }
    }

    private void updateJadwal() {
        int selectedRow = jadwalTable.getSelectedRow();
        if (selectedRow != -1) {
            JPanel panel = new JPanel(new GridLayout(3, 2));
            JTextField namaDokterField = new JTextField(jadwalTableModel.getValueAt(selectedRow, 0).toString());
            JComboBox<String> hariComboBox = new JComboBox<>(daftarHari);
            hariComboBox.setSelectedItem(jadwalTableModel.getValueAt(selectedRow, 1).toString());
            JTextField jamPraktekField = new JTextField(jadwalTableModel.getValueAt(selectedRow, 2).toString());

            panel.add(new JLabel("Nama Dokter:"));
            panel.add(namaDokterField);
            panel.add(new JLabel("Hari:"));
            panel.add(hariComboBox);
            panel.add(new JLabel("Jam Praktek:"));
            panel.add(jamPraktekField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Update Jadwal", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String namaDokter = namaDokterField.getText();
                String hari = hariComboBox.getSelectedItem().toString();
                String jamPraktek = jamPraktekField.getText();

                if (namaDokter.isEmpty() || hari.isEmpty() || jamPraktek.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    jadwalPraktekController.updateJadwalPraktek(selectedRow, namaDokter, hari , jamPraktek);
                    jadwalTableModel.setValueAt(namaDokter, selectedRow, 0);
                    jadwalTableModel.setValueAt(hari, selectedRow, 1);
                    jadwalTableModel.setValueAt(jamPraktek, selectedRow, 2);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris pada tabel untuk melakukan pembaruan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
//
//    public static void main(String[] args) {
//        JadwalPraktekModel.init();
//        SwingUtilities.invokeLater(() -> new ManageJadwalPraktekView());
//    }
}