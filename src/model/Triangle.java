package model;

import physics.Circle;
import physics.LineSegment;

import javax.sound.sampled.Line;
import java.util.*;

public class Triangle implements IGizmo {

    private int ypos;
    private int xpos;
    private final int L = 25;
    private String gizmoid;
    private Map<String,LineSegment> lineSegments;
    private Map<String,Circle> circles;
    private int rotationNum;

    public Triangle(String id, int x, int y) {
        this.gizmoid = id;
        this.xpos = x;
        this.ypos = y;
        this.lineSegments = new HashMap<>();
        this.circles = new HashMap<>();
        this.rotationNum = 0;
        updateLineSegments();
        updateCircles();
    }


    public void rotateTriangle(){
        if(rotationNum == 3){
            rotationNum = 0;
            updateLineSegments();
            updateCircles();
        } else {
            rotationNum++;
            updateLineSegments();
            updateCircles();
        }
    }

    public int getRotationNum(){
        return rotationNum;
    }

    private void updateCircles(){
        switch (rotationNum){
            case 0:
                circles.put("northWest",new Circle((xpos*L),(ypos*L),0));
                circles.put("northEast", new Circle((xpos*L)+L,(ypos*L),0));
                circles.put("southWest", new Circle((xpos*L), (ypos*L)+L,0));
                break;
            case 1:
                circles.put("northWest",new Circle(((xpos*L)+L),(ypos*L),0));
                circles.put("northEast",new Circle(((xpos*L)+L),((ypos*L)+L),0));
                circles.put("southWest",new Circle((xpos*L),(ypos*L),0));
                break;
            case 2:
                circles.put("northWest",new Circle(((xpos*L)+L),((ypos*L)+L),0));
                circles.put("northEast",new Circle((xpos*L),((ypos*L)+L),0));
                circles.put("southWest",new Circle(((xpos*L)+L),(ypos*L),0));
                break;
            case 3:
                circles.put("northWest",new Circle((xpos*L),((ypos*L)+L),0));
                circles.put("northEast",new Circle((xpos*L),(ypos*L),0));
                circles.put("southWest",new Circle(((xpos*L)+L),((ypos*L)+L),0));
                break;
            default:
                System.out.println("Error updating Circles in Triangle");
        }
    }


    public int[] getXpos(){
        switch (rotationNum) {
            case 0:
                return new int[] {(xpos*L),((xpos*L)+L),(xpos*L)};
            case 1:
                return new int[] {((xpos*L)+L),((xpos*L)+L),(xpos*L)};
            case 2:
                return new int[] {((xpos*L)+L),(xpos*L),((xpos*L)+L)};
            case 3:
                return new int[] {(xpos*L),(xpos*L),((xpos*L)+L)};
            default:
                System.out.println("Error returning xpos to board");
                return new int[] {(xpos*L),((xpos*L)+L),(xpos*L)};
        }
    }

    public int[] getYpos() {
        switch (rotationNum) {
            case 0:
                return new int[] {(ypos*L),(ypos*L),((ypos*L)+L)};
            case 1:
                return new int[] {(ypos*L),((ypos*L)+L),(ypos*L)};
            case 2:
                return new int[] {((ypos*L)+L),((ypos*L)+L),(ypos*L)};
            case 3:
                return new int[] {((ypos*L)+L),(ypos*L),((ypos*L)+L)};
            default:
                System.out.println("Error returning ypos to board");
                return new int[] {(ypos*L),(ypos*L),((ypos*L)+L)};
        }
    }
    private void updateLineSegments(){
        switch (rotationNum){

            case 0:
                lineSegments.put("north",new LineSegment(((xpos*L)+L), (ypos*L), (xpos*L), (ypos*L)));
                lineSegments.put("hypotenuse", new LineSegment((xpos*L),((ypos*L)+L),((xpos*L)+L),(ypos*L)));
                lineSegments.put("west", new LineSegment((xpos*L),(ypos*L),(xpos*L),(ypos*L)+L));
                break;
            case 1: //Rotation 1 FIXME Change positions
                lineSegments.put("west",new LineSegment((xpos*L),(ypos*L),(xpos*L),(ypos*L)+L));
                lineSegments.put("hypotenuse",new LineSegment((xpos*L),(ypos*L),((xpos*L)+L),((ypos*L)+L)));
                lineSegments.put("north",new LineSegment(((xpos*L)+L),((ypos*L)+L), ((xpos*L)+L), (ypos*L)));
                break;
            case 2: //Rotation 2 FIXME Change positions
                lineSegments.put("north", new LineSegment((xpos*L),((ypos*L)+L),((xpos*L)+L),(ypos*L)+L));
                lineSegments.put("hypotenuse",new LineSegment((xpos*L),((ypos*L)+L),((xpos*L)+L),(ypos*L)));
                lineSegments.put("west", new LineSegment(((xpos*L)+L), (ypos*L), ((xpos*L)+L), ((ypos*L)+L)));
                break;
            case 3: //Rotation 3 FIXME change position
                lineSegments.put("north",new LineSegment((xpos*L),(ypos*L),(xpos*L),(ypos*L)+L));
                lineSegments.put("hypotenuse",new LineSegment((xpos*L),(ypos*L),((xpos*L)+L),((ypos*L)+L)));
                lineSegments.put("west",new LineSegment((xpos*L), ((ypos*L)+L), ((xpos*L)+L), ((ypos*L)+L)));
                break;
            default:
                System.out.println("Error returning LineSegments in Triangle");
        }
    }

    @Override
    public List<LineSegment> getLineSegments() {
        List<LineSegment> lineSeg = new ArrayList<>();

        lineSeg.add(lineSegments.get("north"));
        lineSeg.add(lineSegments.get("west"));
        lineSeg.add(lineSegments.get("hypotenuse"));

        return lineSeg;
    }

    @Override
    public List<Circle> getCircles() {
        List<Circle> circleSegs = new ArrayList<>();

        circleSegs.add(circles.get("northWest"));
        circleSegs.add(circles.get("northEast"));
        circleSegs.add(circles.get("southWest"));

        return circleSegs;
    }


    @Override
    public String getID() {
        return gizmoid;
    }


    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

}
