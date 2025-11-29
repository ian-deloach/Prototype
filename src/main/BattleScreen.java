package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BattleScreen {

    HashMap<String, BufferedImage> dieImages = new HashMap<>();
    HashMap<BufferedImage, Point> images = new HashMap<>();
    DrawPanel dicePanel = new DrawPanel();

    public void createDice(GameScreen screen) throws IOException {
        loadDice();

        images.put(dieImages.get("d1.png"), new Point(100, 100));
        images.put(dieImages.get("d2.png"), new Point(200, 100));
        images.put(dieImages.get("d3.png"), new Point(300, 100));
        images.put(dieImages.get("d4.png"), new Point(400, 100));
        images.put(dieImages.get("d5.png"), new Point(500, 100));
        images.put(dieImages.get("d6.png"), new Point(600, 100));
        dicePanel.setOpaque(false);
        dicePanel.setBounds(0, 0, 1280, 720);

        screen.getLayers().add(dicePanel, 1);
        screen.repaint();
        dicePanel.setBounds(0, 0, screen.getWidth(), screen.getHeight());
    }

    public void loadDice() throws IOException {
        File folder = new File("src/resources/dice/");
        File[] loadedImages = folder.listFiles();

        assert loadedImages != null;
        for (File file : loadedImages) {
            dieImages.put(file.getName(), ImageIO.read(file));
        }
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
