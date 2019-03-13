package view;

import controller.RunListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildComps {

    private ActionListener listener;

    public BuildComps(ActionListener l){
        this.listener = l;
    }

    private Font gf = new Font("Arial", Font.BOLD, 12);

    public JMenuBar createBuildMenu(){
        JMenuBar buildMenuBar = new JMenuBar();

        //Different menu tabs - Add Gizmo's, Edit Gizmos, Physics, File
        JMenu addGizmo = new JMenu("Add Gizmo");
        JMenu editGizmo = new JMenu("Edit Gizmo");
        JMenu fileSL = new JMenu("File");
        JMenu physics = new JMenu("Physics");

        //Add Gizmo types
        JMenuItem ball = new JMenuItem("Ball");
        addGizmo.add(ball);
        JMenuItem square = new JMenuItem("Square");
        addGizmo.add(square);
        JMenuItem circleGiz = new JMenuItem("Circle");
        addGizmo.add(circleGiz);
        JMenuItem triangle = new JMenuItem("Triangle");
        addGizmo.add(triangle);
        JMenuItem leftFlipper = new JMenuItem("Left Flipper");
        addGizmo.add(leftFlipper);
        JMenuItem rightFlipper = new JMenuItem("Right Flipper");
        addGizmo.add(rightFlipper);
        JMenuItem absorber = new JMenuItem("Absorber");
        addGizmo.add(absorber);
        //Add Tab to menuBar
        buildMenuBar.add(addGizmo);

        //Edit Gizmo options
        JMenuItem rotate = new JMenuItem("Rotate");
        editGizmo.add(rotate);
        JMenuItem move = new JMenuItem("Move");
        editGizmo.add(move);
        JMenuItem connect = new JMenuItem("Connect");
        editGizmo.add(connect);
        JMenuItem disconnect = new JMenuItem("Disconnect");
        editGizmo.add(disconnect);
        JMenuItem delete = new JMenuItem("Delete");
        editGizmo.add(delete);
        //Add tab to menuBar
        buildMenuBar.add(editGizmo);

        //File options
        JMenuItem save = new JMenuItem("Save Board");
        fileSL.add(save);
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(listener);
        fileSL.add(load);
        //Add tab to menuBar
        buildMenuBar.add(fileSL);

        //Physics options
        JMenuItem gravity = new JMenuItem("Change Gravity");
        physics.add(gravity);
        JMenuItem friction = new JMenuItem("Change Friction");
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
        quit.setMaximumSize(new Dimension(100,300));
        quit.setFont(gf);

        buildButtons.add(switchMode);
        buildButtons.add(quit);

        return buildButtons;
    }
}
