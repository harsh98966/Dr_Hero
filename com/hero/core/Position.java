package hero.core;

public class Position {

    public static final int PROXIMITY_RANGE = 5;

    private double x, y;

    public Position(double x, double y) {
        update(x, y);
    }

    public static Position copyOf(Position position) {
        return new Position(position.x, position.y);
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isInRangeOf(Position target) {
        double xx = Math.abs(target.getX() - x);
        double yy = Math.abs(target.getY() - y);
        return xx <= PROXIMITY_RANGE && yy <= PROXIMITY_RANGE;
    }

    public void applyVecOnX(Motion motion) {
        this.x += motion.getVector().getX();
    }

    public void applyVecOnY(Motion motion) {
        this.y += motion.getVector().getY();
    }

    public void add(Position position) {
        x += position.getX();
        y += position.getY();
    }

    public double distanceTo(Position other){
        double deltaX = x - other.getX();
        double deltaY = y - other.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

}
