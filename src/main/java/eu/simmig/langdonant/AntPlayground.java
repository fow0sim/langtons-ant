package eu.simmig.langdonant;

public interface AntPlayground {
    public void init();
    public int getWidth();
    public int getHeight();
    public int getColorAt(int x, int y);
    public void setColorAt(int x, int y, int color);
    public void setDidEscape(boolean b);
}
