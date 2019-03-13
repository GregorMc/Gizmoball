package model;

import com.sun.org.apache.xpath.internal.operations.Mod;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

    private List<IGizmo> gizmos;
    private List<IFlipper> flippers;

    private int gravity;

    private Ball ball;
    private Walls walls;
    private double MU;
    private double MU2;

    public Model(){
        this.ball = new Ball("FB",1,1,10,10);
        this.walls = new Walls(0,0,20,20);

        this.gizmos = new ArrayList<>();
        this.flippers = new ArrayList<>();

        this.MU = 0.025D;
        this.MU2 = 0.025D;
        this.gravity = 25;

    }

    public void moveBall(){

        double moveTime = 0.05D; // 0.05 = 20 times per second as per Gizmoball

        if (ball != null && !ball.stopped()) {

            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                // No collision ...
                ball = movelBallForTime(ball,moveTime);

            } else {
                // We've got a collision in tuc
                ball = movelBallForTime(ball, tuc);
                // Post collision velocity ...
                ball.setVelo(cd.getVelo());
            }

            applyGravity(moveTime);
            applyFriciton(moveTime);
            // No satisficingtify observers ... redraw updated view
            this.setChanged();
            this.notifyObservers();
        }

    }

    private void applyGravity(double time){
        double yafter = ball.getVelo().y() + gravity * time;
        ball.setVelo(new Vect(ball.getVelo().x(), yafter));
    }

    private void applyFriciton(double time){
        double xafter = ball.getVelo().x() * (1.0 - (MU * time) - (MU2 * (Math.abs(ball.getVelo().length()) * time)));
        double yafter = ball.getVelo().y() * (1.0 - (MU * time) - (MU2 * (Math.abs(ball.getVelo().length()) * time)));
        ball.setVelo(new Vect(xafter, yafter));
    }

    public CollisionDetails timeUntilCollision(){
        Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();
        Vect newVelo = new Vect(0,0);

        double shortestTime = Double.MAX_VALUE;
        double time = 0.0;

        //Time to Collide with 4 walls
        ArrayList<LineSegment> lss = walls.getLineSegments();
        for (LineSegment line : lss) {
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelo);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
            }
        }

        //Time to collide with any Circle Gizmos
        //FIXME a lot of repeating code, maybe try get working with gizmo interface?
        for(IGizmo g: gizmos) {
                for (LineSegment lineSegs : g.getLineSegments()) {
                    time = Geometry.timeUntilWallCollision(lineSegs, ballCircle, ballVelo);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegs, ballVelo, 1.0);
                        if(g instanceof Absorber && time < 0.01){
                            ball.stop();
                            ball.setExactX((((Absorber) g).getXpos2()-0.5)*25);
                            ball.setExactY((((Absorber) g).getYpos2()-0.75)*25);
                        }
                    }
                }
                //Time to collide with square circles
                for (Circle c : g.getCircles()) {
                    time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelo);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(c.getCenter(), (ball.getCircle().getCenter()), ballVelo);
                    }
                }
            }
        return new CollisionDetails(shortestTime,newVelo);
    }

    private Ball movelBallForTime(Ball b, double time) {

        double newX = 0.0;
        double newY = 0.0;
        double xVel = b.getVelo().x();
        double yVel = b.getVelo().y();
        newX = b.getExactX() + (xVel * time);
        newY = b.getExactY() + (yVel * time);
        b.setExactX(newX);
        b.setExactY(newY);
        return b;
    }

    //Adding gizmos
    public boolean addGizmo(IGizmo giz){
        if(!gizmos.contains(giz)){
            gizmos.add(giz);
            return true;
        } else {
            //FIXME sout for Testing
            System.out.println("Gizmo already exists");
            return false;
        }
    }

    public boolean addFlipper(IFlipper flipper){
        if(flippers.contains(flipper)){
            System.out.println("Flipper alreaday exists");
            return false;
        } else {
            flippers.add(flipper);
            return true;
        }
    }

    public IGizmo getGizmoByID(String gizID){
        for(IGizmo gizmo: gizmos){
            if(gizmo.getID().equals(gizID)) {
                return gizmo;
            }
        }
        return new Square("SFake", -1,-1);
    }

    public void moveFlippers() {
        for (IFlipper fl : flippers) {

            if (fl.isActivated()) {
                if (fl.getAngle() < 90) {
                    fl.setAngle(fl.getAngle() + 5);
                }
            } else {
                if (fl.getAngle() > 0) {
                    fl.setAngle(fl.getAngle() - 5);
                }
            }

            this.setChanged();
            this.notifyObservers();
        }
    }


    public void loadBoard() {
        gizmos.clear();

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Open");
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (fc.getSelectedFile().isFile()) {
                File file = fc.getSelectedFile();
                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        String[] args = line.split(" ");
                        System.out.println("-----------------");
                        if (args.length > 0) {
                            switch (args[0]) {
                                case "Triangle":
                                    addGizmo(new Triangle(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                                    break;
                                case "Rotate":
                                    IGizmo gizmo = getGizmoByID(args[1]);
                                    Triangle t = (Triangle) gizmo;
                                    t.rotateTriangle();
                                    break;
                                case "Connect":
                                    System.out.println("Connect - Do nothing");
                                    break;
                                case "Square":
                                    addGizmo(new Square(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                                    break;
                                case "Circle":
                                    addGizmo(new CircleGiz(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                                    break;
                                case "LeftFlipper":
                                    addFlipper(new LeftFlipper(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                                    break;
                                case "RightFlipper":
                                    addFlipper(new RightFlipper(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                                    break;
                                case "Absorber":
                                    addGizmo(new Absorber(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5])));
                                    break;
                                case "Ball":
                                    ball = new Ball(args[1], Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
                                    break;
                                case "KeyConnect":
                                    System.out.println("Key Connect - Do nothing");
                                default:
                                    System.out.println("Failed to load line:\n" + line);
                                    break;
                            }
                        }

                        line = bufferedReader.readLine();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.setChanged();
        this.notifyObservers();
    }

    //Getters and Setters.
    public List<IGizmo> getGizmos() {
        return gizmos;
    }

    public List<IFlipper> getFlippers() { return flippers; }


    public Ball getBall(){
        return ball;
    }

    public void setBallSpeed(double x, double y) {
        ball.setVelo(new Vect(x, y));
    }

    public void setMU(double mu){
        this.MU = mu;
    }

    public void setMU2(double mu2) {
        this.MU2 = mu2;
    }

    public double getMU() {
        return MU;
    }

    public double getMU2() {
        return MU2;
    }
}
