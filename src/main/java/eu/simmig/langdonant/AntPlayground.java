package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;

public class AntPlayground extends JPanel {
    private GridSpace grid;
    public AntPlayground(GridSpace grid) {
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int scale = 4;
        g.setColor(grid.didEscape() ? Color.CYAN : Color.WHITE);
        g.fillRect(0, 0, grid.getWidth() * scale, grid.getHeight() * scale);
        g.setColor(Color.BLACK);
        for (int i = 0; i < grid.getWidth(); i += 1) {
            for (int j = 0; j < grid.getHeight(); j += 1) {
                if (grid.getColor(i, j) == GridSpace.BLACK) {
                    g.fillRect(i * scale, j * scale, scale, scale);
                }
            }
        }
    }
}
