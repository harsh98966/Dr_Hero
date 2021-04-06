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

    public static Vector2d directionBetweenPositions(Position start, Position end){
        Vector2d direction = new Vector2d(start.getX() - end.getX(), start.getY() - end.getY());
        direction.normalize();
        return direction;
    }


    public static double dotProduct(Vector2d v1, Vector2d v2){
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }


    public static Vector2d copyOf(Vector2d vector2d){
        return new Vector2d(vector2d.getX(), vector2d.getY());
    }






}
