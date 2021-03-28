package hero.ui;

public class Alignment {
    public enum Position{
        START,
        CENTER,
        END
    }

    private Position horizontal;
    private Position vertical;

    public Alignment(Position horizontal, Position vertical){
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Position getVertical() {
        return vertical;
    }

    public void setVertical(Position vertical) {
        this.vertical = vertical;
    }

    public Position getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Position horizontal) {
        this.horizontal = horizontal;
    }
}
