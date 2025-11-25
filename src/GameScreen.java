import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {

    public void createFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = dimension.width;
        int height = dimension.height;

        JFrame frame = new JFrame();
        frame.setSize(width, height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.black);

        frame.setVisible(true);

    }

}
