package view.admin;
import controller.PoliController;
import node.Poli;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static assets.Assets.colorButton;

public class PoliManagementView extends JFrame {
    private final PoliController poliController;
    private DefaultTableModel tableModel;

    public PoliManagementView() {
        poliController = new PoliController();
        setTitle("Poli Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(280, 300);
        component();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void component() {
        tableModel =new DefaultTableModel();
        tableModel.addColumn("List Poli");

        for (Poli poli : poliController.getAll()) {
            tableModel.addRow(new Object[]{poli.namaPoli});
        }

        JTable poliTable = new JTable(tableModel);
        poliTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        poliTable.setDefaultRenderer(Object.class, centerRenderer);

        JButton addButton = new JButton("Tambah Poli");
        addButton.setBackground(colorButton);
        addButton.addActionListener(e-> tambahPoli());

        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setBackground(colorButton);
        kembaliButton.addActionListener(e -> {
            dispose();
            new HomeAdmin().setVisible(true);
        });

        JPanel panelButton = new JPanel();
        panelButton.add(kembaliButton);
        panelButton.add(addButton);

        JScrollPane scrollPane = new JScrollPane(poliTable);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane);

        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
    }

    private void tambahPoli() {
        String newPoli = JOptionPane.showInputDialog(this, "Masukan Poli Baru:");
        if (newPoli != null && !newPoli.trim().isEmpty()) {
            poliController.tambahPoli(newPoli.toUpperCase());
            JOptionPane.showMessageDialog(null, newPoli.toUpperCase() + " telah ditambahkan.");
            tableModel.addRow(new Object[]{newPoli});
        } else {
            JOptionPane.showMessageDialog(this, "nama polinya di isilah.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PoliManagementView::new);
    }
}
