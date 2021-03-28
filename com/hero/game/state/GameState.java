package hero.game.state;

import hero.controller.NPC_Entity_Controller;
import hero.controller.PlayerEntityController;
import hero.game.effects.Sick;
import hero.game.ui.UIGameTime;
import hero.game.ui.UISickStats;
import hero.gameobject.NPC;
import hero.gameobject.Player;
import hero.gameobject.SelectionCircle;
import hero.input.Input;
import hero.map.GameMap;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

import java.util.Comparator;


public class GameState extends State {

    private Player player;

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        player = new Player(new PlayerEntityController(input), spriteLibrary);
        gameObjects.add(player);
        gameMap = new GameMap(new Size(40, 20), spriteLibrary);
        camera.focusOn(player);
        generateNPC();
        makeSick(21);
        initUI(windowSize);
    }

    private void makeSick(int number) {
        getGameObjectOfClass(NPC.class).stream()
                .limit(number)
                .forEach(npc -> npc.addEffect(new Sick()));
    }

    private void initUI(Size windowSize) {
        uiContainers.add(new UIGameTime(windowSize));
        uiContainers.add(new UISickStats(windowSize));
    }

    private void generateNPC() {
        for (int i = 0; i < 80; i++) {
            Position pos = new Position(Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE, Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE);
            gameObjects.add(new NPC(pos, new NPC_Entity_Controller(), spriteLibrary));
        }
    }

    @Override
    public void update() {
        SelectionCircle selectionCircle = new SelectionCircle();
        selectionCircle.addParent(player);
        gameObjects.add(selectionCircle);
        super.update();
        gameObjects.sort(Comparator.comparing(g -> g.getPosition().getY()));
        time.update();
    }


}
