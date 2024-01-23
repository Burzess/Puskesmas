package view.admin;
import controller.PoliController;
import node.Poli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoliManagementView extends JFrame {
    private PoliController poliController;
    private DefaultListModel<String> poliListModel;
    private JList<String> poliList;

    public PoliManagementView() {
        poliController = new PoliController();
        setTitle("Poli Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        component();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void component() {
        poliListModel = new DefaultListModel<>();
        for (Poli poli : poliController.getAll()) {
            poliListModel.addElement(poli.namaPoli);
        }

        poliList = new JList<>(poliListModel);
        poliList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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

        JScrollPane scrollPane = new JScrollPane(poliList);

        JPanel centerPanel = new JPanel(new GridBagLayout());
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
            poliListModel.addElement(newPoli);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid poli name.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PoliManagementView());
    }
}
