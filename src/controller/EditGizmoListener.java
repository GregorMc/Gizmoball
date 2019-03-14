package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGizmoListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Rotate":
                System.out.println("Rotate selected");
                break;
            case "Move":
                System.out.println("Move selected");
                break;
            case "Connect":
                System.out.println("Connect selected");
                break;
            case "Disconnect":
                System.out.println("Disconnect selected");
                break;
            case "Delete":
                System.out.println("Delete selected");
                break;
        }
    }
}