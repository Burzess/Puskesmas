package view.admin;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import controller.TransaksiController;
import node.Transaksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static assets.Assets.colorButton;

public class CetakTransaksiView extends JFrame {
    private DefaultTableModel transaksiTableModel;
    private JTable transaksiTable;
    private JTextField searchField;

    public CetakTransaksiView() {
        initialize();
    }

    private void initialize() {
        setTitle("Detail Transaksi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        String[] columnNames = {"ID Transaksi", "Nomor Antrian", "Nama Pasien", "NIK Pasien", "Nama Poli"};

        TransaksiController transaksiController = new TransaksiController();
        List<Transaksi> transaksiList = transaksiController.getAllTransaki();

        if (transaksiList != null && !transaksiList.isEmpty()) {
            Object[][] data = new Object[transaksiList.size()][columnNames.length];

            for (int i = 0; i < transaksiList.size(); i++) {
                Transaksi transaksi = transaksiList.get(i);
                data[i][0] = transaksi.idTransaksi;
                data[i][1] = transaksi.antrean.index;
                data[i][2] = transaksi.pasien.namaPasien;
                data[i][3] = transaksi.pasien.NIK;
                data[i][4] = transaksi.poli.namaPoli;
            }

            transaksiTableModel = new DefaultTableModel(data, columnNames);
            transaksiTable = new JTable(transaksiTableModel);
        } else {
            transaksiTableModel = new DefaultTableModel(columnNames, 0);
            transaksiTable = new JTable(transaksiTableModel);
            JOptionPane.showMessageDialog(null, "Tidak ada transaksi yang tersedia.");
        }

        searchField = new JTextField(10);
        JButton searchButton = new JButton("Cari Transaksi");

        searchButton.addActionListener(e -> {
                String searchTerm = searchField.getText();
                if (!searchTerm.isEmpty()) {
                    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(transaksiTableModel);
                    transaksiTable.setRowSorter(sorter);

                    try {
                        RowFilter<Object, Object> rowFilter = RowFilter.regexFilter(searchTerm, 0);
                        sorter.setRowFilter(rowFilter);
                    } catch (PatternSyntaxException pse) {
                        pse.getMessage();
                    }
                } else {
                    transaksiTable.setRowSorter(null);
                }
        });

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Cari Transaksi: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JButton printButton = new JButton("Cetak Transaksi");
        printButton.setBackground(colorButton);
        printButton.addActionListener(e -> {
            try {
                int selectedRow = transaksiTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih satu baris transaksi untuk dicetak.");
                    return;
                }

                int idTransaksi = (int) transaksiTableModel.getValueAt(selectedRow, 0);
                Transaksi transaksi = transaksiController.getTransaksiById(idTransaksi);

                if (transaksi != null) {
                    String filename = transaksi.poli.namaPoli + transaksiTableModel.getValueAt(selectedRow, 0);
                    String filePath = "src/file_transaksi/" + filename + ".pdf";

                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();

                    Paragraph header = new Paragraph("Transaksi Poli " + transaksi.poli.namaPoli, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
                    header.setAlignment(Element.ALIGN_CENTER);
                    document.add(header);

                    Paragraph detail = new Paragraph("\nDetail Transaksi", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
                    detail.setAlignment(Element.ALIGN_CENTER);
                    document.add(detail);

                    String message = "ID Transaksi : " + transaksi.idTransaksi + "\n" +
                            "Nomor Antrian : " + transaksi.antrean.index + "\n" +
                            "Nama Pasien : " + transaksi.pasien.namaPasien + "\n" +
                            "NIK Pasien : " + transaksi.pasien.NIK + "\n" +
                            "Poli : " + transaksi.poli.namaPoli + "\n";
                    Paragraph paragraph = new Paragraph(message);
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph);

                    document.close();
                    JOptionPane.showMessageDialog(null, "File " + filename + ".pdf telah berhasil dibuat di folder src/file_transaksi.");
                } else {
                    JOptionPane.showMessageDialog(null, "Transaksi tidak ditemukan.");
                }

            } catch (DocumentException | HeadlessException | FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        });

        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setBackground(colorButton);
        kembaliButton.addActionListener(e -> {
            dispose();
            new HomeAdmin().setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(kembaliButton);
        buttonPanel.add(printButton);

        JScrollPane scrollPane = new JScrollPane(transaksiTable);
        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
