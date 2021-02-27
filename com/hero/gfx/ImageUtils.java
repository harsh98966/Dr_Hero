package hero.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static BufferedImage loadImage(String src){
        try {
            return ImageIO.read(ImageUtils.class.getResource(src));
        } catch (Exception e){
            System.out.println("Could not load image from the source");
            e.printStackTrace();
        }
        return null;
    }
}
