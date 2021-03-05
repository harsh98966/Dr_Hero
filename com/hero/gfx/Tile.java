package hero.gfx;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage sprite;

    public Tile(SpriteLibrary spriteLibrary){
        sprite = spriteLibrary.getTile("default");
    }

    public BufferedImage getSprite(){
        return sprite;
    }
}
