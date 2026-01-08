package rectangleToolTests.JGiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.figure.RectangleFigure;

public class GivenRectangle extends Stage<GivenRectangle> {

    @ProvidedScenarioState
    RectangleFigure rectangle;

    public GivenRectangle a_rectangle_tool_is_active() {
        rectangle = new RectangleFigure();
        return this;
    }

    public GivenRectangle a_rectangle_exists_with_bounds(
            double x, double y, double width, double height) {

        rectangle = new RectangleFigure(x, y, width, height);
        return this;
    }
}
