package hero.gameobject.humanoid.action;

import hero.game.state.State;
import hero.gameobject.humanoid.Humanoid;

public abstract class Action {
    public abstract void update(State state, Humanoid humanoid);
    public abstract boolean isDone();
    public abstract String getAnimationName();
}
