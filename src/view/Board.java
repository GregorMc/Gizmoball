package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.MouseEventListener;
import model.*;
import physics.Circle;


public  class Board extends JPanel implements Observer {

    protected int width;
    protected int height;
    protected Model gm;
    private RunGui gui;
    //fixme Model is 20Lx20L and Board is difference coordinates - multiply by L to get pos on board & divide to get pos on model.
    protected final int L = 25;

    private MouseEventListener ml;

    public Board(int w, int h, Model m, RunGui g) {
        // Observe changes in Model
        this.gui = g;
        m.addObserver(this);
        width = w;
        height = h;
        gm = m;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.ml = new MouseEventListener(gm);
        this.addMouseListener(ml);
    }

    // Fix onscreen size
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        //draw grid lines
        if(!gui.getRunMode()){
            int hgtOfRw = height/20, wdtOfRw = width/20;
            for(int i=0; i<20; i++){
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(Color.PINK);
                g2.drawLine(0, i*hgtOfRw, width, i*hgtOfRw);
                g2.drawLine(i*wdtOfRw, 0, i*wdtOfRw, height);
            }
        }

        //FIXME Attempt to draw all gizmos
        for(IGizmo gizmo: gm.getGizmos()){
            g2.setColor(gizmo.getGizColour());
            if(gizmo instanceof Triangle){
                Triangle t = (Triangle)gizmo;
                g2.fillPolygon(t.getXpos(),t.getYpos(),3);
            } else if(gizmo instanceof  Square){
                Square s = (Square)gizmo;
                g2.fillRect(s.getXpos(),s.getYpos(), L, L);
            } else if(gizmo instanceof CircleGiz){
                CircleGiz c = (CircleGiz) gizmo;
                Ellipse2D.Double circle = new Ellipse2D.Double(c.getXPosinP(),c.getYPosinP(), L, L);
                g2.fill(circle);
            } else if (gizmo instanceof Absorber){
                Absorber a = (Absorber) gizmo;
                g2.fillRect(a.getXpos1InL(),a.getYpos1InL(),Math.abs(a.getXpos2InL()-a.getXpos1InL()),Math.abs(a.getYpos2InL()-a.getYpos1InL()));
            } //FIXME draw flippers seperate
        }


        for(IFlipper fl: gm.getFlippers()){
            AffineTransform t = new AffineTransform();
            RoundRectangle2D.Double flipper = new RoundRectangle2D.Double(fl.getXpos(), fl.getYpos(), 12, 2 * L, 12, 12);  //TODO change width/height to make it a vertical/horizontal flipper

            t.rotate(Math.toRadians(fl.getAngle()), fl.getXpos(), fl.getYpos());

                    if (fl.getType().equals("LEFT")) {

                        try {
                            t.invert();
                        } catch (NoninvertibleTransformException e) {
                            e.printStackTrace();
                        }
                    }

            //some stuff to invert it
            Shape newFlipper = t.createTransformedShape(flipper);
            g2.fill(newFlipper);

        }

        Ball b = gm.getBall();
        if (b != null) {
            g2.setColor(b.getColour());
            int x = (int) (b.getExactX() - b.getRadius());
            int y = (int) (b.getExactY() - b.getRadius());
            int width = (int) (2 * b.getRadius());
            g2.fillOval(x, y, width, width);
        }

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        repaint();
        this.requestFocus();
    }

}
