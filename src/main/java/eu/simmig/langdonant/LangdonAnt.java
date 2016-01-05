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
    private boolean outOfBounds;
    private String rule;
    private AntPlane antPlane;

    public LangdonAnt(int x, int y, AntPlane antPlane, String rule) {
        this.x = x;
        this.y = y;
        this.antPlane = antPlane;
        setRule(rule);
        setOutOfBounds(false);
    }

    public LangdonAnt(int x, int y, AntPlane antPlane) {
        this(x, y, antPlane, DEFAULT_RULE);
    }

    public LangdonAnt(int width, int height, String rule) {
        this(width / 2, height / 2, new AntYard(width, height), rule);
    }

    public LangdonAnt(int width, int height) {
        this(width, height, DEFAULT_RULE);
    }

    public void step() {
        step(getLoops());
    }

    public void step(int loops) {
        if (!isOutOfBounds()) {
            try {
                for (int i = 0; i < loops; i += 1) {
                    doOneStep();
                }
            } catch (IndexOutOfBoundsException ex) {
                setOutOfBounds(true);
            }
        }
    }

    protected void doOneStep() {
        switch (rule.charAt(fieldColor())) {
            case 'R':
                rotateRight();
                break;
            case 'L':
                rotateLeft();
                break;
            default:
                throw new IllegalArgumentException("Invalid rule: " + rule);
        }
        switchColor();
        move();
    }

    protected void switchColor() {
        int colorCount = rule.length();
        antPlane.setColorAt(x, y, (fieldColor() + 1) % colorCount);
    }

    public int fieldColor() {
        return antPlane.getColorAt(x, y);
    }

    protected void rotateLeft() {
        direction = (direction + 3) % 4;
    }

    protected void rotateRight() {
        direction = (direction + 1) % 4;
    }

    protected void move() {
        switch (direction) {
            case NORTH:
                y -= 1;
                break;
            case EAST:
                x += 1;
                break;
            case SOUTH:
                y += 1;
                break;
            case WEST:
                x -= 1;
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

    public void setRule(String rule) {
        String newRule = "";
        for (int i = 0; i < rule.length(); i += 1) {
            switch (rule.charAt(i)) {
                case 'R':
                case 'r':
                case '1':
                    newRule += "R";
                    break;
                case 'L':
                case 'l':
                case '0':
                    newRule += "L";
                    break;
                case ' ':
                    break;
                default:
                    throw new IllegalArgumentException("Invalid rule: " + rule);
            }
        }
        this.rule = newRule;
    }

    public int getLoops() {
        return loops;
    }

    public void setLoops(int loops) {
        this.loops = loops;
    }

    public int getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public AntPlane getAntPlane() {
        return antPlane;
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

    protected void setOutOfBounds(boolean outOfBounds) {
        this.outOfBounds = outOfBounds;
    }

}
