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
    DrawingPanel borderPanel = new DrawingPanel();
    JLayeredPane layers = new JLayeredPane();

    public void createFrame() throws IOException {
        addKeyListener(this);

        border = ImageIO.read(new File("src/resources/border.png"));
        layers.setPreferredSize(new Dimension(1280, 720));
        layers.setLayout(null);
        add(layers);
        borderPanel.setBounds(0, 0, 1280, 720);
        borderPanel.setOpaque(false);
        layers.add(borderPanel, 0);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);
        getContentPane().setBackground(Color.black);

        add(borderPanel);
        pack();
        setVisible(true);
        layers.setBounds(0, 0, getWidth(), getHeight());
        borderPanel.setBounds(0, 0, getWidth(), getHeight());

        BattleScreen battle = new BattleScreen();
        battle.createDice(this);

    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(border, 320, 180, 1280, 720, null);
        }
    }

    public JLayeredPane getLayers() {
        return layers;
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


}
