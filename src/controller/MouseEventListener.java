package controller;

import model.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseEventListener implements MouseListener {

    private Model m;
    private IGizmo g;
    private IFlipper f;
    private static final int L = 25;

    public MouseEventListener(Model m){
        this.m = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX()/L;
        int y = e.getY()/L;
        System.out.println(x+","+y);

        if(!m.getGizmoSelected()){
            return;
        }

        switch(m.getSelectedGizmo()){
            case "Ball":  //TODO this actually breaks it
                System.out.println("Ball placement attempted");
                // g = new Ball("b",x,y,0,0);
                m.getBall().setExactX(x * 25);
                m.getBall().setExactY(y * 25);
                break;
            case "Square":
                g =  new Square("s",x,y);  //works
                break;
            case "Circle":
                g =  new CircleGiz("c",x,y);  //works
                break;
            case "Triangle":
                g =  new Triangle("t",x,y); //works
                break;
            case "Left Flipper":
                System.out.println("left flipper attempted");  //TODO I think because these are drawn differently
                f =  new LeftFlipper("lf",x,y);
                break;
            case "Right Flipper":
                System.out.println("right flipper attempted");
                f =  new RightFlipper("rf",x,y);
                // m.addFlipper()
                break;
            case "Absorber":
                System.out.println("absorber attempted");
                g =  new Absorber("ab",x,y,x + 1,y + 1);       //TODO this needs to be done differently, i.e mouse clicked, mouse released
                break;
        }
        m.addGizmo(g);

        if(m.getSelectedGizmo().equals("Left Flipper") || m.getSelectedGizmo().equals("Right Flipper")){
            m.addFlipper(f);
        }
        m.quickUpdate();
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
