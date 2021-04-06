package hero.core;

public enum Direction {
    S,
    SW,
    W,
    NW,
    N,
    NE,
    E,
    SE;

    public static Direction fromMotion(Motion motion) {
        double x = motion.getVector().getX();
        double y = motion.getVector().getY();

        if (x == 0 && y > 0) return S;
        else if (x == 0 && y < 0) return N;
        else if (x < 0 && y == 0) return W;
        else if (x > 0 && y == 0) return E;
        else if (x < 0 && y < 0) return NW;
        else if (x < 0 && y > 0) return SW;
        else if (x > 0 && y < 0) return NE;
        else if (x > 0 && y > 0) return SE;
        return null;
    }

    public static Vector2d vectorFromDirection(Direction direction, Vector2d vector2d) {
        double x = vector2d.getX();
        double y = vector2d.getY();

        if (direction != null) {
            switch (direction) {
                case N -> {
                    x = 0;
                    y = -1;
                }
                case E -> {
                    x = 1;
                    y = 0;
                }
                case S -> {
                    x = 0;
                    y = 1;
                }
                case W -> {
                    x = -1;
                    y = 0;
                }
                case NE -> {
                    x = 1;
                    y = -1;
                }
                case NW -> {
                    x = -1;
                    y = -1;
                }
                case SE -> {
                    x = 1;
                    y = 1;
                }
                case SW -> {
                    x = -1;
                    y = 1;
                }
            }
        }


        return new Vector2d(x, y);
    }
}
