package hero.gameobject;

import hero.controller.Controller;
import hero.core.CollisionBox;
import hero.core.Direction;
import hero.core.Motion;
import hero.game.Game;
import hero.game.action.Action;
import hero.game.effects.Effect;
import hero.game.effects.Sick;
import hero.game.state.State;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.misc.Position;
import hero.misc.Size;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    protected List<Effect> effects;
    protected Optional<Action> action;

    protected Size collisionBoxSize;

    public MovingEntity(Position position, Size size, Controller controller) {
        super(position, size);
        this.controller = controller;
        motion = new Motion(2);
        direction = Direction.S;
        effects = new ArrayList<>();
        action = Optional.empty();
        collisionBoxSize = new Size(16, 32);
    }

    protected abstract void initAnimationManager(SpriteLibrary spriteLibrary);

    @Override
    public void update(State state) {

        if (action.isEmpty()) {
            motion.update(controller);
        } else {
            motion.stop(true, true);
            action.get().update(state, this);
        }

        direction = Direction.fromMotion(motion);
        animationManager.update(direction);

        handleCollisions(state);
        decideAnimation();
        playEffect(state);

        if (action.isPresent() && action.get().isDone()) action = Optional.empty();
        position.applyVec(motion.getVector());
    }

    private void handleCollisions(State state) {
        List<GameObject> collidingWith = state.getCollidingGameObject(this);
        collidingWith.forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject gameObject);

    private void playEffect(State state) {
        ListIterator<Effect> effectListIterator = effects.listIterator();
        while (effectListIterator.hasNext()) {
            Effect currEffect = effectListIterator.next();
            if (currEffect.shouldDelete()) effectListIterator.remove();
            else currEffect.update(state, this);
        }

    }

    protected void decideAnimation() {
        if (action.isPresent()) animationManager.setAnimation(action.get().getAnimationName());
        else if (motion.isMoving()) animationManager.setAnimation("walk");
        else animationManager.setAnimation("stand");
    }

    @Override
    public BufferedImage getSprite() {
        return animationManager.getSprite();
    }

    public Controller getController() {
        return controller;
    }

    public void performAction(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(position);
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

    @Override
    public boolean CollidesWith(CollisionBox other) {
        return getCollisionBox().collidesWith(other);
    }

    public boolean willCollideX(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position posWithAppliedX = Position.copyOf(position);
        posWithAppliedX.applyVecOnX(motion);
        return CollisionBox.of(posWithAppliedX, size).collidesWith(otherBox);
    }

    public boolean willCollideY(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position posWithAppliedY = Position.copyOf(position);
        posWithAppliedY.applyVecOnY(motion);
        return CollisionBox.of(posWithAppliedY, size).collidesWith(otherBox);
    }
}
