package hero.ai;

import hero.game.state.State;
import hero.gameobject.NPC;

public interface AICondition {
    boolean isMet(State state, NPC currentCharacter);
}
