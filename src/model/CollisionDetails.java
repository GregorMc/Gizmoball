package model;

import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public  class CollisionDetails {
    private double tuc;
    private Vect velo;
    private IGizmo hitGiz;

    public CollisionDetails(double t, Vect v, IGizmo collisionGiz) {
        this.tuc = t;
        this.velo = v;
        this.hitGiz = collisionGiz;
    }

    public double getTuc() {
        return tuc;
    }

    public Vect getVelo() {
        return velo;
    }

    public IGizmo getHitGiz() { return hitGiz; }

}
