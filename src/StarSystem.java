import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class StarSystem implements Simulation<StarSystem.SystemState> {
    public static final double G = 5000;

    @Override
    public SystemState init() {
        ArrayList<MovingPoint> asteroids = new ArrayList<MovingPoint>();
        for (int i = 0; i < 20; i++){
            asteroids.add(new MovingPoint(new Vector2d(Math.random()*1500, Math.random()*1200),
                    new Vector2d((Math.random()-0.5)*200, (Math.random()-0.5)*200)));
        }

        return new SystemState(0, asteroids);
    }

    @Override
    public SystemState stepForward(SystemState state, double deltaT) {
        ArrayList<MovingPoint> nextAsteroids = new ArrayList<MovingPoint>();
        for (MovingPoint ast : state.asteroids){
            SolarObject sun = constructSolarSystem();
            Vector2d center = new Vector2d(750, 600);
            Vector2d calcGravity = sun.calcGravity(ast.location, deltaT, center);
            MovingPoint nextAst = ast.stepForceExact(deltaT-state.timeElapsed, calcGravity);
            if (Vector2d.distance(nextAst.location, center) > 50)
                nextAsteroids.add(nextAst);
        }
        return new SystemState(deltaT, nextAsteroids);
    }

    @Override
    public void draw(SystemState state, GraphicsContext gc, double width, double height) {
        Vector2d center = new Vector2d(width / 2, height / 2);
        gc.setTextAlign(TextAlignment.CENTER);

        // background image clears canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);

        SolarObject sun = constructSolarSystem();
        sun.draw(gc, state.timeElapsed, center);
        gc.setFill(Color.GRAY);
        for (MovingPoint ast : state.asteroids)  gc.fillOval(ast.location.x, ast.location.y, 3, 3);

    }

    public static SolarObject constructSolarSystem(){

        List<SolarObject> planets = new ArrayList<>();

        // mercury
        SolarObject mercury = new SolarObject(new ArrayList<>(),15, Color.ORANGERED, "Mercury", 20, 1.4, 250, 120);
        planets.add(mercury);

        // venus
        SolarObject venus = new SolarObject(new ArrayList<>(),20, Color.ORANGE, "Venus", 48, 1.1, 500, 150);
        planets.add(venus);

        // earth
        int earthDistance = 200;
        int earthSize = 20;

        List<SolarObject> moonOrbiters = new ArrayList<>();
        moonOrbiters.add(new SolarObject(new ArrayList<>(), 1, Color.WHITE, "", 0, 26, 1000, 5));
        List<SolarObject> earthMoons = new ArrayList<>();
        earthMoons.add(new SolarObject(moonOrbiters, 5, Color.LIGHTGRAY, "", 12.5, 13, 1000, earthSize));

        SolarObject earth = new SolarObject(earthMoons, earthSize, Color.BLUE, "Earth", 50, 1, 1000, earthDistance);
        planets.add(earth);

        // mars
        List<SolarObject> marsMoons = new ArrayList<>();
        marsMoons.add(new SolarObject(new ArrayList<>(), 1, Color.GRAY, "", 0, 16, 0, 16));
        marsMoons.add(new SolarObject(new ArrayList<>(), 1, Color.GRAY, "", 0, 14, 0, 16));

        SolarObject mars = new SolarObject(marsMoons, 16, Color.DARKRED, "Mars", 30, 0.95, 0, 300);
        planets.add(mars);

        // saturn
        List<SolarObject> saturnMoons = new ArrayList<>();
        saturnMoons.add(new SolarObject(new ArrayList<>(), 45, Color.LIGHTGOLDENRODYELLOW, "", 0, 0, 1200, 0));
        saturnMoons.add(new SolarObject(new ArrayList<>(), 43, Color.BLACK, "Saturn", 0, 0, 1200, 0));

        SolarObject saturn = new SolarObject(saturnMoons, 25, Color.ORANGE, "", 100, 0.85, 1200, 550);
        planets.add(saturn);

        // asteroid

        // sun
        return new SolarObject(planets, 100, Color.YELLOW, "", 1000, 0, 0, 0);
    }

    public static class SystemState {
        public double timeElapsed;
        public ArrayList<MovingPoint> asteroids;

        public SystemState(double timeElapsed, ArrayList<MovingPoint> asteroids){
            this.timeElapsed = timeElapsed;
            this.asteroids = asteroids;
        }
    }

    public static Vector2d gravitationalAcceleration(Vector2d attracteeLocation, Vector2d attractorLocation, double attractorMass) {
        double distance = Vector2d.distance(attractorLocation, attracteeLocation);
        double magnitude = (G * attractorMass) / (distance*distance);
        Vector2d direction = attractorLocation.subtract(attracteeLocation).scale(1/distance);
        return direction.scale(magnitude);
    }
}