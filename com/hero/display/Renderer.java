package hero.display;

import hero.game.state.State;
import hero.gfx.Tile;
import hero.map.GameMap;
import hero.core.Constants;
import hero.core.Position;

import java.awt.*;

public class Renderer {
    public void render(State state, Graphics graphics) {
        renderMap(state, graphics);
        renderGameObjects(state, graphics);
        renderUI(state, graphics);
    }

    private void renderUI(State state, Graphics graphics){
        state.getUiContainers().forEach(
                uiContainer -> graphics.drawImage(
                        uiContainer.getSprite(),
                        uiContainer.getPosition().intX(),
                        uiContainer.getPosition().intY(),
                        null
                )
        );
    }

    private void renderGameObjects(State state, Graphics graphics){
        Camera camera = state.getCamera();
        state.getGameObjects().stream().filter(camera::isInView).forEach(g -> {
                    graphics.drawImage(
                            g.getSprite(),
                            g.getRenderPosition(state).intX(),
                            g.getRenderPosition(state).intY(),
                            null);
                }
        );
    }

    private void renderMap(State state, Graphics g) {
        GameMap gameMap = state.getGameMap();
        Camera camera = state.getCamera();
        Position startingPos = gameMap.getStartingPos(camera);
        Position endingPos = gameMap.getEndingPos(camera);
        for (int x = startingPos.intX(); x < endingPos.intX(); x++) {
            for (int y = startingPos.intY(); y < endingPos.intY(); y++) {
                Tile tile = gameMap.getTiles()[x][y];
                g.drawImage(
                        tile.getSprite(),
                        x * Constants.SPRITE_SIZE - state.getCamera().getPosition().intX(),
                        y * Constants.SPRITE_SIZE - state.getCamera().getPosition().intY(),
                        null
                );
            }
        }
    }
}