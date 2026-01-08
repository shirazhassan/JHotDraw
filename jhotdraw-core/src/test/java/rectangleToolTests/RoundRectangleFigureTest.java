package rectangleToolTests;

import static org.junit.Assert.*;

import org.jhotdraw.draw.figure.RoundRectangleFigure;
import org.junit.Test;
import java.awt.geom.*;

public class RoundRectangleFigureTest {

    @Test
    public void testArcWidthAndHeight() {
        RoundRectangleFigure fig = new RoundRectangleFigure(0, 0, 100, 100);
        fig.setArcWidth(20);
        fig.setArcHeight(30);

        assertEquals(20, fig.getArcWidth(), 0.001);
        assertEquals(30, fig.getArcHeight(), 0.001);
    }

    @Test
    public void testContainsPointInsideRoundedRectangle() {
        RoundRectangleFigure fig = new RoundRectangleFigure(0, 0, 100, 100);
        assertTrue(fig.contains(new Point2D.Double(50, 50)));
    }

    @Test
    public void testArcInvariantNonNegative() {
        RoundRectangleFigure fig = new RoundRectangleFigure(0, 0, 100, 100);

        assert fig.getArcWidth() >= 0 : "Arc width should never be negative";
        assert fig.getArcHeight() >= 0 : "Arc height should never be negative";
    }

}
