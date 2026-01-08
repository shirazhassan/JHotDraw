package rectangleToolTests;

import static org.junit.Assert.*;

import org.jhotdraw.draw.figure.RectangleFigure;
import org.junit.Test;
import java.awt.geom.*;

public class RectangleFigureTest {

    @Test
    public void testSetBoundsCreatesCorrectRectangle() {
        RectangleFigure fig = new RectangleFigure();
        fig.setBounds(new Point2D.Double(10, 10), new Point2D.Double(30, 40));

        Rectangle2D bounds = fig.getBounds();
        assertEquals(10, bounds.getX(), 0.001);
        assertEquals(10, bounds.getY(), 0.001);
        assertEquals(20, bounds.getWidth(), 0.001);
        assertEquals(30, bounds.getHeight(), 0.001);
    }

    @Test
    public void testContainsPointInside() {
        RectangleFigure fig = new RectangleFigure(0, 0, 100, 100);
        assertTrue(fig.contains(new Point2D.Double(50, 50)));
    }

    @Test
    public void testContainsPointOutside() {
        RectangleFigure fig = new RectangleFigure(0, 0, 100, 100);
        assertFalse(fig.contains(new Point2D.Double(150, 150)));
    }

    @Test
    public void testRectangleInvariantNonNegativeSize() {
        RectangleFigure fig = new RectangleFigure(0, 0, 50, 50);
        Rectangle2D bounds = fig.getBounds();

        assert bounds.getWidth() >= 0 : "Width should never be negative";
        assert bounds.getHeight() >= 0 : "Height should never be negative";
    }

}
