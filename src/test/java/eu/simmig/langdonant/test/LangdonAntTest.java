package eu.simmig.langdonant.test;

import eu.simmig.langdonant.AntPlane;
import eu.simmig.langdonant.AntYard;
import eu.simmig.langdonant.LangdonAnt;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LangdonAntTest {
    public static final int RL_ITERATIONS = 200;
    public static final int RL_WIDTH = 20;
    public static final int RL_HEIGHT = 10;
    public static final int[][] RL_PLANE = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 }
    };

    public static final int MC_ITERATIONS = 300;
    public static final int MC_WIDTH = 20;
    public static final int MC_HEIGHT = 10;
    public static final String MC_RULE = "RLRRRL";
    public static final int[][] MC_PLANE = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 2, 3, 2, 2, 3, 3, 2, 2, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 4, 5, 5, 5, 5, 5, 3, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 3, 2, 4, 2, 3, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 0, 1, 3, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 5, 0, 2, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 3, 2, 3, 1, 1, 4, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 2, 3, 3, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 }
    };

    @Test
    public void testSimpleLangdonAnt() {
        AntPlane antYard = new AntYard(RL_WIDTH, RL_HEIGHT);
        int initialX = RL_WIDTH / 2;
        int initialY = RL_HEIGHT / 2;
        LangdonAnt ant = new LangdonAnt(initialX, initialY, antYard);
        assertEquals(ant.getRule(), "RL");
        ant.step();
        assertEquals(initialX + 1, ant.getX());
        assertEquals(initialY, ant.getY());
        assertEquals(LangdonAnt.EAST, ant.getDirection());
        assertEquals(1, ant.getLoops());
        ant.step(RL_ITERATIONS - 1);
        for (int iy = 0; iy < antYard.getHeight(); iy += 1) {
            for (int ix = 0; ix < antYard.getWidth(); ix += 1) {
                assertEquals(RL_PLANE[iy][ix], antYard.getColorAt(ix, iy));
            }
        }
        assertEquals(LangdonAnt.NORTH, ant.getDirection());
        assertEquals(RL_ITERATIONS, ant.getIterations());
    }

    @Test
    public void testMulticolorLangdonAnt() {
        AntPlane antYard = new AntYard(MC_WIDTH, MC_HEIGHT);
        int initialX = MC_WIDTH / 2;
        int initialY = MC_HEIGHT / 2;
        LangdonAnt ant = new LangdonAnt(initialX, initialY, antYard, MC_RULE);
        assertEquals(ant.getRule(), MC_RULE);
        ant.step(MC_ITERATIONS);
        for (int iy = 0; iy < antYard.getHeight(); iy += 1) {
            for (int ix = 0; ix < antYard.getWidth(); ix += 1) {
                assertEquals(MC_PLANE[iy][ix], antYard.getColorAt(ix, iy));
            }
        }
        assertEquals(LangdonAnt.SOUTH, ant.getDirection());
        assertEquals(MC_ITERATIONS, ant.getIterations());
    };

    @Test
    public void testOutOfBounds() {
        AntPlane antPlane = new AntYard(5, 5);
        LangdonAnt ant = new LangdonAnt(2, 2, antPlane);
        ant.step(2000);
        assertEquals(true, ant.isOutOfBounds());
        assertEquals(21, ant.getIterations());
    }

    @Test
    public void testConvenienceConstructors() {
        LangdonAnt ant = new LangdonAnt(10, 5);
        assertEquals(5, ant.getX());
        assertEquals(2, ant.getY());
        assertEquals("RL", ant.getRule());
        ant = new LangdonAnt(15, 8, " rl 110");
        assertEquals(7, ant.getX());
        assertEquals(4, ant.getY());
        assertEquals("RLRRL", ant.getRule());
    }

    @Test
    public void testRuleSets() {
        AntPlane antPlane = new AntYard(5, 5);
        LangdonAnt ant = new LangdonAnt(2, 2, antPlane, "  10RRL lr0 ");
        assertEquals("RLRRLLRL", ant.getRule());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidRule() {
        AntPlane antPlane = new AntYard(5, 5);
        LangdonAnt ant = new LangdonAnt(2, 2, antPlane, "Invalid rule");
    }

    protected void printPlane(AntPlane antPlane) {
        String line = "";
        for (int y = 0; y < antPlane.getHeight(); y += 1) {
            line += "{ ";
            for (int x = 0; x < antPlane.getWidth(); x += 1) {
                line += antPlane.getColorAt(x, y);
                if (x < antPlane.getWidth() - 1) {
                    line += ", ";
                }
            }
            line += " },";
            System.out.println(line);
            line = "";
        }
    }

}
