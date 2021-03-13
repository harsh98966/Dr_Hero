package hero.game;

import hero.misc.Constants;

public class Time {
    private int updateSinceStart;
    public Time(){
        updateSinceStart = 0;
    }

    public int updateFromSeconds(int seconds){
        return seconds * (int) Constants.UPDATES_PER_SECOND;
    }

}
