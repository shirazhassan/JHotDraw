
package org.jhotdraw.gui.action;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class CanvasCustomizationTest extends ScenarioTest<GivenDrawingApp, WhenUserCustomizes, ThenCanvasUpdates> {

    // Scenario 2: Opacity Preview
    @Test
    public void user_adjusts_background_opacity() {
        given().the_application_is_open_with_a_blank_drawing()
                .and().the_canvas_has_a_solid_background_color();

        when().the_user_opens_the_edit_canvas_dialog()
                .and().types_$_into_the_opacity_field("50")
                .and().presses_enter();

        then().the_drawing_opacity_attribute_should_update_to(0.5);
    }

    // Scenario 1: Custom Colors
    @Test
    public void user_changes_canvas_background_color() {
        given().the_application_is_open_with_a_blank_drawing();

        when().the_user_opens_the_edit_canvas_dialog()
                .and().selects_color("Blue");

        then().the_drawing_background_should_render_as("Blue");
    }
}