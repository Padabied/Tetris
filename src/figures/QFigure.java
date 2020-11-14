package figures;

public enum QFigure implements Figure {

    ONE(new String[][] {
            new String[]      {"X", "X", "X"},
            new String[]      {"X", "X", "X"},
            new String[]      {"X", "X", "X"}}),

    TWO(new String[][] {
            new String[]      {"X", "X", "X"},
            new String[]      {"X", "X", "X"},
            new String[]      {"X", "X", "X"}});

    public Figure turnRight() {
        return this;
    }

    public Figure turnLeft() {
        return this;
    }

    private String[][] body;

    QFigure(String[][] body) {
        this.body = body;
    }

    public String[][] getBody() {
        return body;
    }

    @Override
    public int getHigh() {
        return body.length;
    }

    @Override
    public int getLength() {
        return body[0].length;
    }
}
