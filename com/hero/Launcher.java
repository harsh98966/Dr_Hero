package hero;

import hero.game.GameLoop;

public class Launcher {
    public static void main(String[] args) {
        new Thread(new GameLoop()).start();
    }
}
