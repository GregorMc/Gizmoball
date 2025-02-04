package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class RunComps {

    private ActionListener listener;
    private KeyListener flipListener, absorbListener;

    public RunComps(ActionListener l, KeyListener fl, KeyListener al){
        this.listener = l;
        this.flipListener = fl;
        this.absorbListener = al;
    }

    private Font gf = new Font("Arial", Font.BOLD, 12);

    public JMenuBar createRunMenuBar(){
        JMenuBar runMenu = new JMenuBar();

        JMenu physics = new JMenu("Physics");

        JMenuItem friction = new JMenuItem("Change Friction");
        physics.add(friction);

        JMenuItem gravity = new JMenuItem("Change Gravity");
        physics.add(gravity);

        runMenu.add(physics);

        return runMenu;
    }

    public JPanel createRunButtons(){
        JPanel runButtons = new JPanel();

        runButtons.setLayout(new GridLayout(1, 4));

        JButton button1 = new JButton("Start");
        button1.setFont(gf);
        button1.addActionListener(listener);
        button1.addKeyListener(flipListener);
        button1.addKeyListener(absorbListener);

        button1.setMaximumSize(new Dimension(100, 300));
        runButtons.add(button1);

        JButton button2 = new JButton("Stop");
        button2.setFont(gf);
        button2.addActionListener(listener);
        button2.setMaximumSize(new Dimension(100, 300));
        runButtons.add(button2);

        JButton button4 = new JButton("Build Mode");
        button4.setFont(gf);
        button4.addActionListener(listener);
        button4.setMaximumSize(new Dimension(100, 300));
        runButtons.add(button4);

        JButton button3 = new JButton("Load");
        button3.setFont(gf);
        button3.addActionListener(listener);
        button3.setMaximumSize(new Dimension(100, 300));
        runButtons.add(button3);

        return runButtons;
    }


}
