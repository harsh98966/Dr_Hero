package hero.gameobject.humanoid.effects;

import hero.gameobject.humanoid.action.Cough;
import hero.game.state.State;
import hero.core.Constants;
import hero.gameobject.humanoid.Humanoid;

public class Sick extends Effect {

    private final double COUGH_RATE = 1d / Constants.UPDATES_PER_SECOND / 10;

    public Sick() {
        super(TYPE.FOREVER, 0);
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        super.update(state, humanoid);
        if(Math.random() < COUGH_RATE) humanoid.performAction(new Cough());
    }
}
