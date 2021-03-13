package hero.display;

import hero.game.state.State;
import hero.gameobject.GameObject;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

import java.awt.*;
import java.util.Optional;

public class Camera {

    private final static int SAFETY_SPACE = 2;

    private Size windowSize;
    private Position position;
    private Optional<GameObject> objectWithFocus;
    private Rectangle viewBounds;

    public Camera(Size windowSize) {
        position = new Position(0, 0);
        this.windowSize = windowSize;
        objectWithFocus = Optional.empty();
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rectangle(position.intX(), position.intY(), windowSize.getWidth() + SAFETY_SPACE * Constants.SPRITE_SIZE, windowSize.getHeight() + SAFETY_SPACE * Constants.SPRITE_SIZE);
    }

    public void focusOn(GameObject object) {
        objectWithFocus = Optional.of(object);
    }

    public void update(State state) {
        objectWithFocus.ifPresent(object -> {
            position.update(object.getPosition().getX() - windowSize.getWidth() / 2,
                    object.getPosition().getY() - windowSize.getHeight() / 2);
            clampWithinBounds(state);
            calculateViewBounds();
        });


    }

    private void clampWithinBounds(State state) {
        if (position.getX() < 0) position.setX(0);
        if (position.getY() < 0) position.setY(0);

        if(position.getX() + windowSize.getWidth() > state.getGameMap().getTiles().length * Constants.SPRITE_SIZE){
            position.setX(state.getGameMap().getTiles().length * Constants.SPRITE_SIZE - windowSize.getWidth());
        }
        if(position.getY() + windowSize.getHeight() > state.getGameMap().getTiles()[0].length * Constants.SPRITE_SIZE){
            position.setY(state.getGameMap().getTiles()[0].length * Constants.SPRITE_SIZE - windowSize.getHeight());
        }

    }

    public Position getPosition() {
        return position;
    }

    public GameObject focusedOn(){
        return objectWithFocus.orElse(null);
    }

    public boolean isInView(GameObject gameobject) {
        return viewBounds.intersects(new Rectangle(gameobject.getPosition().intX(),
                gameobject.getPosition().intY(),
                Constants.SPRITE_SIZE,
                Constants.SPRITE_SIZE));
    }

    public Size getSize() {
        return windowSize;
    }
}
