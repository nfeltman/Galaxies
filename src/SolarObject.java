import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class SolarObject {
    // other parameters (size, color, name, mass)

    private final List<SolarObject> satellites;
    private int size;
    private double speed;
    private int offset;
    private int distance;
    private Color color;
    private String name;
    private double mass;

    public SolarObject (List<SolarObject> satellites, int size, Color color, String name, double mass, double speed, int offset, int distance) {
        this.satellites = satellites;
        this.size = size;
        this.speed = speed;
        this.offset = offset;
        this.distance = distance;
        this.color = color;
        this.name = name;
        this.mass = mass;
    }

    public void draw(GraphicsContext gc, double t, Vector2d center) {
        Vector2d circlePos = Vector2d.onCircle(speed*(t+offset)).scale(distance).add(center);
        for (SolarObject so : satellites) so.draw(gc, t, circlePos);
        gc.setFill(color);
        gc.fillOval( circlePos.x-(size/2), circlePos.y-(size/2), size, size );
        gc.setFill(Color.WHITE);
        gc.fillText(name, circlePos.x, circlePos.y-size);
    }
}
