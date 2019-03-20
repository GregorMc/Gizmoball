package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Absorber implements IGizmo {

    private final int L = 25;
    private int xpos1;
    private int ypos1;
    private int xpos2;
    private int ypos2;
    private LineSegment northLine;
    private String id;
    private Color colour;

    private List<IGizmo> trigGiz;
    private List<String> keyConnects;

    public Absorber(String gizid, int x1,int y1,int x2,int y2){
        this.northLine = new LineSegment((x1*L), (y1*L),(x2*L), (y1*L));
        this.id = gizid;
        this.xpos1 = x1;
        this.ypos1 = y1;
        this.xpos2 = x2;
        this.ypos2 = y2;
        this.colour = Color.PINK;

        this.trigGiz = new ArrayList<>();
        this.keyConnects = new ArrayList<>();
    }

    public List<LineSegment> getLineSegments() {
        List<LineSegment> lineSegments = new ArrayList<>();

        northLine = new LineSegment((xpos1*L),(ypos1*L),(xpos2*L),(ypos1*L));
        LineSegment l2 = new LineSegment((xpos2*L),(ypos1*L),(xpos2*L),(ypos2*L));
        LineSegment l3 = new LineSegment((xpos2*L),(ypos2*L),(xpos1*L),(ypos2*L));
        LineSegment l4 = new LineSegment((xpos1*L),(ypos2*L),(xpos1*L),(ypos1*L));

        lineSegments.add(northLine);
        lineSegments.add(l2);
        lineSegments.add(l3);
        lineSegments.add(l4);

        return lineSegments;
    }


    public List<Circle> getCircles() {
        List<Circle> circles = new ArrayList<>();

        Circle c1 = new Circle((xpos1*L),(ypos1*L),0);
        Circle c2 = new Circle((xpos2*L),(ypos1*L),0);
        Circle c3 = new Circle((xpos2*L),(ypos2*L),0);
        Circle c4 = new Circle((xpos1*L),(ypos1*L),0);

        circles.add(c1);
        circles.add(c2);
        circles.add(c3);
        circles.add(c4);

        return circles;
    }


    @Override
    public String getID() {
        return id;
    }

    @Override
    public void performAction(Model model) {
        model.launchBall();
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
            System.out.println("Connection added in Absorber");
            System.out.println(input);
            System.out.println("------------");
        }
    }

    @Override
    public void removeKeyConnect(String command) {
        if(keyConnects.contains(command)){
            keyConnects.remove(command);
        } else {
            System.out.println("connection doesnt exist");
        }
    }

    @Override
    public List<String> getKeyConnections() {
        return keyConnects;
    }

    @Override
    public int getXposinL() {
        return xpos1;
    }

    @Override
    public int getYposinL() {
        return ypos1;
    }

    @Override
    public int getXPosinP() {
        return xpos1*L;
    }

    @Override
    public int getYPosinP() {
        return ypos1*L;
    }

    @Override
    public void setX(int x) {
        //ignore
    }

    @Override
    public void setY(int y) {
        //ignore
    }

    public int getXpos1InL(){ return (xpos1*L); }

    public int getXpos2InL(){ return (xpos2*L); }

    public int getYpos1InL() { return (ypos1*L); }

    public int getYpos2InL() { return (ypos2*L); }

    public int getXpos(){ return xpos1; }

    public int getXpos2(){ return xpos2; }

    public int getYpos1() { return ypos1; }

    public int getYpos2() { return ypos2; }



}
