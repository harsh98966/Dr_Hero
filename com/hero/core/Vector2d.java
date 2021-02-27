package hero.core;

public class Vector2d {
    private double x, y;
    public Vector2d(double x, double y){
        update(x, y);
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void multiply(double velocity) {
        x *= velocity;
        y *= velocity;
    }

    public void normalize(){
        double length = Math.sqrt(x * x + y * y);
        x = x == 0 ? 0 : x / length;
        y = y == 0 ? 0 : y / length;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }
}
