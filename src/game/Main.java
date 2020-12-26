package game;
import javax.swing.*;
import java.awt.*;

public class Main {

    private static JFrame frame;

    public static void main(String[] args) throws InterruptedException  {
        frame = new JFrame("Tetris");
        View view = new View(frame);
        frame.add(view);
        frame.setSize(View.WIDTH + 20, View.HEIGHT + 40);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - frame.getWidth())/2,
                        (screenSize.height - frame.getHeight())/2,
                            frame.getWidth(),
                            frame.getHeight());

        frame.repaint();
        Game.createNewFigure();

        while (!Game.isOver()) {
            frame.repaint();
            Thread.sleep(500);
            Game.stepDown();
        }

        //When game is over
        frame = new JFrame("Game over");
        JLabel text = new JLabel();
        text.setText("GAME OVER");
        text.setFont(new Font("Verdana", Font.BOLD, 20));
        text.setBackground(Color.LIGHT_GRAY);
        frame.add(text);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screen.width - frame.getWidth())/2,
                (screen.height - frame.getHeight())/2,
                frame.getWidth(),
                frame.getHeight());

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}