package hero.game.state;

import hero.display.Camera;
import hero.game.Time;
import hero.gameobject.GameObject;
import hero.gameobject.SelectionCircle;
import hero.gfx.SpriteLibrary;
import hero.input.Input;
import hero.map.GameMap;
import hero.misc.Position;
import hero.misc.Size;
import hero.ui.UIContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {


    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Camera camera;
    protected Input input;
    protected Time time;
    protected List<UIContainer> uiContainers;

    protected boolean debug;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
        time = new Time();
        uiContainers = new ArrayList<>();
        debug = false;
    }

    public void update() {
        camera.update(this);
        uiContainers.forEach(uiContainer -> uiContainer.update(this));
        gameObjects.forEach(g -> g.update(this));
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    public List<GameObject> getCollidingGameObject(GameObject otherGameObject) {
        return gameObjects.stream()
                .filter(other -> other != otherGameObject)
                .filter(other -> other.CollidesWith(otherGameObject.getCollisionBox()))
                .collect(Collectors.toList());
    }

    public <T extends GameObject> List<T> getGameObjectOfClass(Class<T> clazz){
        return gameObjects.stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T)gameObject)
                .collect(Collectors.toList());
    }

}
