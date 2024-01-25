package view;

import javax.swing.*;
import java.awt.*;

public abstract class Frame extends JFrame{
    public Frame(String title, int width, int height){
        setTitle(title);
        setSize(width, height);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
    }

    @Override
    public void setVisible(boolean b){
        if (b){
            component();
            event();
        }
        super.setVisible(b);
    }

    protected abstract void component();
    protected abstract void event();

    protected void setBound(Component com, int x, int y, int width, int height){
        com.setBounds(x, y, width, height);
        add(com);
    }
}
