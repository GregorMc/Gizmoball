package controller;

import model.Model;
import view.BuildComps;
import view.RunGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhysicsListener implements ActionListener {

    private RunGui gui;
    private Model m;

    public PhysicsListener(RunGui g, Model m){
        this.gui = g;
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Change Gravity":
                gui.promptGravity();
                break;
            case "Change Friction":
                gui.promptFriction();           //TODO changing friction works, need to deal with edge cases
                break;
        }
    }
}
