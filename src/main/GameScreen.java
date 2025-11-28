package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameScreen extends JFrame implements KeyListener {

    BufferedImage border;
    BufferedImage testDie;


    public void createFrame() throws IOException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = dimension.width;
        int height = dimension.height;
        addKeyListener(this);

        border = ImageIO.read(new File("src/resources/border.png"));
        DrawingPanel panel = new DrawingPanel();
        panel.setPreferredSize(new Dimension(1280, 720));
        panel.setOpaque(false);

        setSize(width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);
        getContentPane().setBackground(Color.black);

        add(panel);
        pack();
        setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("exit");
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(border, 320, 180, 1280, 720, null);
        }
    }

}
