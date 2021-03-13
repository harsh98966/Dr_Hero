package hero.map;

import hero.display.Camera;
import hero.gfx.SpriteLibrary;
import hero.gfx.Tile;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

import java.util.Arrays;

public class GameMap {

    private Tile[][] tiles;

    public GameMap(Size size, SpriteLibrary spriteLibrary){
        tiles = new Tile[size.getWidth()][size.getHeight()];
        initTiles(spriteLibrary);
    }

    private void initTiles(SpriteLibrary spriteLibrary) {
        for(Tile[] row : tiles){
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles(){ return tiles; }

    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * Constants.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * Constants.SPRITE_SIZE;
        return new Position(x, y);
    }

    public Position getStartingPos(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / Constants.SPRITE_SIZE - 5),
                Math.max(0, camera.getPosition().getY() / Constants.SPRITE_SIZE - 5)
        );
    }

    public Position getEndingPos(Camera camera) {
        return new Position(
                Math.min(tiles.length, camera.getPosition().getX() / Constants.SPRITE_SIZE + camera.getSize().getWidth() / Constants.SPRITE_SIZE + 5),
                Math.min(tiles[0].length, camera.getPosition().getY() / Constants.SPRITE_SIZE + camera.getSize().getHeight() / Constants.SPRITE_SIZE + 5)
        );
    }

}
