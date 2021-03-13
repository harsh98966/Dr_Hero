package hero.game.effects;

import hero.game.action.Cough;
import hero.game.state.State;
import hero.gameobject.MovingEntity;
import hero.misc.Constants;

public class Sick extends Effect {

    private final double COUGH_RATE = 1d / Constants.UPDATES_PER_SECOND / 10;

    public Sick() {
        super(TYPE.FOREVER, 0);
    }

    @Override
    public void update(State state, MovingEntity entity) {
        super.update(state, entity);
        if(Math.random() < COUGH_RATE) entity.performAction(new Cough());
    }
}
