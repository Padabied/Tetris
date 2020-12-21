package figures;

public enum QFigure implements Figure {

    ONE(new String[][] {
            new String[]      {"X", "X"},
            new String[]      {"X", "X"}});

    public Figure turn() {
        return this;
    }

    private String[][] body;

    QFigure(String[][] body) {
        this.body = body;
    }

    @Override
    public String[][] getBody() {
        return body;
    }

}
