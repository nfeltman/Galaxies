package galaxies.NBody;

import galaxies.Simulation;
import galaxies.util.MovingPoint;
import galaxies.util.Physics;
import galaxies.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class NBodySimulation implements Simulation<NBodySimulation.SystemState> {
    @Override
    public SystemState init(int width, int height) {
        ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
        for (int i = 0; i < 200; i++){
            asteroids.add(new Asteroid(
                    new MovingPoint(
                            new Vector2d(Math.random()*1500, Math.random()*1200),
                    new Vector2d(0, 0)),
                    0.01));
        }
        return new SystemState(asteroids);
    }

    @Override
    public SystemState stepForward(SystemState s, double dt) {
        ArrayList<Asteroid> nextAsteroids = new ArrayList<Asteroid>();
        for (Asteroid ast : s.asteroids){
            Vector2d gravityForce = new Vector2d(0, 0);
            for (Asteroid otherAst : s.asteroids){
                if (otherAst != ast){
                    Vector2d result = Physics.gravitationalAcceleration(ast.point.location, otherAst.point.location, otherAst.mass);
                    gravityForce = gravityForce.add(result);
                }
            }
            Asteroid nextAst = new Asteroid(ast.point.stepForceExact(dt, gravityForce), ast.mass);
            nextAsteroids.add(nextAst);
        }

        return new SystemState(nextAsteroids);
    }

    @Override
    public void draw(SystemState s, GraphicsContext gc, double width, double height) {
        gc.setTextAlign(TextAlignment.CENTER);

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        gc.setFill(Color.GRAY);
        for (Asteroid ast : s.asteroids) gc.fillOval(ast.point.location.x, ast.point.location.y, 3, 3);

        gc.setFill(Color.WHITE);
        gc.fillText("Kinetic energy: " + String.valueOf(kineticEnergy(s)), 1350, 950);
        gc.fillText("Potential energy: " + String.valueOf(potentialEnergy(s)), 1350, 970);
    }

    public double kineticEnergy(SystemState s){
        double result = 0.0;
        for (Asteroid ast : s.asteroids){
            result += Physics.kineticEnergy(ast.point, ast.mass);
        }
        return result;
    }

    public double potentialEnergy(SystemState s){
        double result = 0.0;
        for (int i = 0; i < s.asteroids.size() - 1; i++){
            for (int j = i + 1; j < s.asteroids.size(); j++){
                result += Physics.potentialEnergy(s.asteroids.get(i).point.location, s.asteroids.get(j).point.location, s.asteroids.get(i).mass, s.asteroids.get(j).mass);
            }
        }
        return result;
    }

    public static class SystemState{
        public final ArrayList<Asteroid> asteroids;
        public SystemState(ArrayList<Asteroid> asteroids){
            this.asteroids = asteroids;
        }
    }
}
