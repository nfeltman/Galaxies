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
        for (int i = 0; i < 125; i++){
            asteroids.add(new Asteroid(
                    new MovingPoint(
                            new Vector2d(Math.random()*1500, Math.random()*1200),
                    new Vector2d(Math.random()*10-5, Math.random()*10-5)),
                    0.1, Math.random() < 0.5 ? -1 : 1, 10));
        }
        return new SystemState(asteroids);
    }

    @Override
    public SystemState stepForward(SystemState s, double dt) {
        ArrayList<Asteroid> nextAsteroids = new ArrayList<Asteroid>();
        for (Asteroid ast : s.asteroids){
            Vector2d gravityForce = new Vector2d(0, 0);
            Vector2d electromagneticForce = new Vector2d(0, 0);
            for (Asteroid otherAst : s.asteroids){
                if (otherAst != ast){
                    Vector2d gravity = Physics.gravitationalAcceleration(ast.point.location, otherAst.point.location, otherAst.mass);
                    gravityForce = gravityForce.add(gravity);
                    Vector2d electromagnetic = Physics.gravitationalAcceleration(ast.point.location, otherAst.point.location, otherAst.mass);
                    electromagnetic = electromagnetic.scale((ast.charge * otherAst.charge) * -20);
                    electromagneticForce = electromagneticForce.add(electromagnetic);
                }
            }
            MovingPoint nextPoint = ast.point.stepForceApprox(dt, gravityForce.add(electromagneticForce));
            Vector2d nextVelocity = nextPoint.velocity;

            for (Asteroid otherAst : s.asteroids){
                if (otherAst != ast){
                    if (Vector2d.distance(ast.point.location, otherAst.point.location) < ast.size)
                        nextVelocity = MovingPoint.elasticCollision(ast.point, otherAst.point, ast.mass, otherAst.mass);
                }
            }

            if (ast.point.location.x < ast.size / 2) nextVelocity = new Vector2d(Math.abs(nextVelocity.x), nextVelocity.y);
            if (ast.point.location.x > 1500 - (ast.size / 2)) nextVelocity = new Vector2d(-Math.abs(nextVelocity.x), nextVelocity.y);
            if (ast.point.location.y < ast.size / 2) nextVelocity = new Vector2d(nextVelocity.x, Math.abs(nextVelocity.y));
            if (ast.point.location.y > 1000 - (ast.size / 2)) nextVelocity = new Vector2d(nextVelocity.x, -Math.abs(nextVelocity.y));
            Asteroid nextAst = new Asteroid(new MovingPoint(nextPoint.location, nextVelocity), ast.mass, ast.charge, ast.size);
            nextAsteroids.add(nextAst);
        }

        return new SystemState(nextAsteroids);
    }

    @Override
    public void draw(SystemState s, GraphicsContext gc, double width, double height) {
        gc.setTextAlign(TextAlignment.CENTER);

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        for (Asteroid ast : s.asteroids){
            switch (ast.charge) {
                case 1 : gc.setFill(Color.RED); break;
                case -1: gc.setFill(Color.BLUE); break;
                default: gc.setFill(Color.GREEN);
            }
            gc.fillOval(ast.point.location.x - (ast.size / 2), ast.point.location.y - (ast.size / 2), ast.size, ast.size);
        }


        gc.setFill(Color.WHITE);

        double kineticEnergy = kineticEnergy(s);
        double potentialEnergy = potentialEnergy(s);
        double totalEnergy = kineticEnergy + potentialEnergy;

        gc.fillText("Kinetic energy: " + String.valueOf(kineticEnergy), 1350, 930);
        gc.fillText("Potential energy: " + String.valueOf(potentialEnergy), 1350, 950);
        gc.fillText("Total energy: " + String.valueOf(totalEnergy), 1350, 970);
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
