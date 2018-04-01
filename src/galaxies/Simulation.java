package galaxies;

import javafx.scene.canvas.GraphicsContext;

public interface Simulation<State> {
    public State init();
    public State stepForward (State s, double dt);
    public void draw(State s, GraphicsContext gc, double width, double height);
}
