package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CircleGiz implements IGizmo {
    private int xpos;
    private int ypos;
    private String gizid;
    private final int L= 25;
    private final double radius = 12.5;
    private Color colour;

    private List<IGizmo> trigGiz;

    public CircleGiz(String id, int x, int y) {
        this.gizid = id;
        this.xpos = x;
        this.ypos = y;
        this.colour = Color.ORANGE;

        this.trigGiz = new ArrayList<>();
    }


    @Override
    public String getID() {
        return  gizid;
    }

    @Override
    public void performAction() {
        if(colour == Color.ORANGE){
            colour = Color.YELLOW;
        } else {
            colour = Color.ORANGE;
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
            System.out.println("Gizmo connection doesnt exist");
        }
    }

    @Override
    public List<IGizmo> getGizConnections() {
        return trigGiz;
    }

    public int getXposinL(){
        return xpos*L;
    }

    public int getYposinL(){
        return ypos*L;
    }


    //FIXME Don't think these are needed in this class, any way to omit them?
    @Override
    public List<LineSegment> getLineSegments() {
        return Collections.emptyList();
    }

    @Override
    public List<Circle> getCircles() {
        List<Circle> circles = new ArrayList<>();

        Circle c = new Circle((xpos*L)+radius,(ypos*L)+radius,radius);
        circles.add(c);

        return circles;
    }

}
