package org.jhotdraw.gui.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.DefaultDrawing;
import java.awt.Color;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_COLOR;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_OPACITY;

public class WhenUserCustomizes extends Stage<WhenUserCustomizes> {
    @ExpectedScenarioState EditCanvasAction action;
    @ExpectedScenarioState DefaultDrawing drawing;

    public WhenUserCustomizes the_user_opens_the_edit_canvas_dialog() {
        // Dialog opening is simulated
        return self();
    }

    public WhenUserCustomizes types_$_into_the_opacity_field(String value) {
        // Simulate typing opacity value
        double opacity = Double.parseDouble(value) / 100.0;
        drawing.set(CANVAS_FILL_OPACITY, opacity);
        return self();
    }

    public WhenUserCustomizes presses_enter() {
        // Simulate pressing Enter (action already completed in previous step)
        return self();
    }

    public WhenUserCustomizes selects_color(String colorName) {
        // Convert color name to Color object
        Color color;
        switch (colorName.toLowerCase()) {
            case "blue":
                color = Color.BLUE;
                break;
            case "red":
                color = Color.RED;
                break;
            case "green":
                color = Color.GREEN;
                break;
            case "white":
                color = Color.WHITE;
                break;
            default:
                color = Color.WHITE;
        }

        // Set the canvas fill color
        drawing.set(CANVAS_FILL_COLOR, color);
        return self();
    }
}