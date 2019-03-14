package controller;

import model.Ball;
import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGizmoListener implements ActionListener {

    private Model gm;

    public SelectGizmoListener(Model m){
        this.gm = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
            case "Ball":
                System.out.println("Ball selected");
                gm.setGizmoSelected(true);
                gm.setSelectedGizmo("Ball");
                break;
            case "Square":
                System.out.println("Square selected");
                gm.setGizmoSelected(true);
                gm.setSelectedGizmo("Square");
                break;
            case "Circle":
                System.out.println("Circle selected");
                gm.setGizmoSelected(true);
                gm.setSelectedGizmo("Circle");
                break;
            case "Triangle":
                System.out.println("Triangle selected");
                gm.setGizmoSelected(true);
                gm.setSelectedGizmo("Triangle");
                break;
            case "Left Flipper":
                System.out.println("Left Flipper selected");
                gm.setGizmoSelected(true);
                gm.setSelectedGizmo("Left Flipper");
                break;
            case "Right Flipper":
                System.out.println("Right Flipper selected");
                gm.setGizmoSelected(true);
                gm.setSelectedGizmo("Right Flipper");
                break;
            case "Absorber":
                System.out.println("Absorber selected");
                gm.setGizmoSelected(true);
                gm.setSelectedGizmo("Absorber");
                break;
            default:
                gm.setGizmoSelected(false);
                break;
        }
    }
}
