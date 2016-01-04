package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class AntScreen extends JPanel implements ComponentListener {
    public static final int SCALE = 4;
    public static Color COLOR_MAP[] = {Color.WHITE, Color.BLACK};

    private AntPlayground grid;
    private int colorCount;

    public AntScreen(AntPlayground grid) {
        this.grid = grid;
        addComponentListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(COLOR_MAP[0]);
        g.fillRect(0, 0, grid.getWidth() * SCALE, grid.getHeight() * SCALE);
        for (int i = 0; i < grid.getWidth(); i += 1) {
            for (int j = 0; j < grid.getHeight(); j += 1) {
                int colorIndex = grid.getColorAt(i, j);
                if (colorIndex > 0) {
                    int ix = (colorIndex >= COLOR_MAP.length) ? 1 : colorIndex;
                    g.setColor(COLOR_MAP[ix]);
                    g.fillRect(i * SCALE, j * SCALE, SCALE, SCALE);
                }
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    public int getColorCount() {
        return colorCount;
    }

    public void setColorCount(int colorCount) {
        COLOR_MAP = computeColorMap(colorCount);
        this.colorCount = colorCount;
    }

    private Color[] computeColorMap(int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Count must be at least 2");
        }
        Color[] colorMap = new Color[count];
        colorMap[0] = Color.WHITE;
        colorMap[count - 1] = Color.BLACK;
        if (count > 2) {
            for (int i = 1; i < count - 1; i += 1) {
                int steps = count - 2;
                float hue = 0.3f + (i - 1) * (0.5f / steps);
                float saturation = 0.8f + (i - 1) * (0.2f / steps) ;
                float brightness = 0.8f + (i - 1) * (0.2f / steps) ;
                colorMap[i] = Color.getHSBColor(hue, saturation, brightness);
            }
        }
        return colorMap;
    }
}

