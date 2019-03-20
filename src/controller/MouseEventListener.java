package controller;

import model.*;
import view.RunGui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseEventListener implements MouseListener {

    private boolean selected = false;
    private boolean firstPosSelected = false;

    private Model m;
    private RunGui gui;

    private IFlipper f;

    //Variables to keep track of multiple click data
    private int count;
    private IGizmo tempGiz;


    private static final int L = 25;
    private int absorberX1;
    private int absorberX2;
    private int absorberY1;
    private int absorberY2;
    private int moveX1 = 0, moveY1 = 0;


    public MouseEventListener(Model m, RunGui runGui){
        this.m = m;
        this.gui = runGui;

        this.count = 0;
        this.tempGiz = new Square("SFAKE",-1,-1);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX()/L;
        int y = e.getY()/L;
        System.out.println(x+","+y);

        if(!m.getGizmoSelected() && !m.getActionSelected()){ //TODO why not just replace this with checking that gizmoSelected is not Null or something... && gizmoAction idk
            if(!m.getConnectionSelected()){
                return;
            }
        }

        IGizmo g = new Square("SFAKE", -5,-5);
        if(m.getGizmoSelected() && !(m.getConnectionSelected() || m.getActionSelected())) {
                //Switch statement to get the right gizmo.
            System.out.println("Trying gizmo");
                switch (m.getSelectedGizmo()) {
                    case "Ball":  //TODO this actually breaks it
                        System.out.println("Ball placement attempted");
                        // g = new Ball("b",x,y,0,0);
                        m.getBall().setExactX(x * 25);
                        m.getBall().setExactY(y * 25);
                        break;
                    case "Square":
                        g = new Square("S"+x+y, x, y);  //works
                        break;
                    case "Circle":
                        g = new CircleGiz("C"+x+y, x, y);  //works
                        break;
                    case "Triangle":
                        g = new Triangle("T"+x+y, x, y); //works
                        break;
                    case "Left Flipper":
                        System.out.println("left flipper attempted");  //TODO I think because these are drawn differently
                        f = new LeftFlipper("LF"+x+y, x, y);
                        break;
                    case "Right Flipper":
                        System.out.println("right flipper attempted");
                        f = new RightFlipper("RF"+x+y, x, y);
                        // m.addFlipper()
                        break;
                    case "Absorber":
                        if(count == 0) {
                            absorberX1 = x;
                            absorberY1 = y;
                            System.out.println("x1,y1 selected");
                            count++;
                            break;
                        }
                        System.out.println(count);
                        if(count == 1) {
                            absorberX2 = x;
                            absorberY2 = y;
                            System.out.println("x2,y2 selected");
                            g = new Absorber("A"+absorberX1+absorberY1, absorberX1, absorberY1, absorberX2, absorberY2);
                            m.addGizmo(g);
                            count = 0;
                            break;
                        }
                        break;
                }
            System.out.println("Selected gizmo: " + m.getSelectedGizmo());
                if(!m.getSelectedGizmo().equals("Absorber")) {
                    if (!m.isGizmoAtCoordinates(g.getXposinL(), g.getYposinL())) {

                        if (m.getSelectedGizmo().equals("Left Flipper") || m.getSelectedGizmo().equals("Right Flipper")) {
                            m.addFlipper(f);
                            gui.changeText(m.getSelectedGizmo() + " added at: (" + x + "," + y + ").");
                        } else {
                            m.addGizmo(g);
                            gui.changeText("Gizmo " + g.getID() + " added at: (" + x + "," + y + ").");
                        }
                    } else {
                        gui.changeText("Gizmo already exists here.");
                    }
                }
            m.quickUpdate();

        }
        if(m.getActionSelected()&& !(m.getConnectionSelected() || m.getGizmoSelected())){
            System.out.println("Trying action");
            switch (m.getSelectedAction()) {
                case "Rotate":
                    IGizmo giz = m.getGizmoAtCoordinates(x,y);
                    if(!m.isGizmoAtCoordinates(x,y)){
                        gui.changeText("There is no gizmo here to rotate");
                    } else if(!(giz instanceof  Triangle)) {
                        gui.changeText("You cannot rotate this type of gizmo");
                    } else {
                        Triangle t = (Triangle) giz;
                        t.rotateTriangle();
                        gui.changeText("Gizmo: " + t.getID() + " rotated, choose another gizmo.");
                    }
                    System.out.println("Rotate attempted");
                    m.quickUpdate();
                    break;
                case "Move":
                    switch (count) {
                        case 0:
                            if(m.isGizmoAtCoordinates(x,y)){
                                moveX1 = x;
                                moveY1 = y;
                                gui.changeText("Select where you would like to move the gizmo");
                            } else {
                                gui.changeText("There is no gizmo at ("+ x + "," +y +") to move, try again");
                                count = -1;
                            }
                            break;
                        case 1:
                            if(!m.isGizmoAtCoordinates(x,y)){
                                IGizmo tempGiz = m.getGizmoAtCoordinates(moveX1,moveY1);
                                tempGiz.setX(x);
                                tempGiz.setY(y);

                                m.removeGizmo(m.getGizmoAtCoordinates(moveX1,moveY1));
                                m.addGizmo(tempGiz);
                                gui.changeText("Gizmo moved from ("+moveX1+","+moveY1+") to ("+x+","+y+"), choose another gizmo to move!");
                                count = -1;
                            } else {
                                gui.changeText("You cannot place a gizmo on another gizmo, try again");
                                count = 0;
                            }
                            break;
                    }
                    count++;
                    break;
                case "Delete":
                    if (m.isGizmoAtCoordinates(x, y)) {
                        g = m.getGizmoAtCoordinates(x, y);
                        m.removeGizmo(g);
                        if(g instanceof LeftFlipper || g instanceof RightFlipper){
                            m.removeFlipper((IFlipper)g);
                        }
                        gui.changeText("Gizmo at: (" + x + "," + y +") deleted");
                    } else {
                        gui.changeText("There is no gizmo here to delete! Try again.");
                    }
                    System.out.println("Delete attempted");
                    m.quickUpdate();
                    break;
            }
        }

        if(m.getConnectionSelected() && !(m.getGizmoSelected() || m.getActionSelected())){
            System.out.println("trying connection");
            switch (m.getSelectedConnection()){
                case "Connect Gizmo's":
                    System.out.println("trying connect");
                    System.out.println("Clicks: "+count);
                    //Must leave count how it was found after action.
                    switch (count) {
                        case 0:
                            if(m.isGizmoAtCoordinates(x,y)){
                                moveX1 = x;
                                moveY1 = y;

                                gui.changeText("Select which gizmo's action you want to trigger.");
                            } else {
                                gui.changeText("There is no gizmo there! Try again.");
                                count = -1;
                            }
                            break;
                        case 1:
                            if(m.isGizmoAtCoordinates(x,y)){
                                if(m.getGizmoAtCoordinates(moveX1,moveY1).getGizConnections().contains(m.getGizmoAtCoordinates(x,y))){
                                    gui.changeText("These gizmos are already connected, choose another trigger Gizmo");
                                    count = -1;
                                } else {
                                    m.getGizmoAtCoordinates(moveX1,moveY1).addGizConnect(m.getGizmoAtCoordinates(x,y));
                                    gui.changeText("Connection added, choose another trigger gizmo");
                                    count = -1;
                                }
                            } else {
                                gui.changeText("There is no gizmo here! Choose a trigger gizmo.");
                                count = 0;
                            }
                            break;
                    }
                    count++;
                    break;
                case "Disconnect Gizmo's":
                    System.out.println("trying disconnect");
                    //Must leave count how it was found after action.
                    System.out.println("Del CLicked: " + count);
                    switch (count) {
                        case 0:
                            if(m.isGizmoAtCoordinates(x,y)){
                                moveX1 = x;
                                moveY1 = y;

                                gui.changeText("Select which gizmo's action you want to disconnect.");
                            } else {
                                gui.changeText("There is no gizmo there! Select trigger gizmo.");
                                count = -1;
                            }
                            break;
                        case 1:
                            if(m.isGizmoAtCoordinates(x,y)){
                                if(!m.getGizmoAtCoordinates(moveX1,moveY1).getGizConnections().contains(m.getGizmoAtCoordinates(x,y))){
                                    gui.changeText("Gizmos not connected, Select which trigger gizmo you would like to delete.");
                                    count = -1;
                                } else {
                                    m.getGizmoAtCoordinates(moveX1,moveY1).deleteGizConnect(m.getGizmoAtCoordinates(x,y));
                                    gui.changeText("Connection deleted, choose another trigger gizmo to delete");
                                    count = -1;
                                }
                            } else {
                                gui.changeText("There is no gizmo here! Choose an action gizmo.");
                                count = 0;
                            }
                            break;
                    }
                    count++;
                    break;
                case "KeyConnect Gizmo":
                    gui.changeText("KeyConnecting gizmos");

                    System.out.println("Del CLicked: " + count);
                    switch (count) {
                        case 0:
                            int reply = JOptionPane.showConfirmDialog(null,"Have you pressed a Key?", "Confirm KeyPress", JOptionPane.YES_NO_OPTION);
                            if(reply == JOptionPane.YES_OPTION){
                                m.setNeedKeyCode(false);
                                gui.changeText("Select which gizmo's action you want to trigger.");
                            } else {
                                gui.changeText("Please select a key and click the board");
                                count = -1;
                            }
                            break;
                        case 1:
                            if(m.isGizmoAtCoordinates(x,y)){
                                String key = Integer.toString(m.getLastKeyCode());
                                if(key.equals("0")) {
                                    gui.changeText("Key not found, try again.");
                                    m.setNeedKeyCode(true);
                                    count = -1;
                                } else {
                                    String input = "key" + "." + key + "." + "down";
                                    if(m.getGizmoAtCoordinates(x,y).getKeyConnections().contains(input)){
                                        gui.changeText("Key connect already exists, choose another action gizmo.");
                                        count = 0;
                                    } else {
                                        m.getGizmoAtCoordinates(x,y).addKeyConnect(key, "down");
                                        gui.changeText("Connection added, choose another key & click.");
                                        m.setNeedKeyCode(true);
                                        count = -1;
                                    }
                                }
                            } else {
                                gui.changeText("There is no gizmo here! Choose an action gizmo.");
                                count = 0;
                            }
                            break;
                    }
                    count++;
                    break;
                case "Remove KeyConnect":
                    gui.changeText("Key-Disconnecting gizmos");
                    System.out.println("Del CLicked: " + count);
                    switch (count) {
                        case 0:
                            int reply = JOptionPane.showConfirmDialog(null,"Have you pressed a Key?", "Confirm KeyPress", JOptionPane.YES_NO_OPTION);
                            if(reply == JOptionPane.YES_OPTION){
                                m.setNeedKeyCode(false);
                                gui.changeText("Select which gizmo's action you want to delete.");
                            } else {
                                gui.changeText("Select a key to connect and click board.");
                                count = -1;
                            }
                            break;
                        case 1:
                            if(m.isGizmoAtCoordinates(x,y)){
                                String key = Integer.toString(m.getLastKeyCode());
                                if(key.equals("0")) {
                                    gui.changeText("Key not found, try again.");
                                    m.setNeedKeyCode(true);
                                    count = -1;
                                } else {
                                    String input = "key" + "." + key + "." + "down";
                                    if(m.getGizmoAtCoordinates(x,y).getKeyConnections().contains(input)){
                                        m.getGizmoAtCoordinates(x,y).removeKeyConnect(input);
                                        gui.changeText("Connection deleted, choose another key");
                                        m.setNeedKeyCode(true);
                                        count = -1;
                                    } else {
                                        gui.changeText("KeyConnect doesnt exist, choose another key.");
                                        m.setNeedKeyCode(true);
                                        count = -1;
                                    }
                                }
                            } else {
                                gui.changeText("There is no gizmo here! Choose an action gizmo.");
                                count = 0;
                            }
                            break;
                    }
                    count++;
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
