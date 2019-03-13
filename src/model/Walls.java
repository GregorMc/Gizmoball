package model;

import java.util.ArrayList;

import physics.LineSegment;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Walls {

    private int xpos1;
    private int ypos1;
    private int ypos2;
    private int xpos2;
    private final int L = 25;

    // Walls are the enclosing Rectangle - defined by top left corner and bottom
    // right
    public Walls(int x1, int y1, int x2, int y2) {
        xpos1 = x1;
        ypos1 = y1;
        xpos2 = x2;
        ypos2 = y2;
    }

    public ArrayList<LineSegment> getLineSegments() {
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(xpos1*L, ypos1*L, xpos2*L, ypos1*L);
        LineSegment l2 = new LineSegment(xpos1*L, ypos1*L, xpos1*L, ypos2*L);
        LineSegment l3 = new LineSegment(xpos2*L, ypos1*L, xpos2*L, ypos2*L);
        LineSegment l4 = new LineSegment(xpos1*L, ypos2*L, xpos2*L, ypos2*L);
        ls.add(l1);
        ls.add(l2);
        ls.add(l3);
        ls.add(l4);
        return ls;
    }

}
