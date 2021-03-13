package hero.ai.state;

import hero.ai.AITransition;
import hero.game.state.State;
import hero.gameobject.NPC;

public abstract class AIState {
    protected AITransition transition;
    public AIState(){
        transition = initTransition();
    }

    protected abstract AITransition initTransition();
    public abstract void update(State state, NPC currentCharacter);

    public boolean shouldTransition(State state, NPC currentCharacter){
        return transition.shouldTransition(state, currentCharacter);
    }

    public String getNextState(){
        return transition.getNextState();
    }

}
