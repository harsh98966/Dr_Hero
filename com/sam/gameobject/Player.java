package sam.gameobject;

import sam.controller.Controller;
import sam.misc.Position;
import sam.misc.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovingEntity {
    private BufferedImage image;
    private Controller controller;
    public Player(Controller controller){
        super(new Position(0, 0), new Size(50, 50),controller);

        image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public BufferedImage getSprite() {
        return image;
    }
}
