package example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupMenuExample extends JFrame {
    private JPopupMenu popupMenu;

    public PopupMenuExample() {
        setTitle("Popup Menu Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPopupMenu();
        createContent();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();

        JMenuItem menuItem1 = new JMenuItem("Menu Item 1");
        JMenuItem menuItem2 = new JMenuItem("Menu Item 2");

        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PopupMenuExample.this, "Menu Item 1 clicked");
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PopupMenuExample.this, "Menu Item 2 clicked");
            }
        });
    }

    private void createContent() {
        JTextArea textArea = new JTextArea();
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Menggunakan JMenuItem di atas panel sebagai trigger
        JMenuItem triggerMenuItem = new JMenuItem("Show Popup Menu");
        triggerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(triggerMenuItem, 0, triggerMenuItem.getHeight());
            }
        });

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(triggerMenuItem);
        add(panel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PopupMenuExample::new);
    }
}

