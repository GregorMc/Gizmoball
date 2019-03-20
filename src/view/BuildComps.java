package view;

import controller.*;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildComps {

    private ActionListener listener, sg, eg, pl, cl, fl;
    private Model model;
    private RunGui gui;


    public BuildComps(ActionListener l, Model m, RunGui g){

        this.model = m;
        this.gui = g;

        this.listener = l;
        this.sg = new SelectGizmoListener(model, gui);
        this.eg = new EditGizmoListener(gui, model);
        this.pl = new PhysicsListener(gui, model);
        this.cl = new ConnectionListener(gui, model);
        this.fl = new FileListener(model, gui);
    }

    private Font gf = new Font("Arial", Font.BOLD, 12);

    public JMenuBar createBuildMenu(){
        JMenuBar buildMenuBar = new JMenuBar();

        //Different menu tabs - Add Gizmo's, Edit Gizmos, Physics, File
        JMenu addGizmo = new JMenu("Add Gizmo");
        JMenu editGizmo = new JMenu("Edit Gizmo");
        JMenu connections = new JMenu("Connections");
        JMenu fileSL = new JMenu("File");
        JMenu physics = new JMenu("Physics");

        //Add Gizmo types
        JMenuItem ball = new JMenuItem("Move Ball");
        ball.setActionCommand("Ball");
        ball.addActionListener(sg);
        addGizmo.add(ball);

        addGizmo.addSeparator();

        JMenuItem square = new JMenuItem("Square");
        square.addActionListener(sg);
        addGizmo.add(square);

        JMenuItem circleGiz = new JMenuItem("Circle");
        circleGiz.addActionListener(sg);
        addGizmo.add(circleGiz);

        JMenuItem triangle = new JMenuItem("Triangle");
        triangle.addActionListener(sg);
        addGizmo.add(triangle);

        JMenuItem leftFlipper = new JMenuItem("Left Flipper");
        leftFlipper.addActionListener(sg);
        addGizmo.add(leftFlipper);

        JMenuItem rightFlipper = new JMenuItem("Right Flipper");
        rightFlipper.addActionListener(sg);
        addGizmo.add(rightFlipper);

        JMenuItem absorber = new JMenuItem("Absorber");
        absorber.addActionListener(sg);
        addGizmo.add(absorber);
        //Add Tab to menuBar
        buildMenuBar.add(addGizmo);

        //Edit Gizmo options
        JMenuItem rotate = new JMenuItem("Rotate");
        rotate.addActionListener(eg);
        editGizmo.add(rotate);

        JMenuItem move = new JMenuItem("Move");
        move.addActionListener(eg);
        editGizmo.add(move);

        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(eg);
        editGizmo.add(delete);
        //Add tab to menuBar
        buildMenuBar.add(editGizmo);

        JMenuItem gizCon = new JMenuItem("Connect Gizmo's");
        gizCon.addActionListener(cl);
        connections.add(gizCon);

        JMenuItem gizDisCon = new JMenuItem("Disconnect Gizmo's");
        gizDisCon.addActionListener(cl);
        connections.add(gizDisCon);

        connections.addSeparator();

        JMenuItem keyCon = new JMenuItem("KeyConnect Gizmo");
        keyCon.addActionListener(cl);
        connections.add(keyCon);

        JMenuItem keyDisCon = new JMenuItem("Remove KeyConnect");
        keyDisCon.addActionListener(cl);
        connections.add(keyDisCon);
        buildMenuBar.add(connections);

        //File options FIXME NEEDS LISTENER
        JMenuItem save = new JMenuItem("Save Board");
        save.addActionListener(fl);
        fileSL.add(save);

        JMenuItem load = new JMenuItem("Load Board");
        load.addActionListener(fl);
        fileSL.add(load);

        fileSL.addSeparator();

        JMenuItem reload = new JMenuItem("Reload Board");
        reload.addActionListener(fl);
        fileSL.add(reload);
        //Add tab to menuBar
        buildMenuBar.add(fileSL);

        //Physics options
        JMenuItem gravity = new JMenuItem("Change Gravity");
        gravity.addActionListener(pl);
        physics.add(gravity);

        JMenuItem friction = new JMenuItem("Change Friction");
        friction.addActionListener(pl);
        physics.add(friction);
        //Add tab to menuBar
        buildMenuBar.add(physics);


        buildMenuBar.setFont(gf);
        return buildMenuBar;
    }

    public JPanel createBuildButtons(){
        JPanel buildButtons = new JPanel();
        buildButtons.setLayout(new GridLayout(1,2));

        JButton switchMode = new JButton("Run Mode");
        switchMode.addActionListener(listener);
        switchMode.setMaximumSize(new Dimension(100,300));
        switchMode.setFont(gf);

        JButton quit = new JButton("Quit");
        quit.addActionListener(listener);
        quit.setMaximumSize(new Dimension(100,300));
        quit.setFont(gf);

        buildButtons.add(switchMode);
        buildButtons.add(quit);

        return buildButtons;
    }


    public String getInput(String message){
        return JOptionPane.showInputDialog(message);
    }

}
