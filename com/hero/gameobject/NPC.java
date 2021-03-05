package hero.gameobject;

import hero.controller.Controller;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.misc.Position;
import hero.misc.Size;

public class NPC extends MovingEntity {
    public NPC(Position position, Size size, Controller controller, AnimationManager animationManager, SpriteLibrary spriteLibrary) {
        super(position, size, controller, animationManager, spriteLibrary);
    }
}
