package galaxies_and_shooter.solar;

import galaxies_and_shooter.Simulation;
import galaxies_and_shooter.util.MovingPoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import galaxies_and_shooter.util.Vector2d;

import java.util.ArrayList;

public class SolarSimulation implements Simulation<SolarSimulation.SystemState> {

    @Override
    public SystemState init(int width, int height) {
        ArrayList<MovingPoint> asteroids = new ArrayList<MovingPoint>();
        Vector2d solarCenter = new Vector2d(width/2.0, height/2.0);
        for (int i = 0; i < 200; i++){
            asteroids.add(new MovingPoint(new Vector2d(Math.random()*1500, Math.random()*1200),
                    new Vector2d((Math.random()-0.5)*200, (Math.random()-0.5)*200)));
        }

        return new SystemState(CelestialObject.constructSolarSystem(), solarCenter,0, asteroids);
    }

    @Override
    public SystemState stepForward(SystemState state, double deltaT, ArrayList<KeyEvent> events) {
        // System.out.println("Taking step of size " + deltaT + ", and current time is " + state.timeElapsed);

        ArrayList<MovingPoint> nextAsteroids = new ArrayList<MovingPoint>();
        for (MovingPoint ast : state.asteroids){
            Vector2d calcGravity = state.solarSystem.calcGravity(ast.location, state.timeElapsed, state.solarCenter);
            MovingPoint nextAst = ast.stepForceExact(deltaT, calcGravity);
            if (Vector2d.distance(nextAst.location, state.solarCenter) > 50)
                nextAsteroids.add(nextAst);
        }
        return new SystemState(state.solarSystem, state.solarCenter, state.timeElapsed + deltaT, nextAsteroids);
    }

    @Override
    public void draw(SystemState state, GraphicsContext gc, double width, double height) {
        gc.setTextAlign(TextAlignment.CENTER);

        // background image clears canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        state.solarSystem.draw(gc, state.timeElapsed, state.solarCenter);
        gc.setFill(Color.GRAY);
        for (MovingPoint ast : state.asteroids) gc.fillOval(ast.location.x, ast.location.y, 3, 3);

    }

    public static class SystemState {
        public final CelestialObject solarSystem;
        public final Vector2d solarCenter;
        public final double timeElapsed;
        public final ArrayList<MovingPoint> asteroids;

        public SystemState(CelestialObject solarSystem, Vector2d solarCenter, double timeElapsed, ArrayList<MovingPoint> asteroids){
            this.solarSystem = solarSystem;
            this.solarCenter = solarCenter;
            this.timeElapsed = timeElapsed;
            this.asteroids = asteroids;
        }
    }

}