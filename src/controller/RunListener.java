package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Model;
import view.RunGui;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

    private Timer timer;
    private Model model;
    private RunGui GUI;

    public RunListener(Model m, RunGui gui) {
        this.GUI = gui;
        model = m;
        timer = new Timer(50, this);
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {

        if (e.getSource() == timer) {
            model.moveBall();
        } else
            switch (e.getActionCommand()) {
                case "Start":
                    model.quickUpdate();
                    timer.start();
                    System.out.println("Timer started");
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Load":
                    timer.stop();
                    System.out.println("Loading Action triggered.");
                    model.loadBoard();
                    System.out.println("-----------------");
                    break;
                case "Run Mode":
                    System.out.println("Run Mode Clicked");
                    model.setGizmoSelected(false);
                    model.setActionSelected(false);
                    model.setConnectionSelected(false);
                    GUI.switchToRun();
                    break;
                case "Build Mode":
                    timer.stop();
                    System.out.println("Build Mode Clicked");
                    GUI.switchToBuild();
                    break;
                case "Quit":
                    System.exit(0);
            }
    }
}
