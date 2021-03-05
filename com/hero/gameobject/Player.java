package hero.gameobject;

import hero.controller.Controller;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;
import hero.misc.Position;
import hero.misc.Size;

import java.awt.image.BufferedImage;

public class Player extends MovingEntity {
    private BufferedImage image;
    private Controller controller;

    public Player(Controller controller, SpriteLibrary spriteLibrary) {
        super(new Position(0, 0),
                new Size(50, 50),
                controller,
                new AnimationManager(spriteLibrary.getSpriteSet("Player")),
                spriteLibrary
        );
    }

    @Override
    public void update() {
        super.update();
    }

}
