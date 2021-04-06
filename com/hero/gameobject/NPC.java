package hero.gameobject;

import hero.ai.AIManager;
import hero.controller.EntityController;
import hero.game.state.State;
import hero.gameobject.humanoid.Humanoid;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.core.Constants;
import hero.core.Position;
import hero.core.Size;

public class NPC extends Humanoid {
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
