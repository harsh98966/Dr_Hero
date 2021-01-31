package sam.gameobject;

import sam.misc.Position;
import sam.misc.Size;

import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected Position position;
    protected Size size;

    public GameObject(Position position, Size size){
        this.position = position;
        this.size = size;
    }

    public abstract void update();
    public abstract BufferedImage getSprite();

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }
}
