package sam.game;

import sam.display.Display;
import sam.gameobject.GameObject;
import sam.gameobject.Player;
import sam.input.Input;
import sam.controller.PlayerController;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Display display;
    private List<GameObject> gameObjects;
    private Input input;


    Game(int width, int height){
        gameObjects = new ArrayList<>();
        input = new Input();
        display = new Display(width, height, input);
        gameObjects.add(new Player(new PlayerController(input)));
    }

    public void update(){
        gameObjects.forEach(GameObject::update);
    }

    public List<GameObject> getGameObjects(){ return gameObjects; }

    public void render(){
        display.render(this);
    }
}
