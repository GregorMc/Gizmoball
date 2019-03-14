package controller;

import model.IFlipper;
import model.Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class AbsorberKeyPressListener implements KeyListener{
    private Model m;
    private static final int L = 25;


    public AbsorberKeyPressListener(Model m){
        this.m = m;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed");
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("--------------");
            System.out.println("Absorber Launch Key pressed");
            if(m.getBall().stopped()) {
                m.launchBall();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Ket Released");
    }


}
