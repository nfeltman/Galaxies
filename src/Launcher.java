import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Mandelbrot set with JavaFX.
 */
public class Launcher extends Application {
    // Size of the canvas for the Mandelbrot set
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 600;

    @Override
    public void start(Stage theStage) {
        theStage.setTitle( "Simple Solar System" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( CANVAS_WIDTH, CANVAS_HEIGHT );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                StarSystem.drawSolarSystem(gc,t,CANVAS_WIDTH,CANVAS_HEIGHT);
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}