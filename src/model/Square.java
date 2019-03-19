package model;

import physics.Circle;
import physics.LineSegment;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Square implements IGizmo {

    private int xpos;
    private int ypos;
    private String gizid;
    private final int L =25;
    private Color colour;

    private List<IGizmo> trigGiz;
    private List<String> keyConnects;

    public Square(String id, int x, int y) {
        this.gizid = id;
        this.xpos = x;
        this.ypos = y;
        this.colour = Color.GREEN;

        this.trigGiz = new ArrayList<>();
        this.keyConnects = new ArrayList<>();
    }

    //Collision Methods
    @Override
    public List<LineSegment> getLineSegments() {
        List<LineSegment> lineSegments = new ArrayList<>();

        LineSegment l1 = new LineSegment((xpos*L),(ypos*L),((xpos*L)+L),(ypos*L));
        LineSegment l2 = new LineSegment(((xpos*L)+L),(ypos*L),((xpos*L)+L),((ypos*L)+L));
        LineSegment l3 = new LineSegment(((xpos*L)+L),((ypos*L)+L),(xpos*L),((ypos*L)+L));
        LineSegment l4 = new LineSegment((xpos*L),((ypos*L)+L),(xpos*L),(ypos*L));

        lineSegments.add(l1);
        lineSegments.add(l2);
        lineSegments.add(l3);
        lineSegments.add(l4);

        return lineSegments;
    }

    @Override
    public List<Circle> getCircles() {
        List<Circle> circles = new ArrayList<>();

        Circle c1 = new Circle((xpos*L),(ypos*L),0);
        Circle c2 = new Circle(((xpos*L)+L),(ypos*L),0);
        Circle c3 = new Circle(((xpos*L)+L),((ypos*L)+L),0);
        Circle c4 = new Circle((xpos*L),((ypos*L)+L),0);

        circles.add(c1);
        circles.add(c2);
        circles.add(c3);
        circles.add(c4);

        return circles;
    }


    @Override
    public String getID() {
        return gizid;
    }

    @Override
    public void performAction(Model model) {
        if(colour == Color.GREEN){
            colour = Color.BLUE;
        } else {
            colour = Color.GREEN;
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

    //Getters and Setters
    public int getXpos(){
        return xpos*L;
    }

    public int getYpos(){
        return ypos*L;
    }


}
