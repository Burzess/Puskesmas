package example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingText extends JFrame {
    private JLabel movingLabel;
    private Timer timer;
    private int xPosition;

    public MovingText() {
        super("Moving Text Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);

        xPosition = getWidth();  // Mulai dari kanan frame

        movingLabel = new JLabel("Teks Bergerak");
        movingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(movingLabel);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveText();
            }
        });

        timer.start();
    }

    private void moveText() {
        xPosition -= 1;  // Sesuaikan kecepatan perpindahan di sini
        movingLabel.setBounds(xPosition, 30, 200, 30);

        if (xPosition + movingLabel.getWidth() < 0) {
            xPosition = getWidth();  // Reset posisi saat mencapai batas kiri
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovingText example = new MovingText();
            example.setVisible(true);
        });
    }
}
