package hero.gameobject;

import hero.core.CollisionBox;
import hero.game.state.State;
import hero.gfx.SpriteLibrary;
import hero.misc.Position;
import hero.misc.Size;

import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected Position position;
    protected Size size;

    public GameObject(Position position, Size size){
        this.position = position;
        this.size = size;
    }

    public abstract void update(State state);
    public abstract BufferedImage getSprite();
    public abstract CollisionBox getCollisionBox();
    public abstract boolean CollidesWith(CollisionBox other);

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }
}
