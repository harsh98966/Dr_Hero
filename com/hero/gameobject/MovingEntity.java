package hero.gameobject;

import hero.controller.EntityController;
import hero.core.*;

import hero.game.state.State;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    protected Vector2d directionVector;
    protected Size collisionBoxSize;

    public MovingEntity(Position position, Size size, EntityController entityController) {
        super(position, size);
        this.entityController = entityController;
        motion = new Motion(2);
        direction = Direction.S;
        directionVector = new Vector2d(0, 0);
        collisionBoxSize = new Size(16, 32);

        renderOffset = new Position(size.getWidth() / 2, size.getHeight() / 2);
    }

    protected abstract void initAnimationManager(SpriteLibrary spriteLibrary);

    @Override
    public void update(State state) {
        motion.update(entityController);
        handleMotion();
        direction = Direction.fromMotion(motion);
        animationManager.update(direction);

        handleCollisions(state);
        animationManager.setAnimation(decideAnimation());
        directionVector = Direction.vectorFromDirection(direction, directionVector);

        position.applyVec(motion.getVector());

    }

    private void handleCollisions(State state) {
        List<GameObject> collidingWith = state.getCollidingGameObject(this);
        collidingWith.forEach(this::handleCollision);
    }

    protected abstract void handleMotion();
    protected abstract void handleCollision(GameObject gameObject);

    protected abstract String decideAnimation();
    @Override
    public BufferedImage getSprite() {
        return animationManager.getSprite();
    }

    public EntityController getController() {
        return entityController;
    }


    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(getPosition());
        positionWithMotion.applyVec(motion.getVector());
        return new CollisionBox(
                new Rectangle(
                        positionWithMotion.intX(),
                        positionWithMotion.intY(),
                        collisionBoxSize.getWidth(),
                        collisionBoxSize.getHeight()
                )
        );
    }

    public boolean willCollideX(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position posWithAppliedX = Position.copyOf(position);
        posWithAppliedX.applyVecOnX(motion);
        return CollisionBox.of(posWithAppliedX, size).collidesWith(otherBox);
    }

    public boolean willCollideY(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position posWithAppliedY = Position.copyOf(position);
        posWithAppliedY.applyVecOnY(motion);
        return CollisionBox.of(posWithAppliedY, size).collidesWith(otherBox);
    }

    protected boolean isFacing(Position other) {
        Vector2d direction = Vector2d.directionBetweenPositions(other, getPosition());
        return Vector2d.dotProduct(direction, directionVector) > 0;
    }

}
