package org.jhotdraw.draw;

import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import static org.junit.Assert.*;
import static org.jhotdraw.draw.AttributeKeys.*;

public class DefaultDrawingTest {
    private DefaultDrawing drawing;

    @Before
    public void setUp() {
        drawing = new DefaultDrawing();
    }

    @Test
    public void testCanvasOpacityLimits() {
        // Set opacity to 50%
        drawing.set(CANVAS_FILL_OPACITY, 0.5);

        // Assert opacity is stored correctly
        assertEquals(0.5, drawing.get(CANVAS_FILL_OPACITY), 0.001);
    }

    @Test
    public void testCanvasDefaultValues() {
        // JHotDraw should provide default values if nothing is set
        assertNotNull("Default canvas color should not be null", drawing.get(CANVAS_FILL_COLOR));
        assertEquals(1.0, drawing.get(CANVAS_FILL_OPACITY), 0.0);
    }
}