package hero.controller;

import hero.input.Input;

import java.awt.event.KeyEvent;

public class PlayerController implements  Controller {

    private final Input input;

    public PlayerController(Input input){
        this.input = input;
    }

    @Override
    public boolean isReqUP() {
        return input.isPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean isReqLeft() {
        return input.isPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean isReqRight() {
        return input.isPressed(KeyEvent.VK_RIGHT);

    }

    @Override
    public boolean isReqDown() {
        return input.isPressed(KeyEvent.VK_DOWN);
    }
}
