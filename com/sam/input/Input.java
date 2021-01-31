package sam.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean[] pressed;

    public Input(){
        pressed = new boolean[255];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if(keyCode < 255){
            pressed[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if(keyCode < 255){
            pressed[keyCode] = false;
        }
    }

    public boolean isPressed(int keyCode){
        if(keyCode < 255) return pressed[keyCode];
        return false;
    }

}
