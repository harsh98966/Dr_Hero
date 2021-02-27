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

    public static Direction fromMotion(Motion motion){
        double x = motion.getVector().getX();
        double y = motion.getVector().getY();

        if(x == 0 && y > 0) return S;
        else if(x == 0 && y < 0) return N;
        else if(x < 0 && y == 0) return W;
        else if(x > 0 && y == 0) return E;
        else if(x < 0 && y < 0) return NW;
        else if(x < 0 && y > 0) return SW;
        else if(x > 0 && y < 0) return NE;
        else if(x > 0 && y > 0) return SE;
        return null;
    }
}
