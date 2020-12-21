package game;

import figures.*;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

   private static Field field = new Field();
   private static Figure figure;
   private static boolean isOver;
   private boolean figureStands;
   private static int downLevel;             //индекс линии, соответствующий самой нижней клетке фигуры
   private static int leftLevel;             //индекс крайней левой клетки фигуры
   private static int rightLevel;            //индекс крайней правой клетки фигуры
   private static int score;

    public Game() {
        field = new Field();
    }

    public static Field getField() {
        return field;
    }

    public static int getScore() {return score;}

    public static void createNewFigure() {
        int x = new Random().nextInt(4);
        int y = new Random().nextInt(2);
        switch (x) {
            case 0:
                figure = GFigure.values()[y];
                break;
            case 1:
                figure = IFigure.values()[y];
                break;
            case 2:
                figure = QFigure.values()[y];
                break;
            case 3:
                figure = TFigure.values()[y];
                break;
        }
        mergeFieldAndFigure();
    }

    private static void mergeFieldAndFigure() {
        int figureLength = figure.getLength();
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 6; j < 6 + figureLength; j++) {
                    field.getField()[i][j] = figure.getBody()[i][j - 6];
                }
            }
        } catch (Exception ignored) {}
        downLevel = figure.getHigh();
        leftLevel = 6;
        rightLevel = leftLevel + figure.getLength()-1;

        while (downLevel < 3) {
            stepDown();
        }
    }

    private static boolean canStepBeDone() {
        boolean result = true;

        String[] figureLowestLevel = field.getField()[downLevel];
        String[] nextLine = field.getField()[downLevel + 1];

        for (int i = leftLevel ; i < leftLevel + figure.getLength(); i++) {
            if (figureLowestLevel[i].equals("X") && nextLine[i].equals("X")) {
                result = false;
                break;
            }
        }
        return result && (downLevel + 1) < field.getHeight() -1;   //try <=
    }

    private static boolean canStepLeftBeDone() {
        int stepLeft =  leftLevel-1;
        if (stepLeft == -1) return false;

        for (int i = 0; i < figure.getHigh(); i++) {
            String figureLeftColumn = field.getField()[downLevel -i][leftLevel];
            String fieldLeftColumn = field.getField()[downLevel -i][leftLevel -1];

            if (figureLeftColumn.equals("X") && fieldLeftColumn.equals("X")) return false;
        }
        return true;
    }

    private static boolean canStepRightBeDone() {
        int stepRight = rightLevel +1;
        if (stepRight == 15) return false;

        for (int i = 0; i < figure.getLength(); i++) {
            String figureRightColumn = field.getField()[downLevel - i][rightLevel];
            String fieldRightColumn = field.getField()[downLevel - i][rightLevel + 1];

            if (figureRightColumn.equals("X") && fieldRightColumn.equals("X")) return false;
        }
        return true;
    }

    private static boolean canFigureBeTurned() {
        if (figure == QFigure.ONE) return true;

        String[][] fieldWithFigure = extractFieldWithFigure();
        String[][] turnedFigureMatrix = Figures.getMatrix(figure.turn());
        List<String> commonCell = Figures.getIndexesOfCommonCellsOfCurrentAndTurnedFigure(figure);

        for (int i = 0; i < fieldWithFigure.length; i++) {
            for (int j = 0; j < fieldWithFigure[0].length; j++) {
                String index = String.valueOf(i) + j;
                if (!commonCell.contains(index)) {
                    if (turnedFigureMatrix[i][j].equals("X") && fieldWithFigure[i][j].equals("X")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Вырезает участок поля, в котором содержится фигура. Ширина и высота берется из Figures.getMatrix
    private static String[][] extractFieldWithFigure() {
        int matrixHigh = Figures.getMatrix(figure).length;
        int leftLevelForExtract = (figure == IFigure.ONE) ? leftLevel-1 : leftLevel;
        String[][] result = new String[matrixHigh][matrixHigh];

        int iCount = 0;
        int jCount = 0;
        for (int i = downLevel - matrixHigh+1; i <= downLevel; i++) {
            for (int j = leftLevelForExtract; j < leftLevelForExtract + matrixHigh; j++) {
                result[iCount][jCount] = field.getField()[i][j];
                jCount++;
            }
            iCount++;
            jCount = 0;
        }
        return result;
    }

    public static void stepDown() {
        //Doing step
        if (canStepBeDone()) {

            for (int i = 0; i < figure.getHigh()+1; i++) {
                String[] nextLevel = field.getField()[downLevel+1-i];
                String[] figureLowestLevel = field.getField()[downLevel-i];
                for (int j = leftLevel; j < figure.getLength() + leftLevel; j++) {
                    if (nextLevel[j].equals(" ") && figureLowestLevel[j].equals("X")) {
                        nextLevel[j] = "X";
                        figureLowestLevel[j] = " ";
                    }
                }
            }
            downLevel++;
        }
        else if (!canStepBeDone() && downLevel == 3 ) {
            isOver = true;
            System.out.println("game.Game Over");
            System.exit(0);
        }
        else if (!canStepBeDone() && downLevel > 3) {
            checkFilledLines();
            createNewFigure();
        }
        else {
            isOver = true;
            System.out.println("game.Game Over #2");
            System.exit(0);
        }
    }

    public static void stepLeft() {
        if (canStepLeftBeDone()) {
            for (int i = 0; i <figure.getLength(); i++) {
                for (int j = 0; j < figure.getHigh()+1; j++) {
                    String figureLeftElement = field.getField()[downLevel -j][leftLevel+i];
                    String fieldLeftElement = field.getField()[downLevel -j][leftLevel -1+i];

                    if (figureLeftElement.equals("X") && fieldLeftElement.equals(" ")) {
                        field.getField()[downLevel -j][leftLevel+i] = " ";
                        field.getField()[downLevel -j][leftLevel -1+i] = "X";
                    }
                }
            }
            leftLevel--;
            rightLevel--;
        }
    }

    public static void stepRight() {
        if (canStepRightBeDone()) {
            for (int i = 0; i < figure.getLength(); i++) {
                for (int j = 0; j < figure.getHigh()+1; j++) {
                    String figureRightElement = field.getField()[downLevel - j][rightLevel - i];
                    String fieldRightElement = field.getField()[downLevel - j][rightLevel - i + 1];
                    if (figureRightElement.equals("X") && fieldRightElement.equals(" ")) {
                        field.getField()[downLevel - j][rightLevel - i] = " ";
                        field.getField()[downLevel - j][rightLevel - i + 1] = "X";
                    }
                }
            }
            leftLevel++;
            rightLevel++;
        }
    }

    public static void turnFigure() {
        if (!canFigureBeTurned()) return;

        List<String> toTurnX = Figures.getIndexesOfCellsToTurn(figure);
        List<String> toTurnNull = Figures.getIndexesOfCellsToNull(figure);

        int matrixHigh = Figures.getMatrix(figure).length;
        int leftLevelForExtract = (figure == IFigure.ONE) ? leftLevel-1 : leftLevel;
        int iCount = 0;
        int jCount = 0;

        for (int i = downLevel - matrixHigh+1; i <= downLevel; i++) {
            for (int j = leftLevelForExtract; j < leftLevelForExtract + matrixHigh; j++) {
                String index = String.valueOf(iCount) + jCount;
                if (toTurnX.contains(index)) {
                    field.getField()[i][j] = "X";
                }
                else if (toTurnNull.contains(index)) {
                    field.getField()[i][j] = " ";
                }

                jCount++;
            }
            iCount++;
            jCount = 0;
        }
        figure = figure.turn();
    }

    //Метод проверяет, есть ли на поле заполненная линия, и если есть - удаляет ее и затем сдвигает поле moveFieldDown
    private static void checkFilledLines() {
        //check
        List<Integer> numbersOfFilledLines = new ArrayList<>();
        for (int i = field.getHeight()-1; i > 3; i--) {
            boolean lineIsFull = true;
            for (int j = 0; j < field.getLength(); j++) {
                if (field.getField()[i][j].equals(" ")) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                numbersOfFilledLines.add(i);
            }
        }

        //Turn to an empty line and move field down
        if (numbersOfFilledLines.size() > 0) {
            for (Integer numbersOfFilledLine : numbersOfFilledLines) {
                Arrays.fill(field.getField()[numbersOfFilledLine], " ");
            }
            moveFieldDown(numbersOfFilledLines.get(0), numbersOfFilledLines.size());
        }
    }

    //Двигает поле назад после удаления заполненной линии
    private static void moveFieldDown(int firstLineIndex, int count) {
        for (int i = 0; i < count; i++) {
            for (int x = firstLineIndex; x > 3; x--) {
                String[] lowerLine = field.getField()[x];
                String[] upperLine = field.getField()[x - 1];
                for (int y = 0; y < field.getLength(); y++) {
                    if (upperLine[y].equals("X") && lowerLine[y].equals(" ")) {
                        field.getField()[x][y] = "X";
                        field.getField()[x - 1][y] = " ";
                    }
                }
            }
        }
        score += (count * field.getLength());
    }

//    public void start() throws InterruptedException {
//        field.printField();
//        createNewFigure();
//
//        while (true) {
//            field.printField();
//            Thread.sleep(1000);
//            stepDown();
//        }
//    }
}

// СДЕЛАТЬ ВЫВОД НА ЭКРАН И KEY LISTENER , ЧТОБЫ КОМАНДЫ ВЫПОЛНЯЛИСЬ
