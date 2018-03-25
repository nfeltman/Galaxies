public class Vector2d {
    public final double x;
    public final double y;

    public Vector2d(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2d add(Vector2d vector){
        return new Vector2d(x+vector.x, y+vector.y);
    }

    public static Vector2d onCircle(double theta){
        return new Vector2d(Math.cos(theta), Math.sin(theta));
    }

    public Vector2d scale (double scalar) {
        return new Vector2d(x * scalar, y * scalar);
    }
    // calculate the acceleration due to gravity of the attractor on the attractee
    public static Vector2d gravitationalAcceleration(Vector2d attracteeLocation, Vector2d attractorLocation, double attractorMass) {
        return new Vector2d((9.8*attractorMass)/Math.abs(attractorLocation.x-attracteeLocation.x), (9.8*attractorMass)/Math.abs(attractorLocation.y-attracteeLocation.y));
    }
    public String toString(){
        return "x: " + x + "\ny: " + y;
    }
}
