package view.admin;

import controller.PoliController;
import node.Poli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoliManagementView {
    private PoliController poliController;
    private JFrame frame;
    private DefaultListModel<String> poliListModel;
    private JList<String> poliList;
    public PoliManagementView() {
        poliController = new PoliController();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Poli Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        poliListModel = new DefaultListModel<>();
        for (Poli poli : poliController.getAll()) {
            poliListModel.addElement(poli.namaPoli);
        }

        poliList = new JList<>(poliListModel);
        poliList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JButton addButton = new JButton("Tambah Poli");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPoli = JOptionPane.showInputDialog(frame, "Enter new poli:");
                if (newPoli != null && !newPoli.trim().isEmpty()) {
                    poliController.tambahPoli(newPoli);
                    JOptionPane.showMessageDialog(null, "" + newPoli + " has been added.");
                    poliListModel.addElement(newPoli);
//                    poliList.revalidate(); // atau poliList.revalidate();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid poli name.");
                }
            }

        });

        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.addActionListener( e -> {
            frame.dispose();
            new HomdeAdmin().setVisible(true);
        });

        JPanel panelButton = new JPanel();
        panelButton.add(kembaliButton);
        panelButton.add(addButton);

        JScrollPane scrollPane = new JScrollPane(poliList);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new PoliManagementView());
//    }
}
