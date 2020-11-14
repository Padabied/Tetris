package figures;

public enum GFigure implements Figure {


    ONE(new String[][] {
             new String[]      {"X", " "},
             new String[]      {"X", " "},
             new String[]      {"X", "X"}}),

    TWO(new String[][] {
            new String[]      {"X", "X", "X"},
            new String[]      {"X", " ", " "}}),

    THREE(new String[][] {
            new String[]      {"X", "X"},
            new String[]      {" ", "X"},
            new String[]      {" ", "X"}}),

    FOUR(new String[][] {
            new String[]      {" ", " ", "X"},
            new String[]      {"X", "X", "X"}});


    public Figure turnRight() {
        switch (this.name()) {
            case "ONE":
                return GFigure.TWO;
            case "TWO":
                return GFigure.THREE;
            case "THREE":
                return GFigure.FOUR;
            case "FOUR":
                return GFigure.ONE;
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

    GFigure(String[][] body) {
        this.body = body;
    }
}
