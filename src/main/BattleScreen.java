package main;

import org.lwjgl.util.zstd.ZSTDOutBuffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BattleScreen {

    HashMap<String, BufferedImage> dieImages = new HashMap<>();
    HashMap<BufferedImage, Point> images = new HashMap<>();
    DrawPanel dicePanel = new DrawPanel();
    ImageIcon[] draftDie = new ImageIcon[6];
    Input input = new Input();

    public void createDice(GameScreen screen) throws IOException {
        loadDice();

        JLabel testDie = new JLabel(new ImageIcon(dieImages.get("d1.png")));
        screen.add(testDie);
        testDie.setBounds(1000, 100, dieImages.get("d1.png").getWidth(), dieImages.get("d1.png").getHeight());
        testDie.addMouseListener(input);

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
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            for (HashMap.Entry<BufferedImage, Point> image: images.entrySet()) {
                graphics.drawImage(image.getKey(),
                        (int)image.getValue().getX(),
                        (int)image.getValue().getY(),
                        image.getKey().getWidth(),
                        image.getKey().getHeight(),
                        null);
            }

            graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            graphics.drawString("oough", 400, 270);

        }
    }
}
