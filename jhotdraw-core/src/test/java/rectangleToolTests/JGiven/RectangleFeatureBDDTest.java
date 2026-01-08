package rectangleToolTests.JGiven;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class RectangleFeatureBDDTest
        extends ScenarioTest<GivenRectangle, WhenRectangle, ThenRectangle> {

    @Test
    public void user_creates_a_rectangle() {
        given().a_rectangle_tool_is_active();
        when().the_user_drags_from_to(0, 0, 100, 50);
        then().a_rectangle_exists_with_size(100, 50);
    }

    @Test
    public void user_resizes_a_rectangle() {
        given().a_rectangle_exists_with_bounds(0, 0, 50, 50);
        when().the_user_drags_from_to(0, 0, 200, 100);
        then().a_rectangle_exists_with_size(200, 100);
    }

    @Test
    public void rectangle_has_a_minimum_size() {
        given().a_rectangle_tool_is_active();
        when().the_user_drags_from_to(10, 10, 10, 10);
        then().the_rectangle_has_a_minimum_size();
    }
}
