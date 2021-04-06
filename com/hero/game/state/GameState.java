package hero.game.state;

import hero.controller.NPC_Entity_Controller;
import hero.controller.PlayerEntityController;
import hero.core.CollisionBox;
import hero.gameobject.humanoid.effects.Sick;
import hero.game.ui.UIGameTime;
import hero.game.ui.UISickStats;
import hero.gameobject.GameObject;
import hero.gameobject.NPC;
import hero.gameobject.Player;
import hero.gameobject.SelectionCircle;
import hero.input.Input;
import hero.map.GameMap;
import hero.core.Constants;
import hero.core.Position;
import hero.core.Size;

import java.util.Comparator;


public class GameState extends State {

    private Player player;

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        SelectionCircle selectionCircle = new SelectionCircle();
        player = new Player(new PlayerEntityController(input), spriteLibrary, selectionCircle);
        gameObjects.add(player);
        gameObjects.add(selectionCircle);
        gameMap = new GameMap(new Size(40, 20), spriteLibrary);
        camera.focusOn(player);
        generateNPC();
        makeSick(21);
        initUI(windowSize);
    }

    private void generateNPC() {
        for (int i = 0; i < 80; i++) {
            Position pos = new Position(Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE, Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE);
            while(true){
                if(!alreadyOccupied(pos, new Size(Constants.SPRITE_SIZE, Constants.SPRITE_SIZE))) break;
                else{
                    pos = new Position(Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE, Math.random() * gameMap.getTiles().length * Constants.SPRITE_SIZE);
                }
            }
            gameObjects.add(new NPC(pos, new NPC_Entity_Controller(), spriteLibrary));
        }
    }

    private boolean alreadyOccupied(Position pos, Size size) {
        long count = gameObjects.stream()
                .filter(g -> g.getCollisionBox().collidesWith(CollisionBox.of(pos, size)))
                .count();
        return count != 0;
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

    @Override
    public void update() {
        super.update();
        gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(g -> g.getPosition().getY()));
        time.update();
    }


}
