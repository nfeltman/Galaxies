import java.util.ArrayList;

public class SystemState {
    public double timeElapsed;
    public ArrayList<MovingPoint> asteroids;

    public SystemState(double timeElapsed, ArrayList<MovingPoint> asteroids){
        this.timeElapsed = timeElapsed;
        this.asteroids = asteroids;
        // Destroys asteroids when they collide with the sun
        for (int i = 0; i < asteroids.size(); i++){
            if (Vector2d.distance(asteroids.get(i).location, new Vector2d(750, 600)) <= 50)
                asteroids.remove(i);
        }
    }

    public SystemState() {
        timeElapsed = 0;
        asteroids = new ArrayList<MovingPoint>();
        for (int i = 0; i < 20; i++){
            asteroids.add(new MovingPoint(new Vector2d(Math.random()*1500, Math.random()*1200),
                    new Vector2d((Math.random()-0.5)*200, (Math.random()-0.5)*200)));
        }
    }
}
