package hero.display;

import hero.game.state.State;
import hero.gameobject.GameObject;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

import java.util.Optional;

public class Camera {
    private Size windowSize;
    private Position position;
    private Optional<GameObject> objectWithFocus;

    public Camera(Size windowSize) {
        position = new Position(0, 0);
        this.windowSize = windowSize;
        objectWithFocus = Optional.empty();
    }

    public void focusOn(GameObject object) {
        objectWithFocus = Optional.of(object);
    }

    public void update(State state) {
        objectWithFocus.ifPresent(object -> {
            position.update(object.getPosition().getX() - windowSize.getWidth() / 2,
                    object.getPosition().getY() - windowSize.getHeight() / 2);
            clampWithinBounds(state);
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
}