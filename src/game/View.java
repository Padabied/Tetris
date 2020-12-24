package game;


import command.CommandExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class View extends JPanel {

    private  JFrame frame;

    // размер одного квадратика
    public static int SIZE = 15;
    // ывсота в квадратах
    public static int HEIGHT = SIZE * 24;
    // ширина
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


        for (int x = 4; x < 24; x++) {
            for (int y = 0; y < 15; y++) {
                if (field[x][y].equals(" ")) {
                    //тут цвет указываем
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
    }
}
