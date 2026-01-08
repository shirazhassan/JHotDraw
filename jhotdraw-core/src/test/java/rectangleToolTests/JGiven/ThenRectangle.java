package rectangleToolTests.JGiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.assertj.core.api.Assertions;
import org.jhotdraw.draw.figure.RectangleFigure;

import java.awt.geom.Rectangle2D;

public class ThenRectangle extends Stage<ThenRectangle> {

    @ExpectedScenarioState
    RectangleFigure rectangle;

    public ThenRectangle a_rectangle_exists_with_size(
            double expectedWidth, double expectedHeight) {

        Rectangle2D bounds = rectangle.getBounds();

        Assertions.assertThat(bounds.getWidth())
                .as("Rectangle width")
                .isEqualTo(expectedWidth);

        Assertions.assertThat(bounds.getHeight())
                .as("Rectangle height")
                .isEqualTo(expectedHeight);

        return this;
    }

    public ThenRectangle the_rectangle_has_a_minimum_size() {
        Rectangle2D bounds = rectangle.getBounds();

        Assertions.assertThat(bounds.getWidth())
                .isGreaterThanOrEqualTo(0.1);

        Assertions.assertThat(bounds.getHeight())
                .isGreaterThanOrEqualTo(0.1);

        return this;
    }
}
