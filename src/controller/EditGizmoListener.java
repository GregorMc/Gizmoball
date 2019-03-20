package controller;


import model.Model;
import view.RunGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGizmoListener implements ActionListener {

    private RunGui gui;
    private Model model;

    public EditGizmoListener(RunGui g, Model m){
        this.gui = g;
        this.model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        switch (e.getActionCommand()) {
            case "Rotate":
                model.setSelectedAction("Rotate");
                gui.changeText("Select which gizmo to rotate");
                //System.out.println("Rotate selected");
//                model.setGizmoSelected(false);
                model.setActionSelected(true);
                model.setGizmoSelected(false);
                model.setConnectionSelected(false);
                break;
            case "Move":
                model.setSelectedAction("Move");
                gui.changeText("Select which gizmo you want to move");
                //System.out.println("Move selected");
//                model.setGizmoSelected(false);
                model.setActionSelected(true);
                model.setGizmoSelected(false);
                model.setConnectionSelected(false);
                break;
            case "Connect":
                model.setSelectedAction("Connect");
                gui.changeText("Please select the trigger gizmo");
                // System.out.println("Connect selected");
//                model.setGizmoSelected(false);
                model.setActionSelected(true);
                model.setGizmoSelected(false);
                model.setConnectionSelected(false);
                break;
            case "Disconnect":
                model.setSelectedAction("Disconnect");
                gui.changeText("Please select the *action* gizmo to disconnect");
                //System.out.println("Disconnect selected");
//                model.setGizmoSelected(false);
                model.setActionSelected(true);
                model.setGizmoSelected(false);
                model.setConnectionSelected(false);
                break;
            case "Delete":
                model.setSelectedAction("Delete");
                gui.changeText("Please select which gizmo to delete");
                //  System.out.println("Delete selected");
//                model.setGizmoSelected(false);
                model.setActionSelected(true);
                model.setGizmoSelected(false);
                model.setConnectionSelected(false);
                break;
        }
    }
}
