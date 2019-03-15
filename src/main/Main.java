package main;

import model.Model;
import view.RunGui;


import javax.swing.*;

public class Main {

    public static void main(String args[]){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Look and Feel error in Main");
        }

        Model model = new Model();  //dunno about these co-ords

        RunGui view = new RunGui(model);
        //view.createAndShowBuild();
        view.createAndShowGUI();

    }
}
