package galaxies_and_shooter.util;

public class Physics {

    public static final double G = 5000;

    public static Vector2d gravitationalAcceleration(Vector2d attracteeLocation, Vector2d attractorLocation, double attractorMass) {
        double distance = Vector2d.distance(attractorLocation, attracteeLocation);
        double magnitude = (G * attractorMass) / (distance*distance);
        Vector2d direction = attractorLocation.subtract(attracteeLocation).scale(1/distance);
        return direction.scale(magnitude);
    }

    public static double potentialEnergy(Vector2d loc1, Vector2d loc2, double mass1, double mass2){
        return (G)*((mass1*mass2)/Vector2d.distance(loc1, loc2));
    }

    public static double kineticEnergy(MovingPoint point, double mass){
        double speed = (point.velocity.x*point.velocity.x) + (point.velocity.y*point.velocity.y);
        return (mass*speed)/2;
    }
}
