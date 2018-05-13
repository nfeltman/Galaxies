package galaxies_and_shooter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public interface Simulation<State> {
    public State init(int width, int height);
    public State stepForward (State s, double dt, ArrayList<KeyEvent> keyPresses, int width, int height);
    public void draw(State s, GraphicsContext gc, double width, double height);
}
