import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class StarSystem {

    public static void drawSolarSystem(GraphicsContext gc, double t, double width, double height) {

        Vector2d center = new Vector2d(width / 2, height / 2);
        gc.setTextAlign(TextAlignment.CENTER);


        // background image clears canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);

        // sun
        gc.setFill(Color.YELLOW);
        gc.fillOval( center.x-50, center.y-50, 100, 100);

        // mercury
        gc.setFill(Color.ORANGERED);
        drawPlanet(1.4, 15, 120, 250, "Mercury", gc, t, center);

        // venus
        gc.setFill(Color.ORANGE);
        drawPlanet(1.1, 20, 150, 500, "Venus", gc, t, center);

        // earth
        int earthDistance = 200;
        int earthSize = 20;
        gc.setFill(Color.BLUE);
        drawPlanet(1, earthSize, earthDistance, 1000, "Earth", gc, t, center);
        gc.setFill(Color.LIGHTGRAY);
        drawMoon(13, 1, 5, earthSize, earthDistance,1000, gc, t, center);
        gc.setFill(Color.WHITE);
        drawMoonOrbiter(13, 1, 26, earthSize, earthDistance, 6, 1000, gc, t, center);

        // mars
        gc.setFill(Color.DARKRED);
        drawPlanet(0.95, 16, 300, 0, "Mars", gc, t, center);
        gc.setFill(Color.GRAY);
        drawMoon(20, 0.95, 1, 16, 300, 0, gc, t, center);
        drawMoon(17, 0.95, 1, 14, 300, 0, gc, t, center);

        // saturn
        gc.setFill(Color.LIGHTGOLDENRODYELLOW);
        drawPlanet(0.95, 35, 550, 1500, "Saturn", gc, t, center);
        gc.setFill(Color.BLACK);
        drawPlanet(0.95, 33, 550, 1500, "", gc, t, center);
        gc.setFill(Color.ORANGE);
        drawPlanet(0.95, 15, 550, 1500, "", gc, t, center);

    }

    public static void drawPlanet(double speed, double size, double distance, double offset, String name, GraphicsContext gc, double t, Vector2d center){
        Vector2d circlePos = Vector2d.onCircle(speed*(t+offset)).scale(distance).add(center);
        gc.fillOval( circlePos.x-(size/2), circlePos.y-(size/2), size, size );
        gc.setFill(Color.WHITE);
        gc.fillText(name, circlePos.x, circlePos.y-size);
    }

    public static void drawMoon(double moonSpeed, double planetSpeed, double moonSize, double moonDistance, double planetDistance, double planetOffset, GraphicsContext gc, double t, Vector2d planetCenter){
        Vector2d planetPos = Vector2d.onCircle(planetSpeed*(t+planetOffset)).scale(planetDistance).add(planetCenter);
        Vector2d moonPos = Vector2d.onCircle(moonSpeed*t).scale(moonDistance).add(planetPos);
        gc.fillOval( moonPos.x-(moonSize/2), moonPos.y-(moonSize/2), moonSize, moonSize );
    }

    public static void drawMoonOrbiter(double moonSpeed, double planetSpeed, double orbiterSpeed, double moonDistance, double planetDistance, double orbiterDistance, double planetOffset, GraphicsContext gc, double t, Vector2d planetCenter){
        Vector2d planetPos = Vector2d.onCircle(planetSpeed*(t+planetOffset)).scale(planetDistance).add(planetCenter);
        Vector2d moonPos = Vector2d.onCircle(moonSpeed*t).scale(moonDistance).add(planetPos);
        Vector2d orbiterPos = Vector2d.onCircle(orbiterSpeed*t).scale(orbiterDistance).add(moonPos);
        gc.fillOval( orbiterPos.x, orbiterPos.y, 1, 1 );
    }
}
