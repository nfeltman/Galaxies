package galaxies.util;

public class Physics {

    public static final double G = 5000;

    public static Vector2d gravitationalAcceleration(Vector2d attracteeLocation, Vector2d attractorLocation, double attractorMass) {
        double distance = Vector2d.distance(attractorLocation, attracteeLocation);
        double magnitude = (G * attractorMass) / (distance*distance);
        Vector2d direction = attractorLocation.subtract(attracteeLocation).scale(1/distance);
        return direction.scale(magnitude);
    }
}
