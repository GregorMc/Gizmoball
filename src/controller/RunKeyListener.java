package controller;

import model.IFlipper;
import model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RunKeyListener implements KeyListener {


    private Model model;

    public RunKeyListener(Model m){
        this.model = m;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_Z){
            for(IFlipper f1: model.getFlippers()){
                f1.setActive(true);
            }
        }
        System.out.println("Key pressed");
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("--------------");
            System.out.println("Absorber Launch Key pressed");
            if(model.getBall().stopped()) {
                model.launchBall();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_Z){
            for(IFlipper f1: model.getFlippers()){
                f1.setActive(false);
            }
        }
    }
}
