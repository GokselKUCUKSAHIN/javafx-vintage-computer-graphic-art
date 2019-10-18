import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Segment
{

    static double w = 0;
    static double h = 0;

    static
    {
        // w and h is middle point of screen
        // static block will run first and once
        w = Main.width / 2;
        h = Main.height / 2;
    }

    // static object arrayList
    public static ArrayList<Segment> segments = new ArrayList<>();

    // body inital values (top left - out of screen)
    private Line body = new Line(-10, -10, -10, -10);

    // progress counter
    double progress = 0;

    public Segment()
    {
        // Constructor
        calculateNextMove();
        body.setStroke(Color.rgb(199, 199, 199,0.5)); // cool gray ;)
        body.setStrokeWidth(3); // default value

        segments.add(this);
    }

    public Segment(double progress)
    {
        // 2nd Constructor
        this();
        setProgress(progress);
        setThickness();
    }

    private void calculateNextMove()
    {
        //Tr(w, h, X(t + i)), Tr(w, h, Y(t + i))
        Point2D start = Tr(w, h, X(progress));
        Point2D end = Tr(w, h, Y(progress));
        body.setStartX(start.getX());
        body.setStartY(start.getY());
        body.setEndX(end.getX());
        body.setEndY(end.getY());
    }

    public void update()
    {
        calculateNextMove();
        // increment progress for next move
        progress += Main.speed; //0.5
    }

    public void setProgress(double progress)
    {
        this.progress = progress;
    }

    public void setThickness()
    {
        this.body.setStrokeWidth(progress * 0.2 + 0.59);
    }

    public Node getNode()
    {
        return this.body;
    }

    private static Point2D X(double t)
    {
        return new Point2D(Math.sin(t / 6) * 100 + Math.sin(t / 4) * 20, Math.sin(t / 10) * 200 + Math.sin(t) * 2);
    }

    private static Point2D Y(double t)
    {
        return new Point2D(Math.cos(t / 10) * 200, Math.cos(t / 20) * 200 + Math.cos(t / 12) * 40);
    }

    private static Point2D X2(double t)
    {
        return new Point2D(Math.sin(t / 10) * 200 + Math.sin(t / 3) * 20, Math.sin(t / 10) * 260 + Math.sin(-t) * 14);
    }

    private static Point2D Y2(double t)
    {
        return new Point2D(Math.cos(t / 7) * 150, (Math.cos(t / 16) * 70) + (Math.cos(t / 9) * 29));
    }

    private static Point2D Tr(double left, double top, Point2D p)
    {
        return new Point2D(left + p.getX(), top + p.getY());
    }
}
