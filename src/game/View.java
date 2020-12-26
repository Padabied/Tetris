package game;


import command.CommandExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class View extends JPanel {

    private  JFrame frame;

    // Size of a cell
    public static int SIZE = 15;
    // High of a panel
    public static int HEIGHT = SIZE * 24;
    // Width of a panel
    public static int WIDTH = SIZE * 15;

    private String[][] field = Game.getField().getField();

    public View(JFrame frame) {
        this.frame = frame;
        enableInputMethods(true);
        enableEvents(KeyEvent.KEY_EVENT_MASK);
        addKeyListener(new Controller(new CommandExecutor(frame)));
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphic2d.setColor(Color.WHITE);
        graphic2d.fillRect(0, 0, 24 * SIZE, 15 * SIZE);

        //Outline
        graphic2d.setStroke(new BasicStroke(
                5.0f,
                       BasicStroke.CAP_ROUND,
                       BasicStroke.CAP_ROUND,
                 5.0f,
                     null,
                0.0f));
        //frame
        graphic2d.setColor(Color.BLACK);
        graphic2d.drawRect(1, 1, 15 * SIZE, 4 * SIZE - 5);
        graphic2d.setColor(Color.red);
        //text
        String points = "Score:  " + Game.getScore();
        graphic2d.drawChars(points.toCharArray(), 0, points.length(), SIZE * 4, (4 * SIZE) / 2);

        for (int x = 4; x < 24; x++) {
            for (int y = 0; y < 15; y++) {
                if (field[x][y].equals(" ")) {
                    graphic2d.setColor(Color.WHITE);
                }
                else if (field[x][y].equals("O")) {
                    graphic2d.setColor(Color.BLACK);
                }
                else {
                    graphic2d.setColor(Color.BLUE);
                }
                graphic2d.fillRect(y * SIZE, x * SIZE, y * SIZE + SIZE, x * SIZE + SIZE);
            }
        }

        graphic2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND, 1.5f, null, 0.0f) );
        //brighter
        graphic2d.setColor(Color.lightGray.brighter());

        for (int x = 1; x < 15; x ++) {
            graphic2d.drawLine(x * SIZE, 4 * SIZE, x * SIZE, 24 * SIZE);
        }
        for (int y = 4; y < 24; y++) {
            graphic2d.drawLine(0, y * SIZE, 15 * SIZE, y * SIZE);
        }
    }
}
