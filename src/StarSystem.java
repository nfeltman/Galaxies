import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StarSystem {

    public static void drawSolarSystem(GraphicsContext gc, double t, double width, double height) {

        double cx = width / 2;
        double cy = height / 2;

        double x = cx + 200 * Math.cos(t);
        double y = cy + 200 * Math.sin(t);

        // background image clears canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);

        // sun
        gc.setFill(Color.YELLOW);
        gc.fillOval( cx-50, cy-50, 100, 100 );

        // earth
        gc.setFill(Color.BLUE);
        gc.fillOval( x-10, y-10, 20, 20 );
    }
}
