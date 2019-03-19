package model;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import jdk.nashorn.internal.ir.annotations.Ignore;
import physics.LineSegment;
import java.util.Collections;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;


public class RightFlipper implements IGizmo, IFlipper{

    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private final int L =25;

    private final String TYPE;
    private boolean activated;

    private Vect velocity;
    private Color colour;
    private double angle;
    private String gizID;

    private List<IGizmo> trigGiz;
    private List<String> keyConnects;

    public RightFlipper(String id, double xpos1,double ypos1){
        this.gizID = id;
        this.angle = 0;
        this.x1 = xpos1 + 1.5;
        this.y1 = ypos1;
        this.colour = Color.BLUE;
        this.TYPE = "RIGHT";

        this.trigGiz = new ArrayList<>();
        this.keyConnects = new ArrayList<>();
    }


    public List<LineSegment> getLineSegments() {

        List<LineSegment> lineSegments = new ArrayList<>();
        LineSegment l1 = new LineSegment(x1, y1, x2, y1);
        LineSegment l2 = new LineSegment(x2, y1, x2, y2);
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
        return gizID;
    }

    @Override
    public void performAction(Model model) {
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
        if(!trigGiz.contains(giz)){
            System.out.println("Connection does not exist");
        } else {
            trigGiz.remove(giz);
        }
    }

    @Override
    public List<IGizmo> getGizConnections() {
        return trigGiz;
    }

    @Override
    public void addKeyConnect(String key, String upDown) {
        String input = "key" + "." + key + "." + upDown;
        if(keyConnects.contains(input)){
            System.out.println("Connection already exists");
        } else {
            keyConnects.add(input);
        }
    }

    @Override
    public List<String> getKeyConnections() {
        return keyConnects;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    @Override
    public void setActive(boolean active) {
        this.activated = active;
    }

    @Override
    public double getAngle() {
        return angle;
    }


    public boolean isActivated(){
        return activated;
    }

    public void activateFlipper(boolean moving){
        if (moving != isActivated()){
            this.activated = moving;
        }
    }


    public Color getColour(){
        return colour;
    }

    public Vect getVelo() {
        return velocity;
    }

    public void setVelo(Vect v) {
        velocity = v;
    }

    public double getXpos() {
        return x1 * L;
    }

    public double getYpos() {
        return y1 * L;
    }


    public void setColour(Color colour){
        this.colour = colour;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void setXpos(double x) {
        x1 = x;
    }

    public void setYpos(double y) {
        y1 = y;
    }


}
