package hero.game.state;

import hero.controller.NPC_Controller;
import hero.controller.PlayerController;
import hero.gameobject.NPC;
import hero.gameobject.Player;
import hero.input.Input;
import hero.map.GameMap;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;
import hero.ui.*;

import java.awt.*;
import java.util.Comparator;


public class GameState extends State {

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        gameMap = new GameMap(new Size(40, 20), spriteLibrary);
        camera.focusOn(player);
        generateNPC();
        initUI();
    }

    private void initUI() {
        UIContainer uiContainer = new HorizontalContainer();
        uiContainer.setBackgroundColor(Color.darkGray);
        uiContainer.add(new UIText("Harsh"));
        uiContainers.add(uiContainer);

    }

    private void generateNPC() {
        for (int i = 0; i < 80; i++) {
            Position pos = new Position(Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE, Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE);
            gameObjects.add(new NPC(pos, new NPC_Controller(), spriteLibrary));
        }
    }

    @Override
    public void update() {
        super.update();
        gameObjects.sort(Comparator.comparing(g -> g.getPosition().getY()));
    }
}
