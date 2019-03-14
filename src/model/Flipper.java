package model;
import java.util.List;
import java.util.ArrayList;
import physics.LineSegment;
import java.util.Collections;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import java.awt.Color;



public interface Flipper {

    List<LineSegment> getLineSegments();

    List<Circle> getCircles();

    double getAngle();

    double getXpos();

    double getYpos();

    boolean isActivated();

    void setAngle(double angle);

    void setActive(boolean active);

    void setColour(Color colour);

}