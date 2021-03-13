package hero.game.action;

import hero.game.state.State;
import hero.gameobject.MovingEntity;
import hero.misc.Constants;

public class Cough extends Action {

    private int lifeSpan;
    public Cough() {
        lifeSpan = (int)Constants.UPDATES_PER_SECOND;
    }

    @Override
    public void update(State state, MovingEntity entity) {
        lifeSpan--;
    }

    @Override
    public boolean isDone() {
        return lifeSpan <= 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }
}
