package hero.gameobject;

import hero.ai.AIManager;
import hero.controller.Controller;
import hero.game.action.Cough;
import hero.game.effects.Sick;
import hero.game.state.State;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

public class NPC extends MovingEntity {
    private AIManager aiManager;
    public NPC(Position position, Controller controller, SpriteLibrary spriteLibrary) {
        super(position, new Size(Constants.SPRITE_SIZE, Constants.SPRITE_SIZE), controller);
        aiManager = new AIManager();
        addEffect(new Sick());
        initAnimationManager(spriteLibrary);
    }

    @Override
    protected void initAnimationManager(SpriteLibrary spriteLibrary) {
        animationManager = new AnimationManager(spriteLibrary.getSpriteSet("NPC"));
        animationManager.setAnimation("stand");
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player){
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }

}
