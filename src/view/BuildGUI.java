package view;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BuildGUI extends JPanel implements IGBallGUI{

    public BuildGUI() {
        createBoard();
    }

    public JPanel createButtons() {
        JButton play = new JButton("Play");
        JButton quit = new JButton("Quit");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(play);
        buttonPanel.add(quit);
        return buttonPanel;
    }

    @SuppressWarnings("Duplicates")
    public JPanel createMsgField() {
        String message = "Message Goes Here";
        JLabel msgLabel= new JLabel(message);
        JPanel msgPanel = new JPanel();
        msgPanel.add(msgLabel);
        return msgPanel;
    }

    @SuppressWarnings("Duplicates")
    public JMenuBar createMenuBar() {
        JMenuBar runMenuBar = new JMenuBar();

        //menus
        JMenu gizmo = new JMenu("Gizmo's");
        JMenu edit = new JMenu("Edit");
        JMenu file = new JMenu("File");
        JMenu physics = new JMenu("Physics");

        //gizmo menu items
        JMenuItem ball = new JMenuItem("Ball");
        JMenuItem square = new JMenuItem("Square");
        JMenuItem circle = new JMenuItem("Circle");
        JMenuItem triangle = new JMenuItem("Triangle");
        JMenuItem leftFlipper = new JMenuItem("Left Flipper");
        JMenuItem rightFlipper = new JMenuItem("Right Flipper");
        JMenuItem absorber = new JMenuItem("Absorber");

        //edit menu items
        JMenuItem rotate = new JMenuItem("Rotate");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem move = new JMenuItem("Move");
        JMenuItem connect = new JMenuItem("Connect");
        JMenuItem disconnect = new JMenuItem("Disconnect");


        //file menu items
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");

        //physics menu items
        JMenuItem gravity = new JMenuItem("Gravity");
        JMenuItem friction = new JMenuItem("Friction");

        //gizmo add
        gizmo.add(ball);
        gizmo.add(square);
        gizmo.add(circle);
        gizmo.add(triangle);
        gizmo.add(leftFlipper);
        gizmo.add(rightFlipper);
        gizmo.add(absorber);

        //edit add
        edit.add(rotate);
        edit.add(delete);
        edit.add(move);
        edit.add(connect);
        edit.add(disconnect);

        //file add
        file.add(load);
        file.add(save);

        //physics add
        physics.add(gravity);
        physics.add(friction);

        runMenuBar.add(gizmo);
        runMenuBar.add(file);
        runMenuBar.add(edit);
        runMenuBar.add(physics);
        return runMenuBar;
    }

    public void createBoard() {
        JPanel buttons = createButtons();
        JPanel msg = createMsgField();
        this.add(buttons);
        this.add(msg);
    }


    public void showError() {
        //TODO
    }


    public void showInfo() {
        //TODO
    }


    public void getInput() {
        //TODO what would this do?
    }


}
