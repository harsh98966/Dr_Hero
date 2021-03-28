package hero.input;

import hero.game.state.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean[] currentlyPressed;
    private boolean[] pressed;

    public Input() {
        currentlyPressed = new boolean[255];
        pressed = new boolean[255];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode < 255) {
            currentlyPressed[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode < 255) {
            currentlyPressed[keyCode] = false;
            pressed[keyCode] = false;
        }
    }

    public boolean isPressed(int keyCode) {
        if (!pressed[keyCode] && currentlyPressed[keyCode]) {
            if (keyCode < 255) pressed[keyCode] = true;
            return true;
        }
        return false;
    }

    public boolean isCurrentlyPressed(int keyCode) {
        if (keyCode < 255) return currentlyPressed[keyCode];
        return false;
    }

}
