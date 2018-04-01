package galaxies.solar;

import galaxies.Simulation;
import galaxies.util.MovingPoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import galaxies.util.Vector2d;

import java.util.ArrayList;

public class SolarSimulation implements Simulation<SolarSimulation.SystemState> {

    @Override
    public SystemState init(int width, int height) {
        ArrayList<MovingPoint> asteroids = new ArrayList<MovingPoint>();
        Vector2d solarCenter = new Vector2d(width/2.0, height/2.0);
        for (int i = 0; i < 20; i++){
            asteroids.add(new MovingPoint(new Vector2d(Math.random()*1500, Math.random()*1200),
                    new Vector2d((Math.random()-0.5)*200, (Math.random()-0.5)*200)));
        }

        return new SystemState(CelestialObject.constructSolarSystem(), solarCenter,0, asteroids);
    }

    @Override
    public SystemState stepForward(SystemState state, double deltaT) {
        ArrayList<MovingPoint> nextAsteroids = new ArrayList<MovingPoint>();
        for (MovingPoint ast : state.asteroids){
            Vector2d calcGravity = state.solarSystem.calcGravity(ast.location, deltaT, state.solarCenter);
            MovingPoint nextAst = ast.stepForceExact(deltaT-state.timeElapsed, calcGravity);
            if (Vector2d.distance(nextAst.location, state.solarCenter) > 50)
                nextAsteroids.add(nextAst);
        }
        return new SystemState(state.solarSystem, state.solarCenter, deltaT, nextAsteroids);
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