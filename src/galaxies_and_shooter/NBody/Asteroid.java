package galaxies_and_shooter.NBody;

import galaxies_and_shooter.util.MovingPoint;

public class Asteroid {
    public final MovingPoint point;
    public final double mass;
    public final int charge;
    public final int size; // diameter

    public Asteroid(MovingPoint point, double mass, int charge, int size){
        this.point = point;
        this.mass = mass;
        this.charge = charge;
        this.size = size;
    }
}
