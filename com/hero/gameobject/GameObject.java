package hero.gameobject;

import hero.core.CollisionBox;
import hero.game.state.State;

import hero.core.Position;
import hero.core.Size;

import java.awt.image.BufferedImage;

public abstract class GameObject {

    protected GameObject parent;

    protected int renderOrder;

    protected Position position;
    protected Position renderOffset;
    protected Size size;

    public GameObject(Position position, Size size){
        this.position = position;
        this.size = size;
        renderOffset = new Position(0, 0);
        renderOrder = 5;
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

    protected void clearParent(){
        parent = null;
    }

    public Position getPosition() {
        Position finalPos = Position.copyOf(position);
        if(parent != null){
            finalPos.add(parent.getPosition());
        }
        return finalPos;
    }

    public Size getSize() {
        return size;
    }

    public Position getRenderPosition(State state){
        return new Position(getPosition().intX() - state.getCamera().getPosition().intX() - renderOffset.intX(),
                getPosition().intY() - state.getCamera().getPosition().intY() - renderOffset.intY());
    }


    public int getRenderOrder() {
        return renderOrder;
    }
}
