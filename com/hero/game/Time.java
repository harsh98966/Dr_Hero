package hero.game;

import hero.misc.Constants;

public class Time {
    private long updateSinceStart;

    public Time() {
        updateSinceStart = 0;
    }

    public int updateFromSeconds(int seconds) {
        return seconds * (int) Constants.UPDATES_PER_SECOND;
    }

    public void update() {
        updateSinceStart++;
    }

    public String getFormattedTime() {
        StringBuilder stringBuilder = new StringBuilder();
        long min = updateSinceStart / (int) Constants.UPDATES_PER_SECOND / 60;
        long sec = updateSinceStart / (int) Constants.UPDATES_PER_SECOND % 60;

        if (min < 10) stringBuilder.append("0");
        stringBuilder.append(min);

        stringBuilder.append(":");

        if (sec < 10) stringBuilder.append("0");
        stringBuilder.append(sec);

        return stringBuilder.toString();
    }
}
