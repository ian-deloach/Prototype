package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BattleScreen {

    BufferedImage draftDie1;
    BufferedImage draftDie2;
    BufferedImage draftDie3;
    BufferedImage draftDie4;
    BufferedImage draftDie5;
    BufferedImage draftDie6;
    HashMap<BufferedImage, Point> images = new HashMap<>();
    DrawPanel dicePanel = new DrawPanel();

    public void createDice(GameScreen screen) throws IOException {
        draftDie1 = ImageIO.read(new File("src/resources/d6.png"));
        draftDie2 = ImageIO.read(new File("src/resources/d6.png"));
        draftDie3 = ImageIO.read(new File("src/resources/d6.png"));
        draftDie4 = ImageIO.read(new File("src/resources/d6.png"));
        draftDie5 = ImageIO.read(new File("src/resources/d6.png"));
        draftDie6 = ImageIO.read(new File("src/resources/d6.png"));
        images.put(draftDie1, new Point(100, 100));
        images.put(draftDie2, new Point(200, 100));
        images.put(draftDie3, new Point(300, 100));
        images.put(draftDie4, new Point(400, 100));
        images.put(draftDie5, new Point(500, 100));
        images.put(draftDie6, new Point(600, 100));
        dicePanel.setOpaque(false);
        dicePanel.setBounds(0, 0, 1280, 720);

        screen.getLayers().add(dicePanel, 1);
        screen.repaint();
        dicePanel.setBounds(0, 0, screen.getWidth(), screen.getHeight());

    }

    private class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (HashMap.Entry<BufferedImage, Point> image: images.entrySet()) {
                g.drawImage(image.getKey(),
                        (int)image.getValue().getX(),
                        (int)image.getValue().getY(),
                        image.getKey().getWidth(),
                        image.getKey().getHeight(),
                        null);
            }
        }
    }
}
