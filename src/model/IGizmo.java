package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public interface IGizmo {
    List<LineSegment> getLineSegments();
    List<Circle> getCircles();
    String getID();
    void performAction(Model model);
    Color getGizColour();

    //Connections
    void addGizConnect(IGizmo giz);
    void deleteGizConnect(IGizmo giz);

    List<IGizmo> getGizConnections();

    void addKeyConnect(String key, String upDown);
    List<String> getKeyConnections();
    //void keyDisconnect(String key); //FIXME Might have to change

}
