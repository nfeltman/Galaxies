package galaxies_and_shooter.shooter;

import galaxies_and_shooter.util.Vector2d;

public class ShooterState {
    private Vector2d location;
    public double speed;
    boolean wPressed;
    boolean aPressed;
    boolean sPressed;
    boolean dPressed;

    public ShooterState(){
        location = new Vector2d(0, 0);
    }

    public ShooterState(Vector2d location, double speed, boolean wPressed, boolean aPressed, boolean sPressed, boolean dPressed){
        this.location = location;
        this.speed = 2;
        this.wPressed = wPressed;
        this.aPressed = aPressed;
        this.sPressed = sPressed;
        this.dPressed = dPressed;
    }

    public Vector2d getLocation(){
        return location;
    }
}
