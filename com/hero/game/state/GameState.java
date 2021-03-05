package hero.game.state;

import hero.controller.NPC_Controller;
import hero.controller.PlayerController;
import hero.gameobject.NPC;
import hero.gameobject.Player;
import hero.gfx.AnimationManager;
import hero.input.Input;
import hero.misc.Position;
import hero.misc.Size;

import java.awt.event.KeyEvent;
import java.util.Comparator;

public class GameState extends State {

    Player player;
    NPC ai;
    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
         player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);
         ai = new NPC(new Position(1000, 50),
                new Size(50, 50),
                new NPC_Controller(),
                new AnimationManager(spriteLibrary.getSpriteSet("NPC")),
                spriteLibrary
        );
        gameObjects.add(ai);
    }

    @Override
    public void update() {
        super.update();
        gameObjects.sort(Comparator.comparing(g -> g.getPosition().getY()));
    }
}
