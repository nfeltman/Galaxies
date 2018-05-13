package galaxies_and_shooter.NBody;

import galaxies_and_shooter.util.MovingPoint;

public class Asteroid {
    public final MovingPoint point;
    public final double mass;
    public final int size; // diameter

    public Asteroid(MovingPoint point, double mass, int size){
        this.point = point;
        this.mass = mass;
        this.size = size;
    }
}
