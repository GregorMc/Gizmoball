package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Ball {

    private Vect velocity;
    private double radius;
    private double xpos;
    private double ypos;
    private Color colour;
    private final int L = 25;
    private String id;

    private boolean stopped;

    // x, y coordinates and x,y velocity fixme Need to be in L's
    public Ball(String ballid, double x, double y, double xv, double yv) {
        this.id = ballid;
        xpos = x*L; // Centre coordinates
        ypos = y*L;
        colour = Color.BLUE;
        velocity = new Vect(xv, yv);
        radius = 8;
        stopped = false;
    }

    public Vect getVelo() {
        return velocity;
    }

    public void setVelo(Vect v) {
        velocity = v;
    }

    public double getRadius() {
        return radius;
    }

    public Circle getCircle() {
        return new Circle(xpos, ypos, radius);

    }

    // Ball specific methods that deal with double precision.
    public double getExactX() {
        return xpos;
    }

    public double getExactY() {
        return ypos;
    }

    public void setExactX(double x) {
        xpos = x;
    }

    public void setExactY(double y) {
        ypos = y;
    }

    public void stop() {
        stopped = true;
    }

    public void start() {
        stopped = false;
    }

    public boolean stopped() {
        return stopped;
    }

    public Color getColour() {
        return colour;
    }

}
