package hero.display;

import hero.game.state.State;
import hero.gfx.Tile;
import hero.misc.Constants;

import java.awt.*;

public class Renderer {
    public void render(State state, Graphics g) {
        state.getGameObjects().forEach(gameObject ->
                {
                    g.drawImage(gameObject.getSprite(), gameObject.getPosition().intX() - state.getCamera().getPosition().intX() - Constants.SPRITE_SIZE / 2, gameObject.getPosition().intY() - state.getCamera().getPosition().intY() - Constants.SPRITE_SIZE  / 2, null);
                }
        );
    }

    public void renderMap(State state, Graphics g){
        Tile[][] tiles = state.getGameMap().getTiles();
        Camera camera = state.getCamera();
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[x].length; y++){
                Tile tile = tiles[x][y];
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