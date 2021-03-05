package hero.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

    private final Map<String, SpriteSet> entities;
    private final Map<String, BufferedImage> tiles;

    public SpriteLibrary() {
        entities = new HashMap<>();
        tiles = new HashMap<>();
        loadEntities();
        loadTiles();
    }

    private void loadTiles() {
        String[] images = getFiles("/Tiles");
        for (String image : images) {
            BufferedImage img = ImageUtils.loadImage("/Tiles" + "/" + image);
            if (tiles.size() == 0) tiles.put("default", img);
            tiles.put(image.substring(0, image.length() - 4), img);
        }
    }

    private void loadEntities() {
        String s = "/Entity";
        String[] folderNames = getFolderNames(s);
        for (String folder : folderNames) {
            SpriteSet spriteSet = new SpriteSet();
            String[] contents = getFiles(s + "/" + folder);
            for (String file : contents) {
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

    public SpriteSet getSpriteSet(String name) {
        return entities.getOrDefault(name, null);
    }

    public BufferedImage getTile(String key) {
        return tiles.getOrDefault(key, null);
    }

}
