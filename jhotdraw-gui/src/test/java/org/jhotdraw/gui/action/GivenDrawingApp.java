package org.jhotdraw.gui.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DrawingEditor;
import java.awt.Color;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_COLOR;

public class GivenDrawingApp extends Stage<GivenDrawingApp> {
    @ProvidedScenarioState DrawingEditor editor;
    @ProvidedScenarioState DefaultDrawing drawing;
    @ProvidedScenarioState EditCanvasAction action;

    public GivenDrawingApp the_application_is_open_with_a_blank_drawing() {
        drawing = new DefaultDrawing();
        DefaultDrawingView view = new DefaultDrawingView();
        view.setDrawing(drawing);
        editor = new DefaultDrawingEditor();
        editor.setActiveView(view);
        action = new EditCanvasAction(null, editor);
        return self();
    }

    public GivenDrawingApp the_canvas_has_a_solid_background_color() {
        // Set a default solid color (e.g., White)
        drawing.set(CANVAS_FILL_COLOR, Color.WHITE);
        return self();
    }
}