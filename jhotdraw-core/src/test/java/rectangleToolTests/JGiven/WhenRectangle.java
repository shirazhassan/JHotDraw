package rectangleToolTests.JGiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.figure.RectangleFigure;

import java.awt.geom.Point2D;

public class WhenRectangle extends Stage<WhenRectangle> {

    @ExpectedScenarioState
    RectangleFigure rectangle;

    public WhenRectangle the_user_drags_from_to(
            double x1, double y1, double x2, double y2) {

        rectangle.setBounds(
                new Point2D.Double(x1, y1),
                new Point2D.Double(x2, y2)
        );
        return this;
    }
}
