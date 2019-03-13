package model;

import physics.Circle;
import physics.LineSegment;

import java.util.List;

public interface IGizmo {
    List<LineSegment> getLineSegments();
    List<Circle> getCircles();
    Circle getGizCircle();
    String getID();
}
