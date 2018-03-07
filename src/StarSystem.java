import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StarSystem {

    public static void drawSolarSystem(GraphicsContext gc, double t, double width, double height) {

        Vector2d center = new Vector2d(width / 2, height / 2);


        // background image clears canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);

        // sun
        gc.setFill(Color.YELLOW);
        gc.fillOval( center.x-50, center.y-50, 100, 100);

        // mercury
        gc.setFill(Color.ORANGERED);
        drawPlanet(1.4, 15, 120, gc, t, center);

        // venus
        gc.setFill(Color.ORANGE);
        drawPlanet(1.1, 20, 150, gc, t, center);

        // earth
        int earthDistance = 200;
        int earthSize = 20;
        gc.setFill(Color.BLUE);
        drawPlanet(1, earthSize, earthDistance, gc, t, center);
        gc.setFill(Color.LIGHTGRAY);
        drawMoon(13, 1, 5, earthSize, earthDistance, gc, t, center);

        // mars
        gc.setFill(Color.DARKRED);
        drawPlanet(0.95, 16, 300, gc, t, center);
        gc.setFill(Color.GRAY);
        drawMoon(20, 0.95, 1, 16, 300, gc, t, center);
        drawMoon(17, 0.95, 1, 14, 300, gc, t, center);

    }

    public static void drawPlanet(double speed, double size, double distance, GraphicsContext gc, double t, Vector2d center){
        Vector2d circlePos = Vector2d.onCircle(speed*t).scale(distance).add(center);
        gc.fillOval( circlePos.x-(size/2), circlePos.y-(size/2), size, size );
    }

    public static void drawMoon(double moonSpeed, double planetSpeed, double moonSize, double moonDistance, double planetDistance, GraphicsContext gc, double t, Vector2d planetCenter){
        Vector2d planetPos = Vector2d.onCircle(planetSpeed*t).scale(planetDistance).add(planetCenter);
        Vector2d moonPos = Vector2d.onCircle(moonSpeed*t).scale(moonDistance).add(planetPos);
        gc.fillOval( moonPos.x-(moonSize/2), moonPos.y-(moonSize/2), moonSize, moonSize );
    }
}
