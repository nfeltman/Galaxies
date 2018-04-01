package galaxies;

import galaxies.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

public interface Simulation<State> {
    public State init(int width, int height);
    public State stepForward (State s, double dt);
    public void draw(State s, GraphicsContext gc, double width, double height);
}
