package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.*;
import model.Model;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunGui {

    private Model model;
    private JFrame frame;
    private ActionListener listener;
    private KeyListener fl, al;
    private KeyListener magicKeyListener, runKeyListener;

    private Board board;
    private Boolean runMode = false;

    private JPanel buildButtons, runButtons, buttons;
    private JMenuBar menuBar, runMenu, buildMenu;

    public RunGui(Model m) {

        model = m;

        buildButtons = new JPanel();
        runButtons = new JPanel();
        buttons = new JPanel();

        menuBar = new JMenuBar();
        runMenu = new JMenuBar();
        buildMenu = new JMenuBar();

        board = new Board(500, 500, model, this);

        // RunListener catches all GUI events. In reality might have many listeners.
        listener = new RunListener(model, this);
        runKeyListener = new RunKeyListener(model);
        magicKeyListener = new MagicKeyListener(runKeyListener);


    }

    public void createAndShowGUI() {

        //Create build and run components
        BuildComps makeBuildComp = new BuildComps(listener);
        RunComps makeRunComp = new RunComps(listener, fl, al);

        frame = new JFrame("Gizmoball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Board is passed the Model so it can act as Observer
        board = new Board(500, 500, model, this);

        board.addKeyListener(magicKeyListener);

        //board.addKeyListener(fl);
        //board.addKeyListener(al);

        Container cp = frame.getContentPane();

        buildMenu = makeBuildComp.createBuildMenu();
        runMenu = makeRunComp.createRunMenuBar();

        frame.setJMenuBar(buildMenu);

        Font gf = new Font("Arial", Font.BOLD, 12);

        buildButtons = makeBuildComp.createBuildButtons();
        runButtons = makeRunComp.createRunButtons();

        buttons.add(buildButtons);

        cp.add(buttons, BorderLayout.NORTH);
        cp.add(board, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void switchToRun(){


        //TODO switch menuBar
        frame.remove(buildMenu);
        frame.setJMenuBar(runMenu);

        //switch buttons
        buttons.remove(buildButtons);
        buttons.add(runButtons);

        runButtons.setVisible(true);
        buildButtons.setVisible(false);
        buttons.setVisible(true);


        runMode = true;
        SwingUtilities.updateComponentTreeUI(frame); //Updates GUI after mode has changed; fixme - later on model needs to know

    }

    public void switchToBuild(){

        frame.remove(runMenu);
        frame.setJMenuBar(buildMenu);

        //switch buttons
        buttons.remove(runButtons);
        buttons.add(buildButtons);

        runButtons.setVisible(false);
        buildButtons.setVisible(true);
        buttons.setVisible(true);

        runMode = false;
        SwingUtilities.updateComponentTreeUI(frame);

    }

    public boolean getRunMode() {
        return runMode;
    }


}
