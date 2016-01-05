package eu.simmig.langdonant;

public class AntYard implements AntPlane {
    private int width;
    private int height;
    private int grid[][];

    public AntYard(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new int[width][height];
    }

    public void init() {
        for (int iy = 0; iy < height; iy += 1) {
            for (int ix = 0; ix < width; ix += 1) {
                grid[ix][iy] = 0;
            }
        }
    }

    public void setColorAt(int x, int y, int color) {
        grid[x][y] = color;
    }

    public int getColorAt(int x, int y) {
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
