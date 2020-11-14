package figures;

public enum TFigure implements Figure {

    ONE(new String[][] {
                new String[]      {"X", "X", "X"},
                new String[]      {" ", "X", " "}}),

    TWO(new String[][] {
                new String[]      {" ", "X"},
                new String[]      {"X", "X"},
                new String[]      {" ", "X"}}),

    THREE(new String[][] {
                new String[]      {" ", "X", " "},
                new String[]      {"X", "X", "X"}}),

    FOUR(new String[][] {
                new String[]      {"X", " "},
                new String[]      {"X", "X"},
                new String[]      {"X", " "}});


    public Figure turnRight() {
        switch (this.name()) {
            case "ONE":
                return TFigure.TWO;
            case "TWO":
                return TFigure.THREE;
            case "THREE":
                return TFigure.FOUR;
            case "FOUR":
                return TFigure.ONE;
        }
        return this;
    }

    public Figure turnLeft() {
        switch (this.name()) {
            case "ONE":
                return GFigure.FOUR;
            case "TWO":
                return GFigure.ONE;
            case "THREE":
                return GFigure.TWO;
            case "FOUR":
                return GFigure.THREE;
        }
        return this;
    }

    private String[][] body;

    TFigure(String[][] body) {
        this.body = body;
    }

    public String[][] getBody() {
        return body;
    }

    @Override
    public int getHigh() {
        return body.length-1;
    }

    @Override
    public int getLength() {
        return body[0].length;
    }
}
