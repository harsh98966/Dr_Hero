package hero.ai.state;

import hero.ai.AITransition;
import hero.controller.NPC_Controller;
import hero.game.state.State;
import hero.gameobject.NPC;
import hero.misc.Position;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState {
    private List<Position> targets;

    public Wander(){
        targets = new ArrayList<>();
    }

    @Override
    protected AITransition initTransition() {
        return new AITransition(((state, currentCharacter) -> arrived(currentCharacter)), "stand");
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(targets.isEmpty()){
            targets.add(state.getRandomPosition());
        }

        NPC_Controller controller = (NPC_Controller) currentCharacter.getController();
        controller.moveToTarget(currentCharacter.getPosition(), targets.get(0));

        if(arrived(currentCharacter)){
            controller.stop();
        }

    }

    private boolean arrived(NPC currentCharacter) {
        return currentCharacter.getPosition().isInRangeOf(targets.get(0));
    }

}
