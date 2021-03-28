package hero.controller;

import hero.input.Input;

import java.awt.event.KeyEvent;

public class PlayerEntityController implements EntityController {

    private final Input input;

    public PlayerEntityController(Input input){
        this.input = input;
    }

    @Override
    public boolean isReqUP() {
        return input.isCurrentlyPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean isReqLeft() {
        return input.isCurrentlyPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean isReqRight() {
        return input.isCurrentlyPressed(KeyEvent.VK_RIGHT);

    }

    @Override
    public boolean isReqDown() {
        return input.isCurrentlyPressed(KeyEvent.VK_DOWN);
    }
}
