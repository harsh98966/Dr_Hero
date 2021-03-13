package hero.ai.state;

import hero.ai.AITransition;
import hero.game.state.State;
import hero.gameobject.NPC;

public class Stand extends AIState{

    private int updatesDone;

    @Override
    protected AITransition initTransition() {
        return new AITransition(((state, currentCharacter) -> updatesDone >= state.getTime().updateFromSeconds(3)), "wander");
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        updatesDone++;
    }
}
