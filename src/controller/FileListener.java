package controller;

import model.Model;
import view.RunGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FileListener implements ActionListener {

    private Model model;
    private RunGui gui;

    public FileListener(Model m, RunGui g) {
        this.gui = g;
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save Board":
                try{
                    model.saveBoard(gui.getInput("Please enter a filename"));
                } catch (Exception excep){
                    gui.changeText("Unable to save board");
                    excep.printStackTrace();
                }
                gui.changeText("Board saved");
                break;
            case "Load Board":
                model.loadBoard();
                gui.changeText("Board loaded");
                break;
            case "Reload Board":
                if(model.getLoaded()){
                    model.reloadBoard();
                    gui.changeText("Board reloaded");
                } else {
                    gui.changeText("Board has never been loaded.");
                }
                //TODO Implement
                break;


    }
    }
}
