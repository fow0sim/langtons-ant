package eu.simmig.langdonant;

public class GridSpace {
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    private int width;
    private int height;
    private int grid[][];
    private boolean didEscape;

    public GridSpace(int width, int height) {
        didEscape = false;
        this.width = width;
        this.height = height;
        grid = new int[width][height];
    }

    public void init() {
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                grid[i][j] = WHITE;
            }
        }
        setDidEscape(false);
    }

    public int switchColor(int x, int y) {
        int newColor = (getColor(x, y) == BLACK) ? WHITE : BLACK;
        setColor(x, y, newColor);
        return newColor;
    }

    public void setColor(int x, int y, int color) {
        grid[x][y] = color;
    }

    public int getColor(int x, int y) {
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean didEscape() {
        return didEscape;
    }

    public void setDidEscape(boolean didEscape) {
        this.didEscape = didEscape;
    }
}
