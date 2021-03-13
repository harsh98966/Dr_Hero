package hero.gameobject;

import hero.controller.Controller;
import hero.game.action.Cough;
import hero.game.state.State;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

public class Player extends MovingEntity {

    public Player(Controller controller, SpriteLibrary spriteLibrary) {
        super(new Position(70, 700),
                new Size(Constants.SPRITE_SIZE, Constants.SPRITE_SIZE),
                controller
        );
        initAnimationManager(spriteLibrary);
    }

    @Override
    protected void initAnimationManager(SpriteLibrary spriteLibrary) {
        animationManager = new AnimationManager(spriteLibrary.getSpriteSet("Player"));
        animationManager.setAnimation("stand");
    }

    @Override
    public void update(State state) {
        super.update(state);
    }

    @Override
    protected void handleCollision(GameObject gameObject) {
        motion.stop(willCollideX(gameObject), willCollideY(gameObject));
    }
}
