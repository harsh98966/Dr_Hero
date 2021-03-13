package hero.ai;

import hero.game.state.State;
import hero.gameobject.NPC;

public class AITransition {
    private AICondition condition;
    private String nextState;

    public AITransition(AICondition condition, String nextState) {
        this.condition = condition;
        this.nextState = nextState;
    }

    public boolean shouldTransition(State state, NPC currentCharacter){
        return condition.isMet(state, currentCharacter);
    }

    public String getNextState() {
        return nextState;
    }
}
