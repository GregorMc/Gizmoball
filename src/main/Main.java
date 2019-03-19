package main;

import model.*;
import view.RunGui;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            System.out.println("Look and feel error in main");
        }

        Model model = new Model();

        //FIXME Hard coded playing board

        //adding some balls, low priority
        //model.addBall(new Ball("B1",1,1,100,100));

        //Squares along top
        model.addGizmo(new Triangle("T2",1,18));
        model.addGizmo(new Triangle("T3", 19,0));
        IGizmo temp = model.getGizmoByID("T3");
        IGizmo T3 = (Triangle) temp;
        ((Triangle) T3).rotateTriangle();


        model.addGizmo(new Square("S02",0,2));
        model.addGizmo(new Square("S12",1,2));
        model.addGizmo(new Square("S22",2,2));
        model.addGizmo(new Square("S32",3,2));
        model.addGizmo(new Square("S42",4,2));

        model.addGizmo(new Square("S52",5,2));
        model.addGizmo(new Square("S62",6,2));
        model.addGizmo(new Square("S72",7,2));
        model.addGizmo(new Square("S82",8,2));
        model.addGizmo(new Square("S132",13,2));

        model.addGizmo(new Square("S142",14,2));
        model.addGizmo(new Square("S152",15,2));
        model.addGizmo(new Square("S162",16,2));
        model.addGizmo(new Square("S172",17,2));
        model.addGizmo(new Square("S182",18,2));

        //Diagonal line of circles
        model.addGizmo(new CircleGiz("C43",4,3));
        model.addGizmo(new CircleGiz("C54",5,4));
        model.addGizmo(new CircleGiz("C65",6,5));
        model.addGizmo(new CircleGiz("C76",7,6));
        model.addGizmo(new CircleGiz("C99",9,9));
        model.addGizmo(new CircleGiz("C109",10,9));
        model.addGizmo(new CircleGiz("C1110",11,10));

        model.addGizmo(new CircleGiz("C129",12,9));
        model.addGizmo(new CircleGiz("C139",13,9));
        model.addGizmo(new CircleGiz("C156",15,6));
        model.addGizmo(new CircleGiz("C165",16,5));
        model.addGizmo(new CircleGiz("C174",17,4));
        model.addGizmo(new CircleGiz("C183",18,3));

        model.addGizmo(new Absorber("A",0,19,20,20));


        model.addFlipper(new LeftFlipper("LF1", 10,17)); //TODO using old co-ordinate system
        model.addFlipper(new RightFlipper("RF1", 12, 17));  //TODO using old co-ordinate system


        model.addGizmo(new Triangle("T6",4,8));

        model.addGizmo(new Triangle("T7",4,9));
        model.addGizmo(new Triangle("T8",4,10));
        model.addGizmo(new Triangle("T9",4,11));
        model.addGizmo(new Triangle("T10",4,12));

        model.addKeyConnect("A","32","down");

        T3.addGizConnect(model.getGizmoByID("S02"));
        T3.addGizConnect(model.getGizmoByID("S12"));
        T3.addGizConnect(model.getGizmoByID("S22"));
        T3.addGizConnect(model.getGizmoByID("S32"));
        T3.addGizConnect(model.getGizmoByID("S42"));
        T3.addGizConnect(model.getGizmoByID("S52"));
        T3.addGizConnect(model.getGizmoByID("S62"));
        T3.addGizConnect(model.getGizmoByID("S72"));
        T3.addGizConnect(model.getGizmoByID("S82"));
        T3.addGizConnect(model.getGizmoByID("S132"));
        T3.addGizConnect(model.getGizmoByID("S142"));
        T3.addGizConnect(model.getGizmoByID("S152"));

        int x = 5, y = 7;
        System.out.println("s"+x+y);

        RunGui gui = new RunGui(model);
        gui.createAndShowGUI();
    }
}
