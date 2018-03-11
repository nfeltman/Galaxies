import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class StarSystem {

    public static void drawSolarSystem(GraphicsContext gc, double t, double width, double height) {

        Vector2d center = new Vector2d(width / 2, height / 2);
        gc.setTextAlign(TextAlignment.CENTER);

        // background image clears canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);

        SolarObject sun = constructSolarSystem();
        sun.draw(gc, t, center);
    }

    public static SolarObject constructSolarSystem(){

        List<SolarObject> planets = new ArrayList<>();

        // mercury
        SolarObject mercury = new SolarObject(new ArrayList<>(),15, Color.ORANGERED, "Mercury", 0, 1.4, 250, 120);
        planets.add(mercury);

        // venus
        SolarObject venus = new SolarObject(new ArrayList<>(),20, Color.ORANGE, "Venus", 0, 1.1, 500, 150);
        planets.add(venus);

        // earth
        int earthDistance = 200;
        int earthSize = 20;

        List<SolarObject> moonOrbiters = new ArrayList<>();
        moonOrbiters.add(new SolarObject(new ArrayList<>(), 1, Color.WHITE, "", 0, 26, 1000, 5));
        List<SolarObject> earthMoons = new ArrayList<>();
        earthMoons.add(new SolarObject(moonOrbiters, 5, Color.LIGHTGRAY, "", 0, 13, 1000, earthSize));

        SolarObject earth = new SolarObject(earthMoons, earthSize, Color.BLUE, "Earth", 0, 1, 1000, earthDistance);
        planets.add(earth);

        // mars
        List<SolarObject> marsMoons = new ArrayList<>();
        marsMoons.add(new SolarObject(new ArrayList<>(), 1, Color.GRAY, "", 0, 16, 0, 16));
        marsMoons.add(new SolarObject(new ArrayList<>(), 1, Color.GRAY, "", 0, 14, 0, 16));

        SolarObject mars = new SolarObject(marsMoons, 16, Color.DARKRED, "Mars", 0, 0.95, 0, 300);
        planets.add(mars);

        // saturn
        List<SolarObject> saturnMoons = new ArrayList<>();
        saturnMoons.add(new SolarObject(new ArrayList<>(), 45, Color.LIGHTGOLDENRODYELLOW, "", 0, 0, 1200, 0));
        saturnMoons.add(new SolarObject(new ArrayList<>(), 43, Color.BLACK, "Saturn", 0, 0, 1200, 0));

        SolarObject saturn = new SolarObject(saturnMoons, 25, Color.ORANGE, "", 0, 0.85, 1200, 550);
        planets.add(saturn);

        // sun
        return new SolarObject(planets, 100, Color.YELLOW, "", 0, 0, 0, 0);
    }
}
