package galaxies_and_shooter;

import galaxies_and_shooter.NBody.NBodySimulation;
import galaxies_and_shooter.shooter.ShooterSim;
import galaxies_and_shooter.shooter.ShooterState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Mandelbrot set with JavaFX.
 */
public class Launcher extends Application {
    // Size of the canvas for the Mandelbrot set
    private static final int CANVAS_WIDTH = 1500;
    private static final int CANVAS_HEIGHT = 1000;
    long lastNanoTime;
    ArrayList<KeyEvent> keyPressed = new ArrayList<KeyEvent>();
    Simulation<ShooterState> sim = new ShooterSim();
    ShooterState state;

    @Override
    public void start(Stage theStage) {
        theStage.setTitle( "Simple Solar System" );

        Group root = new Group();
        Scene scene = new Scene( root );
        theStage.setScene( scene );

        Canvas canvas = new Canvas( CANVAS_WIDTH, CANVAS_HEIGHT );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        lastNanoTime = System.nanoTime();
        state = sim.init(CANVAS_WIDTH, CANVAS_HEIGHT);

        scene.setOnKeyPressed(e -> keyPressed.add(e));
        scene.setOnKeyReleased(e -> keyPressed.add(e));

        new AnimationTimer() {
            public void handle(long currentNanoTime){
                long actualDT = currentNanoTime - lastNanoTime;
                lastNanoTime = currentNanoTime;

                state = sim.stepForward(state, actualDT/1000000000.0, keyPressed, CANVAS_WIDTH, CANVAS_HEIGHT);
                keyPressed = new ArrayList<KeyEvent>();

                // draw
                sim.draw(state, gc, CANVAS_WIDTH, CANVAS_HEIGHT);
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}