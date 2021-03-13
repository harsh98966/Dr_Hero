package hero.ai;

import hero.ai.state.AIState;
import hero.ai.state.Stand;
import hero.ai.state.Wander;
import hero.game.state.State;
import hero.gameobject.NPC;

public class AIManager {
    private AIState currentAIState;

    public AIManager() {
        transitionTo("stand");
    }

    public void update(State state, NPC currentCharacter) {
        currentAIState.update(state, currentCharacter);
        if (currentAIState.shouldTransition(state, currentCharacter)) {
            transitionTo(currentAIState.getNextState());
        }
    }

    private void transitionTo(String nextState) {
        switch (nextState) {
            case "wander" -> currentAIState = new Wander();
            default -> currentAIState = new Stand();
        }
    }

}
