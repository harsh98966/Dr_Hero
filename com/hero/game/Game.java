package hero.game;

import hero.controller.GameController;
import hero.display.Display;
import hero.game.settings.GameSettings;
import hero.game.state.GameState;
import hero.input.Input;
import hero.game.state.State;
import hero.misc.Size;

public class Game {
    private final Display display;
    private Input input;
    private State state;
    private GameSettings settings;
    private GameController gameController;


    Game(int width, int height){
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(new Size(width, height), input);
        settings = new GameSettings(false);
        gameController = new GameController(input);
    }

    public void update(){
        state.update();
        gameController.update(this);
    }

    public void render(){
        display.render(state, settings.isDebugMode());
    }

    public GameSettings getSettings() {
        return settings;
    }
}
