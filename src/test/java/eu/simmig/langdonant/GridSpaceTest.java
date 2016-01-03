package eu.simmig.langdonant;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GridSpaceTest {
    int width = 20;
    int height = 10;
    GridSpace grid = new GridSpace(width, height);

    @Before
    public void setUp() {
        grid.init();
    }

    @Test
    public void testSetColor() {
        int x = 10;
        int y = 5;
        int color = 1;
        grid.setColorAt(10, 5, color);
        assertEquals(grid.getColorAt(x, y), color);
    }

    @Test
    public void testLangdonAnt() {
        int initialX = width / 2;
        int initialY = height / 2;
        LangdonAnt ant = new LangdonAnt(initialX, initialY, grid);
        assertEquals(ant.getRule(), "RL");
        ant.step();
        assertEquals(initialX + 1, ant.getX());
        assertEquals(initialY, ant.getY());
        assertEquals(LangdonAnt.EAST, ant.getDirection());
    }
}
