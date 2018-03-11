import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class SolarObject {
    // other parameters (size, color, name)

    private final List<SolarObject> satellites;

    public SolarObject (List<SolarObject> satellites) {
        this.satellites = satellites;
    }

    public void draw(GraphicsContext gc) {

    }
}
