package controller;

import model.Ball;
import model.Model;
import view.RunGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGizmoListener implements ActionListener {

    private Model gm;
    private RunGui gui;

    public SelectGizmoListener(Model m, RunGui g){
        this.gm = m;
        this.gui = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
            case "Ball":
                gui.changeText("This is bugged app");
                gm.setGizmoSelected(true);
                gm.setActionSelected(false);
                gm.setSelectedGizmo("Ball");
                break;
            case "Square":
                gui.changeText("Select an empty space to add a Square");
                gm.setGizmoSelected(true);
                gm.setActionSelected(false);
                gm.setSelectedGizmo("Square");
                break;
            case "Circle":
                gui.changeText("Select an empty space to add a Circle");
                gm.setGizmoSelected(true);
                gm.setActionSelected(false);
                gm.setSelectedGizmo("Circle");
                break;
            case "Triangle":
                gui.changeText("Select an empty space to add a Triangle");
                gm.setGizmoSelected(true);
                gm.setActionSelected(false);
                gm.setSelectedGizmo("Triangle");
                break;
            case "Left Flipper":
                gui.changeText("Select an empty space to add a Left Flipper");
                gm.setGizmoSelected(true);
                gm.setActionSelected(false);
                gm.setSelectedGizmo("Left Flipper");
                break;
            case "Right Flipper":
                gui.changeText("Select an empty space to add a Right Flipper");
                gm.setGizmoSelected(true);
                gm.setActionSelected(false);
                gm.setSelectedGizmo("Right Flipper");
                break;
            case "Absorber":
                gui.changeText("Choose where to start your Absorber.");
                gm.setGizmoSelected(true);
                gm.setActionSelected(false);
                gm.setSelectedGizmo("Absorber");
                break;
            default:
                gm.setGizmoSelected(false);
                break;
        }
    }
}
