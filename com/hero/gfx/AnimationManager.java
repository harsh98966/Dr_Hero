package hero.gfx;

import hero.core.Direction;
import hero.misc.Constants;

import java.awt.image.BufferedImage;

public class AnimationManager {
    private SpriteSet spriteSet;
    private BufferedImage currentImage;
    private int updatePerFrame;
    private int currentFrameTime;
    private int frameIndex;
    private int direction;

    public AnimationManager(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
        updatePerFrame = 4;
        frameIndex = 0;
        currentFrameTime = 0;

    }

    public BufferedImage getSprite() {
        return currentImage.getSubimage(
                frameIndex * Constants.SPRITE_SIZE, direction * Constants.SPRITE_SIZE, Constants.SPRITE_SIZE, Constants.SPRITE_SIZE
        );
    }

    public void update(Direction direction) {
        currentFrameTime++;
        if (currentFrameTime >= 60 / updatePerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            if (frameIndex >= currentImage.getWidth() / Constants.SPRITE_SIZE) frameIndex = 0;

        }

        if(direction != null){
            this.direction = direction.ordinal();
        }
    }

    public void setAnimation(String name){
        currentImage = spriteSet.getSheet(name);
    }

}
