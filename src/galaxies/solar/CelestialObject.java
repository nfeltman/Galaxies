package galaxies.solar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import galaxies.util.Physics;
import galaxies.util.Vector2d;

public class CelestialObject {
    // other parameters (size, color, name, mass)

    private final CelestialObject[] satellites;
    private int size;
    private double speed;
    private int offset;
    private int distance;
    private Color color;
    private String name;
    private double mass;

    public CelestialObject(int size, Color color, String name, double mass, double speed, int offset, int distance, CelestialObject... satellites) {
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
        for (CelestialObject so : satellites) so.draw(gc, t, circlePos);
        gc.setFill(color);
        gc.fillOval( circlePos.x-(size/2), circlePos.y-(size/2), size, size);
        gc.setFill(Color.WHITE);
        gc.fillText(name, circlePos.x, circlePos.y-size);
    }

    public Vector2d calcGravity(Vector2d point, double t, Vector2d center){
        Vector2d circlePos = Vector2d.onCircle(speed*(t+offset)).scale(distance).add(center);
        Vector2d result = Physics.gravitationalAcceleration(point, circlePos, mass);
        for (CelestialObject so : satellites) result = result.add(so.calcGravity(point, t, circlePos));
        return result;
    }

    public static CelestialObject constructSolarSystem(){
        // mercury
        CelestialObject mercury = new CelestialObject(15, Color.ORANGERED, "Mercury", 20, 1.4, 250, 120);

        // venus
        CelestialObject venus = new CelestialObject(20, Color.ORANGE, "Venus", 48, 1.1, 500, 150);

        // earth
        int earthSize = 20;

        CelestialObject earth =
                new CelestialObject(earthSize, Color.BLUE, "Earth", 50, 1, 1000, 200,
                    new CelestialObject( 5, Color.LIGHTGRAY, "", 12.5, 13, 1000, earthSize,
                            new CelestialObject( 1, Color.WHITE, "", 0, 26, 1000, 5)));

        // mars
        CelestialObject mars = new CelestialObject(16, Color.DARKRED, "Mars", 30, 0.95, 0, 300,
                new CelestialObject(1, Color.GRAY, "", 0, 16, 0, 16),
                new CelestialObject(1, Color.GRAY, "", 0, 14, 0, 16));

        // saturn
        CelestialObject saturn = new CelestialObject(25, Color.ORANGE, "", 100, 0.85, 1200, 550,
                new CelestialObject(45, Color.LIGHTGOLDENRODYELLOW, "", 0, 0, 1200, 0),
                new CelestialObject(43, Color.BLACK, "Saturn", 0, 0, 1200, 0));

        // sun
        return new CelestialObject(100, Color.YELLOW, "", 1000, 0, 0, 0,
                mercury, venus, earth, mars, saturn);
    }
}
