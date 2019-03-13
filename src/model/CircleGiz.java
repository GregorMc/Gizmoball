package model;

import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CircleGiz implements IGizmo {
    private int xpos;
    private int ypos;
    private String gizid;
    private final int L= 25;
    private final double radius = 12.5;
    private final String type = "Circle";

    public CircleGiz(String id, int x, int y) {
        this.gizid = id;
        this.xpos = x;
        this.ypos = y;
    }

    @Override
    public String getID() {
        return  gizid;
    }

    public int getXposinP(){
        return xpos*L;
    }

    public int getYposinP(){
        return ypos*L;
    }


    //FIXME Don't think these are needed in this class, any way to omit them?
    @Override
    public List<LineSegment> getLineSegments() {
        return Collections.emptyList();
    }

    @Override
    public List<Circle> getCircles() {
        List<Circle> cirlces = new ArrayList<>();
        cirlces.add(new Circle((xpos*L)+radius,(ypos*L)+radius,radius));
        return cirlces;
    }

}
