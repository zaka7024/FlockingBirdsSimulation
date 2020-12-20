import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import java.awt.Color;

public class Bird {
    Bird [] birds; // all the birds include this
    Color color;
    double size; // the 2d point size
    Vector2 position; // in 1.1 Square
    Vector2 velocity;
    double maxSpeed = 40;
    double maxForce = 8;
    double distance = 0.3;

    public Bird(double s){
        Color [] colors = {Color.RED, Color.BLACK, Color.blue};
        color = colors[StdRandom.uniform(0,colors.length)];
        size = s;
        position = new Vector2(StdRandom.uniform(0,1.0), StdRandom.uniform(0,1.0));
        double x = StdRandom.uniform(-2,2.0) <= 0.5 ? -1 : 1;
        double y = StdRandom.uniform(-2,2.0) >= 0.5 ? -1 : 1;
        velocity = new Vector2(x, y);
    }

    public void move(double dt){
        Vector2 a1 = Rules.CohesionRule(birds, this);
        Vector2 a2 = Rules.SeparationRule(birds, this);
        Vector2 a3 = Rules.AlignmentRule(birds, this);
        Vector2 a4 = Rules.StayInThePosition(this, 30);

        // add all the aberrations to the this bird velocity

        velocity = Vector2.add(velocity, a1);
        velocity = Vector2.add(velocity, a2);
        velocity = Vector2.add(velocity, a3);
        velocity = Vector2.add(velocity, a4);

        // Check for max speed
        if(velocity.magnitude() > maxSpeed){
            velocity = Vector2.mul(Vector2.divide(velocity, velocity.magnitude()), maxSpeed);
        }
        // calculate this new displacement (change in Velocity = change in Position / change in Time)
        double dx = velocity.x * dt;
        double dy = velocity.y * dt;

        position = Vector2.add(new Vector2(dx, dy), position);
    }

    public void draw(){
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(size);
        StdDraw.point(position.x, position.y);
    }
}
