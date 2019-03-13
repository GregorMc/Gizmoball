package model;

import java.awt.*;

public interface IFlipper {
    double getAngle();
    double getXpos();
    double getYpos();
    boolean isActivated();
    void setAngle(double angle);
    void setActive(boolean active);
    void setColour(Color colour);

    String getType();
}
