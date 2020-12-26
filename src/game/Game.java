package game;

import figures.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

   private static Field field = new Field();
   private static Figure figure;
   private static boolean isOver;
   private static int downLevel;             //Index of a figure floor level
   private static int leftLevel;             //Index of a figure left level
   private static int rightLevel;            //Index of a figure right level
   private static int score;

    public Game() {
        field = new Field();
    }

    public static Field getField() {
        return field;
    }

    public static int getScore() {return score;}

    public static void end() {
        isOver = true;
    }

    public static boolean isOver() {
        return isOver;
    }

    // Creates new figure and merges it with the field. First 4 String arrays of the field reserved for new figure
    public static void createNewFigure() {
            int x = new Random().nextInt(5);
            int y = new Random().nextInt(2);
            switch (x) {
                case 0:
                    figure = GFigure.values()[y];
                    break;
                case 1:
                    figure = IFigure.values()[y];
                    break;
                case 2:
                    figure = QFigure.ONE;
                    break;
                case 3:
                    figure = TFigure.values()[y];
                    break;
                case 4 :
                    figure = ZFigure.values()[y];
                    break;
            }
            mergeFieldAndFigure();
    }

    private static void mergeFieldAndFigure() {
        int figureLength = figure.getLength();
        try {
            for (int i = 0; i < figure.getHigh() + 1; i++) {    // 4 ---> figure.getHigh()
                for (int j = 6; j < 6 + figureLength; j++) {
                    field.getField()[i][j] = figure.getBody()[i][j - 6];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        downLevel = figure.getHigh();
        leftLevel = 6;
        rightLevel = leftLevel + figure.getLength()-1;

        while (downLevel < 3) {
            stepDown();
        }
    }

    private static boolean canNewFigureBeCreated() {
        for (int i = 0; i < 4; i++) {
            for (String cell : field.getField()[i]) {
                if (cell.equals("X")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canStepBeDone() {
        if (downLevel == 23) return false;

        for (int i = 0; i < field.getHeight() - 1; i++) {
            for (int j = 0; j < field.getLength(); j++) {
                String upper = field.getField()[i][j];
                String down = field.getField()[i + 1][j];

                if (upper.equals("X") && down.equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canStepLeftBeDone() {
        if (leftLevel - 1 == -1) return false;

        for (int i = 0; i < field.getHeight() - 1; i++) {
            for (int j = 1; j < field.getLength(); j++) {
                String left = field.getField()[i][j - 1];
                String right = field.getField()[i][j];

                if (right.equals("X") && left.equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canStepRightBeDone() {
        if (rightLevel + 1 == 15) return false;

        for (int i = 0; i <= field.getHeight() - 1; i++) {
            for (int j = 0; j < field.getLength() - 1; j++) {
                String left = field.getField()[i][j];
                String right = field.getField()[i][j + 1];

                if (left.equals("X") && right.equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canFigureBeTurned() {
        if (figure == QFigure.ONE) return true;

        String[][] fieldWithFigure;
        String[][] turnedFigureMatrix;
        List<String> commonCell;

        try {
            fieldWithFigure = extractFieldWithFigure();
            turnedFigureMatrix = Figures.getMatrix(figure.turn());
            commonCell = Figures.getIndexesOfCommonCellsOfCurrentAndTurnedFigure(figure);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

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

    //Extract a part of field where figure exists
    private static String[][] extractFieldWithFigure() {
        int matrixHigh = Figures.getMatrix(figure).length;
        String[][] result = new String[matrixHigh][matrixHigh];

        int iCount = 0;
        int jCount = 0;
        for (int i = downLevel - matrixHigh + 1; i <= downLevel; i++) {   //OK
            for (int j = leftLevel; j < leftLevel + matrixHigh; j++) {    //OK
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
        boolean canStepBeDone = canStepBeDone();

        if (canStepBeDone) {

            for (int i = 0; i < figure.getHigh()+1; i++) {
                String[] nextLevel = field.getField()[downLevel+1-i];
                String[] figureLowestLevel = field.getField()[downLevel-i];
                for (int j = leftLevel; j < leftLevel + figure.getLength(); j++) {
                    if (nextLevel[j].equals(" ") && figureLowestLevel[j].equals("X")) {
                        nextLevel[j] = "X";
                        figureLowestLevel[j] = " ";
                    }
                }
            }
            downLevel++;
        }
        else {
            if (downLevel == 3) {
                isOver = true;
            }
            else if (downLevel > 3 && canNewFigureBeCreated()) {
                buryFallenFigures();
                checkFilledLines();
                createNewFigure();
            }
            else {
                isOver = true;
            }
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
        if (figure.equals(QFigure.ONE)) return;

        List<String> toTurnX = Figures.getIndexesOfCellsToTurn(figure);
        List<String> toTurnNull = Figures.getIndexesOfCellsToNull(figure);

        int matrixHigh = Figures.getMatrix(figure).length;
        int iCount = 0;
        int jCount = 0;

        for (int i = downLevel - matrixHigh+1; i <= downLevel; i++) {
            for (int j = leftLevel; j < leftLevel + matrixHigh; j++) {
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
        rightLevel = leftLevel + figure.getLength()-1;
    }

    //Checks if there any filled lines on a field and removes if it exists. Next moves field down
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

    //Moves field down after removing filled lines
    private static void moveFieldDown(int firstLineIndex, int count) {
        //заменіл Х на О
        for (int i = 0; i < count; i++) {
            for (int x = firstLineIndex; x > 3; x--) {
                String[] lowerLine = field.getField()[x];
                String[] upperLine = field.getField()[x - 1];
                for (int y = 0; y < field.getLength(); y++) {
                    if (upperLine[y].equals("O") && lowerLine[y].equals(" ")) {
                        field.getField()[x][y] = "O";
                        field.getField()[x - 1][y] = " ";
                    }
                }
            }
        }
        score += (count * field.getLength());
    }

    //Converts all fallen figures cells to "O"
    private static void buryFallenFigures() {
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getLength(); j++) {
                if (field.getField()[i][j].equals("X")) {
                    field.getField()[i][j] = "O";
                }
            }
        }
    }

}

