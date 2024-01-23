//package example;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableRowSorter;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.FileOutputStream;
//import java.util.regex.PatternSyntaxException;
//
//public class CetakTransaksiView extends JFrame{
//    private DefaultTableModel transaksiTableModel;
//    private JTable transaksiTable;
//    private JTextField searchField;
//
//    public CetakTransaksiView() {
//        initialize();
//    }
//
//    private void initialize() {
//        setTitle("Detail Transaksi");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 400);
//
//        String[] columnNames = {"ID Transaksi", "Tanggal", "Total Harga"};
//        Object[][] data = {
//                {"T001", "2024-01-16", "500,000"},
//                {"T002", "2024-01-17", "750,000"},
//                {"T003", "2024-01-17", "750,000"},
//                {"T004", "2024-01-17", "750,000"},
//                {"T005", "2024-01-17", "750,000"},
//                {"T006", "2024-01-17", "750,000"},
//                {"T007", "2024-01-17", "750,000"},
//                {"T002", "2024-01-17", "750,000"},
//                {"T009", "2024-01-17", "750,000"},
//                {"T010", "2024-01-17", "750,000"},
//                {"T011", "2024-01-17", "750,000"},
//                {"T012", "2024-01-17", "750,000"},
//                {"T013", "2024-01-17", "750,000"},
//        };
//        transaksiTableModel = new DefaultTableModel(data, columnNames);
//        transaksiTable = new JTable(transaksiTableModel);
//
//        searchField = new JTextField(10);
//        JButton searchButton = new JButton("Cari Transaksi");
//
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String searchTerm = searchField.getText();
//                if (!searchTerm.isEmpty()) {
//                    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(transaksiTableModel);
//                    transaksiTable.setRowSorter(sorter);
//
//                    try {
//                        RowFilter<Object, Object> rowFilter = RowFilter.regexFilter(searchTerm, 0);
//                        sorter.setRowFilter(rowFilter);
//                    } catch (PatternSyntaxException pse) {
//                        pse.printStackTrace();
//                    }
//                } else {
//                    transaksiTable.setRowSorter(null);
//                }
//            }
//        });
//
//        JPanel searchPanel = new JPanel();
//        searchPanel.add(new JLabel("Cari Transaksi: "));
//        searchPanel.add(searchField);
//        searchPanel.add(searchButton);
//
//        JButton printButton = new JButton("Cetak Transaksi");
//        printButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int selectedRow = transaksiTable.getSelectedRow();
//                    if (selectedRow == -1) {
//                        JOptionPane.showMessageDialog(null, "Pilih satu baris transaksi untuk dicetak.");
//                        return;
//                    }
//
//                    String idTransaksi = (String) transaksiTableModel.getValueAt(selectedRow, 0);
//                    String filePath = "src/file/" + idTransaksi + ".pdf";
//
//                    Document document = new Document();
//                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
//                    document.open();
//
//                    String tanggal = (String) transaksiTableModel.getValueAt(selectedRow, 1);
//                    String totalHarga = (String) transaksiTableModel.getValueAt(selectedRow, 2);
//
//                    document.add(new Paragraph("ID Transaksi: " + idTransaksi));
//                    document.add(new Paragraph("Tanggal: " + tanggal));
//                    document.add(new Paragraph("Total Harga: " + totalHarga));
//                    document.add(new Paragraph("\n"));
//
//                    document.close();
//                    JOptionPane.showMessageDialog(null, "File " + idTransaksi + ".pdf telah berhasil dibuat di folder src/file.");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        JButton kembaliButton = new JButton("Kembali");
//        kembaliButton.addActionListener( e -> {
//            dispose();
//            new AdminView().setVisible(true);
//        });
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(kembaliButton);
//        buttonPanel.add(printButton);
//
//        JScrollPane scrollPane = new JScrollPane(transaksiTable);
//        setLayout(new BorderLayout());
//        add(searchPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new CetakTransaksiView());
//    }
//}
