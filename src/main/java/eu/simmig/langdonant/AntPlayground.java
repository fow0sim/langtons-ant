package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;

public class AntPlayground extends JPanel {
    private GridSpace grid;
    private Color colorMap[] = {Color.WHITE, Color.BLUE, Color.GREEN,
            Color.CYAN, Color.ORANGE, Color.YELLOW, Color.BLACK};

    public AntPlayground(GridSpace grid) {
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int scale = 4;
        g.setColor(colorMap[0]);
        g.fillRect(0, 0, grid.getWidth() * scale, grid.getHeight() * scale);
        for (int i = 0; i < grid.getWidth(); i += 1) {
            for (int j = 0; j < grid.getHeight(); j += 1) {
                int colorIndex = grid.getColor(i, j);
                if (colorIndex > 0) {
                    int ix = (colorIndex >= colorMap.length) ? 1 : colorIndex;
                    g.setColor(colorMap[ix]);
                    g.fillRect(i * scale, j * scale, scale, scale);
                }
            }
        }
    }
}
