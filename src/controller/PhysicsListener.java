package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhysicsListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Change Gravity":
                System.out.println("Change Gravity selected");
                break;
            case "Change Friction":
                System.out.println("Change Friction selected");
                break;
        }
    }
}