package model;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import physics.LineSegment;
import java.util.Collections;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class LeftFlipper implements IGizmo, IFlipper{

    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double movement;
    private boolean activated;
    private Vect velocity;
    private Color colour;
    private double angle;
    private final String TYPE;
    private Vect orignalPosition;
    private String gizID;
    private final int L =25;

    private List<IGizmo> trigGiz;



    public LeftFlipper(String id, double xpos1,double ypos1){
        this.gizID = id;
        this.x1 = xpos1;
        this.y1 = ypos1;
        this.colour = Color.BLUE;
        this.angle = 0;
        this.TYPE = "LEFT";
        this.activated = false;

        this.trigGiz = new ArrayList<>();
    }

    public List<LineSegment> getLineSegments() {
        List<LineSegment> lineSegments = new ArrayList<>();
        LineSegment l1 = new LineSegment(x1+0.25, y1, x2+1.75, y1);
        LineSegment l2 = new LineSegment(x2+0.25, y1+0.5, x2+1.75, y2+0.5);
        lineSegments.add(l1);
        lineSegments.add(l2);
        return lineSegments;
    }

    public List<Circle> getCircles() {
        List<Circle> circles = new ArrayList<>();
        Circle tl =  new Circle(new Vect(x1,y1),0);
        Circle tr = new Circle(new Vect(x2,y1), 0);
        Circle bl = new Circle(new Vect(x1,y2), 0);
        Circle br = new Circle(new Vect(x2,y2),0);
        Circle top =  new Circle(new Vect(x1+0.25,y1),0.25);
        Circle bottom = new Circle(new Vect(x2+1.75,y2+0.25),0.25);
        circles.add(tl);
        circles.add(tr);
        circles.add(bl);
        circles.add(br);
        circles.add(top);
        circles.add(bottom);
        return circles;
    }




    @Override
    public String getID() {
        return this.gizID;
    }

    @Override
    public void performAction() {
        if(activated){
            this.activated = false;
        } else {
            this.activated = true;
        }
    }

    @Override
    public Color getGizColour() {
        return colour;
    }

    @Override
    public void addGizConnect(IGizmo giz) {
        if(trigGiz.contains(giz)){
            System.out.println("Connection already exists");
        } else {
            trigGiz.add(giz);
        }
    }

    @Override
    public void deleteGizConnect(IGizmo giz) {
        if(trigGiz.contains(giz)){
            trigGiz.remove(giz);
        } else {
            System.out.println("Connection doesn't exist");
        }
    }

    @Override
    public List<IGizmo> getGizConnections() {
        return trigGiz;
    }

    public boolean isActivated(){
        return activated;
    }

    public Vect getVelo() {
        return velocity;
    }

    public void setVelo(Vect v) {
        velocity = v;
    }


    public void setActive(boolean active){
        this.activated = active;
    }

    public double getXpos() {
        return x1 * L;
    }

    public double getYpos() {
        return y1 * L;
    }

    public void setXpos(double x) {
        x1 = x;
    }

    public void setYpos(double y) {
        y1 = y;
    }

    public void setAngle(double a){
        this.angle = a;
    }

    public double getAngle(){
        return angle;
    }

    public void setColour(Color colour){
        this.colour = colour;
    }

    @Override
    public String getType() {
        return TYPE;
    }


    public Color getColour(){
        return colour;
    }

    public void move(){

    }
}
