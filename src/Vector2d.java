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

    public Vector2d scale (double scalar){
        return new Vector2d(x*scalar, y*scalar);
    }
}
