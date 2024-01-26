package view.admin;

import controller.JadwalPraktekController;
import controller.PoliController;
import node.JadwalPraktek;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static assets.Assets.colorButton;

public class ManageJadwalPraktekView extends JFrame {

    private JTextField filterNamaDokterField;
    private JComboBox<String> filterHariComboBox;
    private JTextField filterJamPraktekField;

    private DefaultTableModel jadwalTableModel;
    private JTable jadwalTable;
    private final PoliController poliController;
    private final JadwalPraktekController jadwalPraktekController;
    private final String[] daftarHari = {"Pilih", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};

    public ManageJadwalPraktekView() {
        poliController = new PoliController();
        jadwalPraktekController = new JadwalPraktekController();
        setTitle("Manage Jadwal Praktek Dokter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 200);
        setLayout(new BorderLayout());
        initialize();
        filter();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void filter(){
        filterNamaDokterField = new JTextField();
        filterHariComboBox = new JComboBox<>(daftarHari);
        filterJamPraktekField = new JTextField();
        JButton filterButton = new JButton("Filter");

        filterNamaDokterField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });

        filterHariComboBox.addActionListener(e -> filterTable());

        filterJamPraktekField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });

        filterButton.addActionListener(e -> filterTable());

        int textFieldWidth = 150;
        int textFieldHeight = 25;

        filterNamaDokterField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        filterHariComboBox.setPreferredSize(new Dimension(100, textFieldHeight));
        filterJamPraktekField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        filterPanel.add(new JLabel("Nama Dokter:"));
        filterPanel.add(filterNamaDokterField);

        filterPanel.add(new JLabel("Hari:"));
        filterPanel.add(filterHariComboBox);

        filterPanel.add(new JLabel("Jam Praktek:"));
        filterPanel.add(filterJamPraktekField);

        add(filterPanel, BorderLayout.NORTH);
    }

    private void filterTable() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(jadwalTableModel);
        jadwalTable.setRowSorter(sorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        String filterNamaDokter = filterNamaDokterField.getText();
        if (!filterNamaDokter.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + filterNamaDokter, 0));
        }

        String filterHari = (String) filterHariComboBox.getSelectedItem();
        if (!"Pilih".equals(filterHari)) {
            filters.add(RowFilter.regexFilter("(?i)" + filterHari, 1));
        }

        String filterJamPraktek = filterJamPraktekField.getText();
        if (!filterJamPraktek.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + filterJamPraktek, 3));
        }

        if (filters.size() > 1) {
            RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);
            sorter.setRowFilter(compoundRowFilter);
        } else if (filters.size() == 1) {
            sorter.setRowFilter(filters.getFirst());
        } else {
            sorter.setRowFilter(null);
        }
    }


    private void initialize() {
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

            jadwalTableModel = new DefaultTableModel(data, columnNames);
            jadwalTable = new JTable(jadwalTableModel);
            JScrollPane scrollPane = new JScrollPane(jadwalTable);
            add(scrollPane, BorderLayout.CENTER);
        } else {
            jadwalTableModel = new DefaultTableModel(new Object[0][0], columnNames);
            jadwalTable = new JTable(jadwalTableModel);

            JLabel jadwalKosongLabel = new JLabel("Jadwal Praktek Kosong");
            jadwalKosongLabel.setHorizontalAlignment(JLabel.CENTER);
            add(jadwalKosongLabel, BorderLayout.CENTER);
        }

        JButton addButton = new JButton("Tambah Jadwal");
        addButton.setFocusPainted(false);
        addButton.setBackground(colorButton);
        addButton.addActionListener(e -> tambahJadwal());

        JButton updateButton = new JButton("Update Jadwal");
        updateButton.setFocusPainted(false);
        updateButton.setBackground(colorButton);
        updateButton.addActionListener(e -> updateJadwal());

        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setBackground(colorButton);
        kembaliButton.addActionListener(e -> {
            dispose();
            new HomeAdmin().setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(kembaliButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void tambahJadwal() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField namaDokterField = new JTextField();
        String[] daftarPoli = poliController.getAll().stream().map(poli -> poli.namaPoli).toArray(String[]::new);
        JComboBox<String> hariComboBox = new JComboBox<>(daftarHari);
        JComboBox<String> poliComboBox = new JComboBox<>(daftarPoli);
        JTextField jamPraktekField = new JTextField();

        panel.add(new JLabel("Nama Dokter:"));
        panel.add(namaDokterField);
        panel.add(new JLabel("Hari:"));
        panel.add(hariComboBox);
        panel.add(new JLabel("Poli"));
        panel.add(poliComboBox);
        panel.add(new JLabel("Jam Praktek:"));
        panel.add(jamPraktekField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Tambah Jadwal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String namaDokter = namaDokterField.getText();
            String hari = (String) hariComboBox.getSelectedItem();
            String jamPraktek = jamPraktekField.getText();
            String poli = (String) poliComboBox.getSelectedItem();

            if (isDataValid(namaDokter, hari, jamPraktek)) {
                jadwalPraktekController.tambahJadwalPraktek(namaDokter, hari, jamPraktek, poli);
                Object[] rowData = {namaDokter, hari, poli, jamPraktek};
                jadwalTableModel.addRow(rowData);
                jadwalTableModel.fireTableDataChanged();
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan lengkapi semua data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateJadwal() {
        int selectedRow = jadwalTable.getSelectedRow();
        if (selectedRow != -1) {
            JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
            JTextField namaDokterField = new JTextField(jadwalTableModel.getValueAt(selectedRow, 0).toString());
            JComboBox<String> hariComboBox = new JComboBox<>(daftarHari);
            hariComboBox.setSelectedItem(jadwalTableModel.getValueAt(selectedRow, 1).toString());
            JTextField jamPraktekField = new JTextField(jadwalTableModel.getValueAt(selectedRow, 3).toString());

            String[] daftarPoli = poliController.getAll().stream().map(poli -> poli.namaPoli).toArray(String[]::new);
            JComboBox<String> poliComboBox = new JComboBox<>(daftarPoli);
            poliComboBox.setSelectedItem(jadwalTableModel.getValueAt(selectedRow, 2).toString());

            panel.add(new JLabel("Nama Dokter:"));
            panel.add(namaDokterField);
            panel.add(new JLabel("Hari:"));
            panel.add(hariComboBox);
            panel.add(new JLabel("Poli:"));
            panel.add(poliComboBox);
            panel.add(new JLabel("Jam Praktek:"));
            panel.add(jamPraktekField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Update Jadwal", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String namaDokter = namaDokterField.getText();
                    String hari = (String) hariComboBox.getSelectedItem();
                    String jamPraktek = jamPraktekField.getText();
                    String poli = (String) poliComboBox.getSelectedItem();

                    if (isDataValid(namaDokter, hari, jamPraktek)) {
                        jadwalPraktekController.updateJadwalPraktek(selectedRow, namaDokter, hari, jamPraktek, poli);
                        jadwalTableModel.setValueAt(namaDokter, selectedRow, 0);
                        jadwalTableModel.setValueAt(hari, selectedRow, 1);
                        jadwalTableModel.setValueAt(poli, selectedRow, 2);
                        jadwalTableModel.setValueAt(jamPraktek, selectedRow, 3);
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak boleh ada yang kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (HeadlessException e) {
                    e.getMessage();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris pada tabel untuk melakukan pembaruan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean isDataValid(String namaDokter, String hari, String jamPraktek) {
        return !namaDokter.isEmpty() && !hari.equals("Pilih") && !jamPraktek.isEmpty();
    }
}
