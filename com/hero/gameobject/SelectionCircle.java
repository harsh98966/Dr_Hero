package hero.gameobject;

import hero.core.CollisionBox;
import hero.game.state.State;
import hero.core.Position;
import hero.core.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject {

    private Color color;
    private BufferedImage sprite;


    public SelectionCircle() {
        super(new Position(0, 12), new Size(20, 15));
        size = new Size(32, 28);
        color = Color.ORANGE;
        renderOffset = new Position(size.getWidth() / 2, size.getHeight() / 2);
        initSprite();
        renderOrder = 4;
    }

    private void initSprite() {
        sprite = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.BITMASK);
        Graphics2D g = sprite.createGraphics();
        g.setColor(color);
        g.setStroke(new BasicStroke(2));
        g.drawOval(0, 0, size.getWidth(), size.getHeight());
        g.dispose();
    }

    @Override
    public void update(State state) {
    }

    @Override
    public BufferedImage getSprite() {
        return parent != null ? sprite : null;
//        return sprite;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return CollisionBox.of(getPosition(), getSize());
    }

}
