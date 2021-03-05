package hero.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {
    private final Map<String, BufferedImage> animationSheet;
    public SpriteSet(){
        animationSheet = new HashMap<>();
    }

    public void addSheet(String name, BufferedImage image){
        animationSheet.put(name, image);
    }

    public BufferedImage getSheet(String name){
        if(animationSheet.containsKey(name)){
            return animationSheet.get(name);
        }
        return null;
    }
}
