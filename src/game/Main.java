package game;
import javax.swing.*;

public class Main {

    private static JFrame frame;

    public static void main(String[] args) throws InterruptedException  {
        frame = new JFrame("Tetris");
        View view = new View(frame);
        frame.add(view);
        frame.setSize(View.WIDTH + 20, View.HEIGHT + 40);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.repaint();
        Game.createNewFigure();

        while (true) {
            frame.repaint();
            Thread.sleep(500);
            Game.stepDown();
        }
    }
}
