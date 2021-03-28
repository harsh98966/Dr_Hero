package hero.controller;

import hero.game.Game;
import hero.input.Input;

import java.awt.event.KeyEvent;

public class GameController {
    private Input input;

    public GameController(Input input){
        this.input = input;
    }

    public void update(Game game){
        if(input.isPressed(KeyEvent.VK_F2)){
            game.getSettings().toggleDebugMode();
        }

        if(input.isPressed(KeyEvent.VK_PAGE_UP)){
            game.getSettings().increaseGameSpeed();
        }

        if(input.isPressed(KeyEvent.VK_PAGE_DOWN)){
            game.getSettings().decreaseGameSpeed();
            if(game.getSettings().getGameSpeed() <= 0) while(game.getSettings().getGameSpeed() < 0) game.getSettings().increaseGameSpeed();
        }
    }
}
