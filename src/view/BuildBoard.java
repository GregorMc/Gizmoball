package view;
import model.Ball;
import model.Model;
import model.VerticalLine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BuildBoard extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;
    protected int width;
    protected int height;
    protected Model m;

    public BuildBoard(int w,int h,int j,int a,Model m) {  //TODO include Model m     //a bunch of these parameters are unneccesary

        this.width = w;
        this.height = h;
        this.m = m;
        m.addObserver(this);
        this.setOpaque(true);
        this.setBackground(Color.WHITE); //doesn't work, only way I can get it to work is by adding a jpanel manually
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }


    //fix onscreen size
    public Dimension  getPrefferedSize() {
        return new Dimension(width,height);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        for ( int x = 0; x <= 475; x += 25 )
            for ( int y = 0; y <= 475; y += 25 )
                g.drawRect( x, y, 25, 25 );
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 500);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        //repaint(); /TODO why doens't this work?
        repaint();
    }



}
