package view.admin;
import controller.PoliController;
import node.Poli;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoliManagementView extends JFrame {
    private PoliController poliController;
    private DefaultTableModel tableModel;
    private JList<String> poliList;

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
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahPoli();
            }
        });

        JButton kembaliButton = new JButton("Kembali");
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
        String newPoli = JOptionPane.showInputDialog(this, "Enter new poli:");
        if (newPoli != null && !newPoli.trim().isEmpty()) {
            poliController.tambahPoli(newPoli);
            JOptionPane.showMessageDialog(null, newPoli + " has been added.");
            tableModel.addRow(new Object[]{newPoli});
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid poli name.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PoliManagementView());
    }
}
