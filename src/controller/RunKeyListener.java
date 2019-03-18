package controller;

import model.IFlipper;
import model.Model;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
        System.out.println("Key pressed");

//        if(e.getKeyCode() == KeyEvent.VK_Z){
//            System.out.println("--------------");
//            System.out.println("Flipper Key pressed");
//            for(IFlipper f1: model.getFlippers()){
//                f1.setActive(true);
//            }
//        }
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

//        System.out.println("Key Released");
//        if(e.getKeyCode() == KeyEvent.VK_Z){
//            System.out.println("-----------------");
//            System.out.println("Flipper Key Released");
//            for(IFlipper f1: model.getFlippers()){
//                if(f1.isActivated()) {
//                    f1.setActive(false);
//                }
//            }
//        }
    }
}
