package galaxies;

import galaxies.NBody.NBodySimulation;
import galaxies.solar.SolarSimulation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Mandelbrot set with JavaFX.
 */
public class Launcher extends Application {
    // Size of the canvas for the Mandelbrot set
    private static final int CANVAS_WIDTH = 1500;
    private static final int CANVAS_HEIGHT = 1000;
    long lastNanoTime;
    Simulation<NBodySimulation.SystemState> sim = new NBodySimulation();
    NBodySimulation.SystemState state;

    @Override
    public void start(Stage theStage) {
        theStage.setTitle( "Simple Solar System" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( CANVAS_WIDTH, CANVAS_HEIGHT );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        lastNanoTime = System.nanoTime();
        state = sim.init(CANVAS_WIDTH, CANVAS_HEIGHT);

        new AnimationTimer() {
            public void handle(long currentNanoTime){
                state = sim.stepForward(state, (currentNanoTime - lastNanoTime) / 1000000000.0);
                lastNanoTime = currentNanoTime;
                sim.draw(state, gc, CANVAS_WIDTH, CANVAS_HEIGHT);
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}