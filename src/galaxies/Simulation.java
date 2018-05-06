package galaxies;

import galaxies.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public interface Simulation<State> {
    public State init(int width, int height);
    public State stepForward (State s, double dt, ArrayList<KeyEvent> keyPresses);
    public void draw(State s, GraphicsContext gc, double width, double height);
}
