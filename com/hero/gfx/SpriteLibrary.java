package hero.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {
    public static final SpriteLibrary spriteLibrary = new SpriteLibrary();

    private Map<String, SpriteSet> entities;

    public SpriteLibrary(){
        entities = new HashMap<>();
        loadSprites("/Entity");
    }

    private void loadSprites(String s) {
        String[] folderNames = getFolderNames(s);
        for(String folder : folderNames){
            SpriteSet spriteSet = new SpriteSet();
            String[] contents = getFiles(s + "/" + folder);
            for(String file : contents){
                BufferedImage image = ImageUtils.loadImage(s + "/" + folder + "/" + file);
                spriteSet.addSheet(file.split("\\.")[0], image);
            }
            entities.put(folder, spriteSet);
        }

    }

    private String[] getFiles(String s) {
        URL url = SpriteLibrary.class.getResource(s);
        File file = new File(url.getFile());
        return file.list();
    }

    private String[] getFolderNames(String s) {
        URL url = SpriteLibrary.class.getResource(s);
        File file = new File(url.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    public SpriteSet getSpriteSet(String name){
        if(entities.containsKey(name)) return entities.get(name);
        return null;
    }

}
