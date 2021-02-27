package hero.misc;

import hero.core.Vector2d;

public class Position {

    private double x, y;

    public Position(double x, double y) {
        update(x, y);
    }
    
    public void update(double x, double y){
        this.x = x;
        this.y = y;
    }

    public int intX(){ return (int)Math.round(x); }

    public int intY() {
        return (int)Math.round(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void applyVec(Vector2d vector) {
        x += vector.getX();
        y += vector.getY();
    }
}
