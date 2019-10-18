import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{

    public static ObservableList<Node> child;
    //
    private static final String title = "JellyBeanci Vintage Computer Art";
    public static final int width = 800;
    public static final int height = 800;
    public static double speed = 0.5;
    private static Color backcolor = Color.rgb(51, 51, 51);

    private boolean perlin = false;
    private double xoff = Utils.getRandom(0, 123456); //nice random range

    private static Timeline update;

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane root = new Pane();
        child = root.getChildren();
        //
        for (int i = 0; i < 100; i++)
        {
            new Segment(i * 0.4); // i * 1.4
        }

        for (Segment segment : Segment.segments)
        {
            child.add(segment.getNode());
        }

        root.setOnKeyPressed(e -> {
            switch (e.getCode())
            {
                case F1:
                {
                    //PLAY
                    update.play();
                    break;
                }
                case F2:
                {
                    //PAUSE
                    update.pause();
                    break;
                }
                case F3:
                {
                    //Show Child Count
                    System.out.println("Child Count: " + child.size());
                    break;
                }
                case F9:
                {
                    // Slow Down
                    if (speed >= 0.1 && !perlin)
                    {
                        speed -= 0.03;
                    }
                    break;
                }
                case F10:
                {
                    // Speed up
                    if (speed <= 1.5 && !perlin)
                    {
                        speed += 0.03;
                    }
                    break;
                }
                case F11:
                {
                    // Perlin mode
                    this.perlin = !perlin;
                    break;
                }
            }
        });
        update = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            //60 fps
            //System.out.println("loop test");
            for (Segment segment : Segment.segments)
            {
                segment.update();
            }
            if (perlin)
            {
                speed = SimpleNoise.noise(xoff, 0, 0.1, 1, true);
                xoff += 0.03;
            }
        }));
        update.setCycleCount(Timeline.INDEFINITE);
        update.setRate(1);
        update.setAutoReverse(false);
        update.play(); //uncomment for play when start
        //
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, width - 10, height - 10, backcolor));
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
