package controller;

import model.Model;
import view.RunGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionListener implements ActionListener {

    private RunGui gui;
    private Model model;

    public ConnectionListener(RunGui g, Model m){
        this.gui = g;
        this.model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Connect Gizmo's":
                //Set selected gizmo
                model.setSelectedConnection("Connect Gizmo's");
                //Prompt user
                gui.changeText("Select which gizmo you would like to produce a trigger.");
                //model booleans
                model.setConnectionSelected(true);
                model.setActionSelected(false);
                model.setGizmoSelected(false);
                break;
            case "Disconnect Gizmo's":
                //Set selected gizmo
                model.setSelectedConnection("Disconnect Gizmo's");
                //Prompt user
                gui.changeText("Select which trigger gizmo you would like to delete.");
                //model booleans
                model.setConnectionSelected(true);
                model.setActionSelected(false);
                model.setGizmoSelected(false);
                break;
            case "KeyConnect Gizmo":
                //Set selected gizmo
                model.setSelectedConnection("KeyConnect Gizmo");
                //FIXME Prompt user
                gui.changeText("Please select which key to connect to a gizmo & click board");

                model.setNeedKeyCode(true);

                //model booleans
                model.setConnectionSelected(true);
                model.setActionSelected(false);
                model.setGizmoSelected(false);
                break;
            case "Remove KeyConnect":
                //Set selected gizmo
                model.setSelectedConnection("Remove KeyConnect");
                //FIXME Prompt user
                gui.changeText("Please select which key to delete & click board");
                model.setNeedKeyCode(true);

                //model booleans
                model.setConnectionSelected(true);
                model.setActionSelected(false);
                model.setGizmoSelected(false);
                break;
        }
    }
}
