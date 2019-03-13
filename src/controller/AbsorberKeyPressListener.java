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
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(m.getBall().stopped()) {
                m.getBall().start();
                m.setBallSpeed(0, -50*L);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
