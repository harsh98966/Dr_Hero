package hero.game;

import hero.misc.Constants;

public class GameLoop implements Runnable{

    public final double UPDATE_PER_SECOND = Constants.UPDATES_PER_SECOND;
    private boolean running;
    private int ups, fps;
    private Game game;

    public GameLoop(){
        running = true;
        game = new Game(Constants.WIDTH, Constants.HEIGHT);
    }

    @Override
    public void run() {
        double acc = 0;
        double prevTime = System.nanoTime();
        double lastTime = System.currentTimeMillis();
        double reqFps = 1000000000 / UPDATE_PER_SECOND;


        while (running){
            double currTime = System.nanoTime();
            acc += (currTime - prevTime) * game.getSettings().getGameSpeed();
            prevTime = currTime;
            while (acc > reqFps){
                update();
                acc -= reqFps;
            }
            render();

            if(System.currentTimeMillis() - lastTime >= 1000) {
                lastTime = System.currentTimeMillis();
                printStats();
            }

        }
    }

    private void printStats() {
        System.out.println("fps: " + fps + " ,ups: " + ups);
        fps = 0;
        ups = 0;
    }

    private void update() {
        game.update();
        ups++;
    }

    private void render() {
        game.render();
        fps++;
    }
}
