package view;

import controller.EditGizmoListener;
import controller.PhysicsListener;
import controller.RunListener;
import controller.SelectGizmoListener;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildComps {

    private ActionListener listener, sg, eg, pl;
    private Model model;


    public BuildComps(ActionListener l, Model m){
        this.listener = l;
        this.model = m;
        this.sg = new SelectGizmoListener(model);
        this.eg = new EditGizmoListener(model);
        this.pl = new PhysicsListener(this, model);
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
        ball.addActionListener(sg);
        addGizmo.add(ball);

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
        square.addActionListener(sg);
        rightFlipper.addActionListener(sg);

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

        JMenuItem connect = new JMenuItem("Connect");
        connect.addActionListener(eg);
        editGizmo.add(connect);

        JMenuItem disconnect = new JMenuItem("Disconnect");
        disconnect.addActionListener(eg);
        editGizmo.add(disconnect);

        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(eg);
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
        quit.setMaximumSize(new Dimension(100,300));
        quit.setFont(gf);

        buildButtons.add(switchMode);
        buildButtons.add(quit);

        return buildButtons;
    }

    public void promptFriction() {
        JTextField mu = new JTextField();
        JTextField mu2 = new JTextField();

        String[] options = {"OK","Cancel"};
        Object[] fields = {
                "New MU value", mu,
                "New MU2 value", mu2
        };

        int result = JOptionPane.showOptionDialog(null,fields,"Change Friction",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,options,options[0]);


        if (result == JOptionPane.OK_OPTION) {

            String ad = mu.getText();
            String add = mu2.getText();

            if (ad.isEmpty()|| add.isEmpty()) {
                JOptionPane.showMessageDialog(null, "please enter values for both mu and mu2");
                return;
            }

            model.setMU(Double.parseDouble(mu.getText()));
            model.setMU2(Double.parseDouble(mu2.getText()));
        }

    }

    public String getInput(String message){
        return JOptionPane.showInputDialog(message);
    }

}
