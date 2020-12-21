package command;

public enum Operation {
    DOWN,
    LEFT,
    RIGHT,
    TURN,
    EXIT;

    public static Operation getOperation(int keyCode) {
        switch(keyCode) {
            case 37 : return LEFT;
            case 39 : return RIGHT;
            case 40 : return DOWN;
            case 32 : return TURN;
            case 27 : return EXIT;
            default: return null;
        }
    }
}
