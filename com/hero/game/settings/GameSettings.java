package hero.game.settings;

public class GameSettings {

    private boolean debugMode;
    private double gameSpeed;

    public GameSettings(boolean debugMode){
        this.debugMode = debugMode;
        gameSpeed = 1;
    }

    public boolean isDebugMode(){
        return debugMode;
    }

    public void toggleDebugMode(){
        debugMode = !debugMode;
    }

    public void increaseGameSpeed(){
        gameSpeed += 0.15;
    }

    public void decreaseGameSpeed(){
        gameSpeed -= 0.15;
    }

    public double getGameSpeed() {
        return gameSpeed;
    }
}
