package eu.simmig.langdonant;

public class LangdonAnt {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private int direction = NORTH;
    private int x;
    private int y;
    private int lastColor;
    private GridSpace grid;

    public LangdonAnt(int x, int y, GridSpace gridSpace) {
        this.x = x;
        this.y = y;
        this.grid = gridSpace;
    }

    public DrawPoint step() {
        DrawPoint point = new DrawPoint();
        point.setX(x);
        point.setY(y);
        if (fieldColor() == GridSpace.WHITE) {
            rotateRight();
        } else {
            rotateLeft();
        }
        lastColor = grid.switchColor(x, y);
        point.setColor(lastColor);
        move();
        return point;
    }

    public int fieldColor() {
        return grid.getColor(x, y);
    }

    public void rotateLeft() {
        direction = (direction + 3) % 4;
    }

    public void rotateRight() {
        direction = (direction + 1) % 4;
    }

    public void move() {
        switch (direction) {
            case NORTH:
                x -= 1;
                break;
            case EAST:
                y += 1;
                break;
            case SOUTH:
                x += 1;
                break;
            case WEST:
                y -= 1;
                break;
        }
    }

    public String toString() {
        return "Langdon Ant: { x: " + x + ", y: " + y + ", direction: " + direction
                + ", color: " + lastColor + " }";
    }
}
