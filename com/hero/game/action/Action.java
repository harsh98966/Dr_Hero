package hero.game.action;

import hero.game.state.State;
import hero.gameobject.MovingEntity;

public abstract class Action {
    public abstract void update(State state, MovingEntity entity);
    public abstract boolean isDone();
    public abstract String getAnimationName();
}
