package view.pasien;

import view.Frame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assets.Assets.*;

public class HomePasien extends Frame {
    JLabel header;
    static JLabel information = new JLabel("");
    JButton bt1, bt2;
    JPanel backgroundPanel;
    Timer timer;

    public HomePasien() {
        super("Home Pasien", 300, 400);
        setLocation(40, 200);
        createMenu();
    }

    private void stopTextAnimation() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void startTextAnimation() {
        timer = new Timer(1, new ActionListener() {
            private int xPosition = getWidth();
            private int rounds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                moveText();
                int maxRounds = 3;
                if (rounds >= maxRounds) {
                    rounds = 0;
                    setInformation("Selamat Datang di Puskesmas Haha Hihi \uD83D\uDE02");
                }
            }

            private void moveText() {
                xPosition -= 2;
                information.setBounds(xPosition, 0, 600, 30);

                if (xPosition + information.getWidth() < 0) {
                    xPosition = getWidth();
                    rounds++;
                }
            }
        });

        timer.start();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu othersMenu = new JMenu("Others Menu");

        JMenuItem lihatJadwal = new JMenuItem("Lihat Jadwal Praktek");
        JMenuItem exitItem = new JMenuItem("Exit");

        othersMenu.add(lihatJadwal);
        othersMenu.add(exitItem);

        lihatJadwal.addActionListener(e ->{
            stopTextAnimation();
            dispose();
            new JadwalPraktekView().setVisible(true);
        });

        exitItem.addActionListener(e -> System.exit(0));

        CompoundBorder compoundBorder = new CompoundBorder(
                new LineBorder(Color.BLACK),
                new EmptyBorder(2, 2, 2, 2)
        );

        othersMenu.setBorder(compoundBorder);

        menuBar.add(othersMenu);

        menuBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setJMenuBar(menuBar);
    }

    protected void component() {
        information.setText("Selamat Datang di Puskesmas Haha Hihi \uD83D\uDE02");
        information.setFont(new Font("JoyPixels ", Font.BOLD, 16));
        information.setHorizontalAlignment(JLabel.CENTER);
        information.setVerticalAlignment(JLabel.CENTER);
        add(information);

        header = new JLabel("<html><center>PUSKESMAS</center><center>HAHA HIHI</center></html>");
        header.setBounds(71, 50, 200, 96);
        header.setFont(fontHeader);
        add(header);

        bt1 = new JButton("Pasien Baru");
        bt1.setFont(fontButton);
        bt1.setBounds(80, 220, 130, 28);
        bt1.setFocusPainted(false);
        bt1.setBackground(colorButton);
        bt1.setForeground(Color.BLACK);
        add(bt1);

        bt2 = new JButton("Ambil Antrian");
        bt2.setFont(fontButton);
        bt2.setBounds(80, 260, 130, 28);
        bt2.setFocusPainted(false);
        bt2.setBackground(colorButton);
        bt2.setForeground(Color.BLACK);
        add(bt2);

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();

                ImageIcon logoIcon = new ImageIcon("src/assets/logo-upt-puskesmas.png");
                Image logoImage = logoIcon.getImage();
                g2d.drawImage(logoImage, 70, 20, 150, 215, this);

                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255, 220),
                        0, height, new Color(255, 255, 255, 220));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);

                g2d.dispose();
            }
        };

        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 300, 430);
        add(backgroundPanel);

        startTextAnimation();
    }

    public void event(){
        bt1.addActionListener(e -> {
                System.out.println("Pasien baru");
                stopTextAnimation();
                dispose();
                new FormDataPasienView().setVisible(true);
        });

        bt2.addActionListener(e -> {
                System.out.println("Ambil antrian");
                stopTextAnimation();
                dispose();
                new DaftarAntrian().setVisible(true);
        });
    }

    public static void setInformation(String information) {
        HomePasien.information.setText(information);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePasien().setVisible(true));
    }
}
