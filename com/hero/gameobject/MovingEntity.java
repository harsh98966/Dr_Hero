package hero.gameobject;

import hero.controller.Controller;
import hero.core.Direction;
import hero.core.Motion;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.misc.Position;
import hero.misc.Size;

import java.awt.image.BufferedImage;

public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    public MovingEntity(Position position, Size size, Controller controller, AnimationManager animationManager, SpriteLibrary spriteLibrary) {
        super(position, size, spriteLibrary);
        this.controller = controller;
        motion = new Motion(3);
        this.animationManager = animationManager;
        animationManager.setAnimation("stand");
        direction = Direction.S;
    }

    @Override
    public void update() {
        motion.update(controller);
        position.applyVec(motion.getVector());
        direction = Direction.fromMotion(motion);
        if (motion.isMoving()) animationManager.setAnimation("walk");
        else animationManager.setAnimation("stand");
        animationManager.update(direction);
    }

    @Override
    public BufferedImage getSprite() {
        return animationManager.getSprite();
    }
}
