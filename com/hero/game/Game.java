package hero.game;

import hero.display.Display;
import hero.game.state.GameState;
import hero.input.Input;
import hero.game.state.State;
import hero.misc.Size;

public class Game {
    private final Display display;
    private Input input;
    private State state;


    Game(int width, int height){
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(new Size(width, height), input);
    }

    public void update(){
        state.update();
    }

    public void render(){
        display.render(state);
    }

}
