package hero.game.state;

import hero.controller.PlayerController;
import hero.display.Camera;
import hero.gameobject.GameObject;
import hero.gameobject.Player;
import hero.gfx.SpriteLibrary;
import hero.input.Input;
import hero.map.GameMap;
import hero.misc.Size;

import java.util.ArrayList;
import java.util.List;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Camera camera;
    protected Input input;

    public State(Size windowSize, Input input){
        gameObjects = new ArrayList<>();
        this.input = input;
        spriteLibrary = new SpriteLibrary();
        gameMap = new GameMap(new Size(40, 20), spriteLibrary);
        camera = new Camera(windowSize);
    }

    public void update(){
        camera.update(this);
        gameObjects.forEach(GameObject::update);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<GameObject> getGameObjects(){ return gameObjects; }

    public Camera getCamera() {
        return camera;
    }
}
