package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.*;
import model.Model;


public class RunGui {

    private Model model;
    private JFrame frame;
    private ActionListener listener;
    private KeyListener magicKeyListener, runKeyListener;

    private Color textBoxColour = Color.WHITE;

    private Board board;
    private Boolean runMode = false;

    private JPanel buildButtons, runButtons, buttons, textbox;
    private JMenuBar menuBar, runMenu, buildMenu;
    private JLabel textLabel;

    public RunGui(Model m) {

        model = m;

        buildButtons = new JPanel();
        runButtons = new JPanel();
        buttons = new JPanel();

        menuBar = new JMenuBar();
        runMenu = new JMenuBar();
        buildMenu = new JMenuBar();

        this.textbox = new JPanel();
        this.textbox.setBackground(textBoxColour);

        this.textLabel = new JLabel();

        board = new Board(500, 500, model, this);

        // RunListener catches all GUI events. In reality might have many listeners.
        listener = new RunListener(model, this);
        runKeyListener = new RunKeyListener(model);
        magicKeyListener = new MagicKeyListener(runKeyListener);


    }

    public void createAndShowGUI() {

        //Create build and run components
        BuildComps makeBuildComp = new BuildComps(listener, model,this);
        RunComps makeRunComp = new RunComps(listener);

        frame = new JFrame("Gizmoball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Board is passed the Model so it can act as Observer
        board = new Board(500, 500, model, this);

        board.addKeyListener(magicKeyListener);

        Container cp = frame.getContentPane();

        buildMenu = makeBuildComp.createBuildMenu();
        runMenu = makeRunComp.createRunMenuBar();

        frame.setJMenuBar(buildMenu);

        Font gf = new Font("Arial", Font.BOLD, 12);

        buildButtons = makeBuildComp.createBuildButtons();
        runButtons = makeRunComp.createRunButtons();

        buttons.add(buildButtons);

        textLabel.setText("Welcome to Gizmoball");
        textbox.add(textLabel);

        cp.add(buttons, BorderLayout.NORTH);
        cp.add(board, BorderLayout.CENTER);
        cp.add(textbox, BorderLayout.SOUTH);

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
        model.setRunMode(runMode);

        textLabel.setText("Welcome to run mode.");

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
        model.setRunMode(runMode);

        textLabel.setText("Welcome to build mode.");

        SwingUtilities.updateComponentTreeUI(frame);

    }

    public boolean getRunMode() {
        return runMode;
    }

    public void changeText(String message){
        if(textBoxColour == Color.WHITE){
            textBoxColour = Color.YELLOW;
            textbox.setBackground(textBoxColour);
        } else {
            textBoxColour = Color.WHITE;
            textbox.setBackground(textBoxColour);
        }
        textLabel.setText(message);
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
