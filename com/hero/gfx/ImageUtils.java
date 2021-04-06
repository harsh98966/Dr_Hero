package hero.gfx;

import hero.core.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static final int ALPHA_OPAQUE = 1;
    public static final int ALPHA_BIT_MASK = 2;
    public static final int ALPHA_BLEND = 3;


    public static BufferedImage loadImage(String src){
        try {
            BufferedImage image = ImageIO.read(ImageUtils.class.getResource(src));
            BufferedImage compatibleImage = (BufferedImage) createCompatibleImage(new Size(image.getWidth(null), image.getHeight(null)), ALPHA_BIT_MASK);
            Graphics2D graphics = compatibleImage.createGraphics();
            graphics.drawImage(image, 0, 0,null);
            graphics.dispose();
            return compatibleImage;
        } catch (Exception e){
            System.out.println("Could not load image from the source");
            e.printStackTrace();
        }
        return null;
    }

    public static Image createCompatibleImage(Size size, int transparency){
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        return graphicsConfiguration.createCompatibleImage(size.getWidth(), size.getHeight(), transparency);
    }
}
