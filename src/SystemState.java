import java.util.ArrayList;

public class SystemState {
    public double timeElapsed;
    public ArrayList<MovingPoint> asteroids;

    public SystemState(double timeElapsed, ArrayList<MovingPoint> asteroids){
        this.timeElapsed = timeElapsed;
        this.asteroids = asteroids;
    }

    public SystemState() {
        timeElapsed = 0;
        asteroids = new ArrayList<MovingPoint>();
        for (int i = 0; i < 20; i++){
            asteroids.add(new MovingPoint(new Vector2d(Math.random()*1500, Math.random()*1200),
                    new Vector2d((Math.random()-0.5)*100, (Math.random()-0.5)*100)));
        }
    }
}
