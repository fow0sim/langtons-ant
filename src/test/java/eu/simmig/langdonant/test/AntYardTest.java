package eu.simmig.langdonant.test;

import eu.simmig.langdonant.AntYard;
import eu.simmig.langdonant.LangdonAnt;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AntYardTest {
    int width = 20;
    int height = 10;
    AntYard grid = new AntYard(width, height);

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

}
