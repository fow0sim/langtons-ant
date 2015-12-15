package eu.simmig.langdonant;

public class LangdonAnt {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final String DEFAULT_RULE = "RL";

    private int direction = NORTH;
    private int x;
    private int y;
    private int iterations = 0;
    private int loops = 1;
    private String rule;
    private GridSpace grid;

    public LangdonAnt(int x, int y, GridSpace gridSpace, String rule) {
        this.x = x;
        this.y = y;
        this.grid = gridSpace;
        this.rule = rule.trim();
    }

    public LangdonAnt(int x, int y, GridSpace gridSpace) {
        this(x, y, gridSpace, DEFAULT_RULE);
    }

    public void step() {
        for (int i = 0; i < getLoops(); i += 1) {
            switch (rule.charAt(fieldColor())) {
            case 'R':
            case 'r':
            case '1':
                rotateRight();
                break;
            case 'L':
            case 'l':
            case '0':
                rotateLeft();
                break;
            default:
                throw new IllegalArgumentException("Invalid rule: " + rule);
        }
        switchColor();
        move();
        }
    }

    public void switchColor() {
        int colorCount = rule.length();
        grid.setColor(x, y, (fieldColor() + 1) % colorCount);
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
        iterations += 1;
    }

    public int getIterations() {
        return iterations;
    }

    public String getRule() {
        return rule;
    }

    public int getLoops() {
        return loops;
    }

    public void setLoops(int loops) {
        this.loops = loops;
    }
}
