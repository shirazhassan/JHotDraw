package org.jhotdraw.gui.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.DrawingEditor;
import java.awt.Color;
import static org.assertj.core.api.Assertions.*;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_OPACITY;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_COLOR;

public class ThenCanvasUpdates extends Stage<ThenCanvasUpdates> {
    @ExpectedScenarioState DrawingEditor editor;
    @ExpectedScenarioState DefaultDrawing drawing;

    public ThenCanvasUpdates the_drawing_opacity_attribute_should_update_to(double expectedOpacity) {
        Double actualOpacity = drawing.get(CANVAS_FILL_OPACITY);

        assertThat(actualOpacity)
                .as("Canvas fill opacity")
                .isNotNull()
                .isEqualTo(expectedOpacity);

        return self();
    }

    public ThenCanvasUpdates the_drawing_background_should_render_as(String colorName) {
        Color expectedColor;
        switch (colorName.toLowerCase()) {
            case "blue":
                expectedColor = Color.BLUE;
                break;
            case "red":
                expectedColor = Color.RED;
                break;
            case "green":
                expectedColor = Color.GREEN;
                break;
            case "white":
                expectedColor = Color.WHITE;
                break;
            default:
                expectedColor = Color.WHITE;
        }

        Color actualColor = drawing.get(CANVAS_FILL_COLOR);

        assertThat(actualColor)
                .as("Canvas fill color")
                .isNotNull()
                .isEqualTo(expectedColor);

        return self();
    }
}