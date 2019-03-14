package main;

import model.Model;
import view.BuildGUI;
import view.RunGUI;
import view.View;

import javax.swing.*;

public class Main {

    public static void main(String args[]){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Look and Feel error in Main");
        }

        Model model = new Model(10,10,10,10);  //dunno about these co-ords

        View view = new View(model);
        view.createAndShowBuild();
        view.createAndShowRun();
    }
}
