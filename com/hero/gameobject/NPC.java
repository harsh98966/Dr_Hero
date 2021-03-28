package hero.gameobject;

import hero.ai.AIManager;
import hero.controller.EntityController;
import hero.game.state.State;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

public class NPC extends MovingEntity {
    private AIManager aiManager;
    public NPC(Position position, EntityController entityController, SpriteLibrary spriteLibrary) {
        super(position, new Size(Constants.SPRITE_SIZE, Constants.SPRITE_SIZE), entityController);
        aiManager = new AIManager();
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
            effects.clear();
        }
    }

}
