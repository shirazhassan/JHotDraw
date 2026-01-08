package org.jhotdraw.draw;

import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;
import static org.jhotdraw.draw.AttributeKeys.*;

/**
 * Simple unit tests for DefaultDrawingView focusing on basic functionality.
 */
public class DefaultDrawingViewTest {
    private DefaultDrawingView view;
    private DefaultDrawing drawing;

    @Before
    public void setUp() {
        // Create a test drawing with canvas properties
        drawing = new DefaultDrawing();
        drawing.set(CANVAS_WIDTH, 800.0);
        drawing.set(CANVAS_HEIGHT, 600.0);
        drawing.set(CANVAS_FILL_COLOR, Color.WHITE);

        // Create the drawing view
        view = new DefaultDrawingView();
        view.setDrawing(drawing);
    }

    @Test
    public void testCanvasColorAttribute() {
        Color testColor = Color.RED;
        drawing.set(CANVAS_FILL_COLOR, testColor);
        assertEquals(testColor, drawing.get(CANVAS_FILL_COLOR));
    }

    @Test
    public void testCanvasOpacity() {
        drawing.set(CANVAS_FILL_OPACITY, 0.5);
        assertEquals(0.5, drawing.get(CANVAS_FILL_OPACITY), 0.001);
    }

    @Test
    public void testCanvasDimensions() {
        drawing.set(CANVAS_WIDTH, 1920.0);
        drawing.set(CANVAS_HEIGHT, 1080.0);

        assertEquals(1920.0, drawing.get(CANVAS_WIDTH), 0.001);
        assertEquals(1080.0, drawing.get(CANVAS_HEIGHT), 0.001);
    }

    @Test
    public void testDrawingAssignment() {
        DefaultDrawing newDrawing = new DefaultDrawing();
        view.setDrawing(newDrawing);
        assertEquals(newDrawing, view.getDrawing());
    }

    @Test
    public void testScaleFactor() {
        view.setScaleFactor(2.0);
        assertEquals(2.0, view.getScaleFactor(), 0.001);
    }
}