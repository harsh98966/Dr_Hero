package hero.gameobject;

import hero.core.CollisionBox;
import hero.game.state.State;

import hero.misc.Position;
import hero.misc.Size;

import java.awt.image.BufferedImage;

public abstract class GameObject {

    protected GameObject parent;

    protected Position position;
    protected Size size;

    public GameObject(Position position, Size size){
        this.position = position;
        this.size = size;
    }

    public abstract void update(State state);
    public abstract BufferedImage getSprite();
    public abstract CollisionBox getCollisionBox();
    public boolean CollidesWith(CollisionBox other){
        return getCollisionBox().collidesWith(other);
    }

    public void addParent(GameObject parent){
        this.parent = parent;
    }

    public Position getPosition() {
        Position finalPos = Position.copyOf(position);
        if(parent != null){
            finalPos.add(parent.position);
        }
        return finalPos;
    }

    public Size getSize() {
        return size;
    }
}
