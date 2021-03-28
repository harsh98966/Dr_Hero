package hero.gameobject;

import hero.core.CollisionBox;
import hero.game.state.State;
import hero.misc.Position;
import hero.misc.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject {

    private Color color;
    private BufferedImage sprite;


    public SelectionCircle() {
        super(new Position(90, 90), new Size(0, 0));
        size = new Size(32, 28);
        color = Color.ORANGE;
        initSprite();
    }

    private void initSprite() {
        sprite = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.BITMASK);
        Graphics2D g = sprite.createGraphics();
        g.setColor(color);
        g.fillOval(0, 0, size.getWidth(), size.getHeight());
        g.dispose();
    }

    @Override
    public void update(State state) {}

    @Override
    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return CollisionBox.of(position, size);
    }

}
