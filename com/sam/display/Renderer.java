package sam.display;

import sam.game.Game;

import java.awt.*;

public class Renderer {
    public void render(Game game, Graphics g) {
        game.getGameObjects().forEach(gameObject ->
                g.drawImage(gameObject.getSprite(), gameObject.getPosition().intX(), gameObject.getPosition().intY(), null)
        );
    }
}