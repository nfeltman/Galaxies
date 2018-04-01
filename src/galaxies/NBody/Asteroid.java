package galaxies.NBody;

import galaxies.util.MovingPoint;

public class Asteroid {
    public final MovingPoint point;
    public final double mass;

    public Asteroid(MovingPoint point, double mass){
        this.point = point;
        this.mass = mass;
    }
}
