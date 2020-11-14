
import figures.*;

import java.util.Arrays;

public class Field {

    private String[][] field = new String[24][15];

    public Field() {
        for (String[] line : field) {
            Arrays.fill(line, " ");
        }
    }

    public String[][] getField() {
        return field;
    }

    public int getLength() {
        return field[0].length;
    }

    public int getHeight() {
        return field.length;
    }

    public void printField() {
        for (int i = 4; i < field.length -1; i++) {
            String stringLine = Arrays.toString(field[i]).replaceAll(",", "");
            stringLine = stringLine.substring(1, stringLine.length()-1);
            ConsoleHelper.print(stringLine);
        }

//        for(String[] line : field) {
//            String stringLine = Arrays.toString(line).replaceAll(",", "");
//            stringLine = stringLine.substring(1, stringLine.length()-1);
//            ConsoleHelper.print(stringLine);
//        }
    }
}
