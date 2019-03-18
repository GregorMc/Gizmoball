package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public interface IGizmo {
    List<LineSegment> getLineSegments();
    List<Circle> getCircles();
    String getID();
    void performAction();
    Color getGizColour();
    void addGizConnect(IGizmo giz);
    void deleteGizConnect(IGizmo giz);
    List<IGizmo> getGizConnections();
}
