package galaxies_and_shooter.shooter;

import galaxies_and_shooter.Simulation;
import galaxies_and_shooter.util.MovingPoint;
import galaxies_and_shooter.util.Vector2d;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ShooterSim implements Simulation<ShooterState>{

    public ShooterState init(int width, int height) {
        return new ShooterState(new Vector2d(width/2, height/2), 2, false, false, false, false, new ArrayList<MovingPoint>());
    }


    public ShooterState stepForward(ShooterState s, double dt, ArrayList<KeyEvent> keyPresses, int width, int height) {
        Vector2d nextLoc = s.getLocation();
        for (KeyEvent k : keyPresses){
            s.wPressed = isPressed(KeyCode.W, k, s.wPressed);
            s.aPressed = isPressed(KeyCode.A, k, s.aPressed);
            s.sPressed = isPressed(KeyCode.S, k, s.sPressed);
            s.dPressed = isPressed(KeyCode.D, k, s.dPressed);
        }
        if (s.wPressed) nextLoc = new Vector2d(nextLoc.x, nextLoc.y - s.speed);
        if (s.aPressed) nextLoc = new Vector2d(nextLoc.x - s.speed, nextLoc.y );
        if (s.sPressed) nextLoc = new Vector2d(nextLoc.x, nextLoc.y + s.speed);
        if (s.dPressed) nextLoc = new Vector2d(nextLoc.x + s.speed, nextLoc.y);

        ArrayList<MovingPoint> nextBullets = new ArrayList();
        for (MovingPoint bullet : s.bullets){
            nextBullets.add(bullet.step(dt));
        }
        if (Math.random() < 0.04){
            Vector2d nextLocation;
            Vector2d nextVelocity;
            double rand = Math.random();
            if (rand < 0.25){
                nextLocation = new Vector2d(0, (int) (Math.random()*height)); // left
                if (nextLocation.y < height / 2) nextVelocity = new Vector2d((Math.random()*3)+5, (Math.random()*3)+5);
                else nextVelocity = new Vector2d((Math.random()*3)+5, (Math.random()*-3)-5);
            } else if (rand < 0.5){
                nextLocation = new Vector2d(width, (int) (Math.random()*height)); // right
                if (nextLocation.y < height / 2) nextVelocity = new Vector2d((Math.random()*-3)-5, (Math.random()*3)+5);
                else nextVelocity = new Vector2d((Math.random()*-3)-5, (Math.random()*-3)-5);
            } else if (rand < 0.75){
                nextLocation = new Vector2d((int) (Math.random()*width), 0); // top
                if (nextLocation.x > width / 2) nextVelocity = new Vector2d((Math.random()*-3)-5, (Math.random()*3)+5);
                else nextVelocity = new Vector2d((Math.random()*+3)+5, (Math.random()*+3)+5);
            }
            else {
                nextLocation = new Vector2d((int) (Math.random()*width), height); // bottom
                if (nextLocation.x > width / 2) nextVelocity = new Vector2d((Math.random()*-3)-5, (Math.random()*-3)-5);
                else nextVelocity = new Vector2d((Math.random()*+3)+5, (Math.random()*-3)-5);
            }
        }
        return new ShooterState(nextLoc, s.speed, s.wPressed, s.aPressed, s.sPressed, s.dPressed, nextBullets);
    }

    private boolean isPressed(KeyCode code, KeyEvent event, boolean isCurrentlyPressed){
        if (event.getCode().equals(code)){
            if (event.getEventType() == KeyEvent.KEY_PRESSED) return true;
            if (event.getEventType() == KeyEvent.KEY_RELEASED) return false;
        }
        return isCurrentlyPressed;
    }


    public void draw(ShooterState s, GraphicsContext gc, double width, double height) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        gc.setFill(Color.BLACK);
        gc.fillOval(s.getLocation().x, s.getLocation().y, 10, 10);
        gc.setFill(Color.RED);
        for (MovingPoint bullet : s.bullets){
            gc.fillOval(bullet.location.x, bullet.location.y, 3, 3);
        }
    }
}
