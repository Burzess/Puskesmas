package view.admin;

import model.ModelAdmin;
import view.Frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;


public class LoginAdmin extends Frame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;

    public LoginAdmin() {
        super("Login", 400, 600);
    }

    @Override
    protected void component() {
        System.out.println("Menu LOGIN ADMIN");
        JLabel puskesmasLabel = new JLabel("Puskesmas Haha Hihi");
        puskesmasLabel.setFont(new Font("Arial", Font.BOLD, 24));
        setBound(puskesmasLabel, 65, 200, 300, 45);

        JLabel kartuLabel = new JLabel("Login Admin");
        kartuLabel.setFont(new Font("Arial", Font.BOLD, 13));
        setBound(kartuLabel, 65, 250, 100, 18);

        usernameField = new JTextField();
        setBound(usernameField, 65, 270, 270, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        setBound(passwordLabel, 65, 315, 100, 18);

        passwordField = new JPasswordField();
        setBound(passwordField, 65, 335, 270, 30);

        loginBtn = new JButton("Login");
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(Color.DARK_GRAY);
        loginBtn.setFocusPainted(false);
        setBound(loginBtn, 157, 380, 85, 30);
    }

    @Override
    protected void event() {
        ModelAdmin admin = new ModelAdmin();

        loginBtn.addActionListener((e) -> {
            try {
                String username = String.valueOf(usernameField.getText());
                String password = String.valueOf(passwordField.getPassword());
                boolean status = admin.cekLogin(username, password);
                if (status) {
                    dispose();
                    new HomeAdmin().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Data Tidak Valid", "Gagal Login",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Masukkan Inputan Yang benar", "Login Sukses",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
