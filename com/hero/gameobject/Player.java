package hero.gameobject;

import hero.controller.EntityController;
import hero.core.*;
import hero.game.state.State;
import hero.gameobject.humanoid.effects.Sick;
import hero.gameobject.humanoid.Humanoid;
import hero.gfx.AnimationManager;
import hero.gfx.SpriteLibrary;

import java.util.Comparator;
import java.util.Optional;

public class Player extends Humanoid {

    private NPC target;
    private double range;
    private SelectionCircle selectionCircle;

    public Player(EntityController entityController, SpriteLibrary spriteLibrary, SelectionCircle selectionCircle) {
        super(new Position(70, 700),
                new Size(Constants.SPRITE_SIZE, Constants.SPRITE_SIZE),
                entityController
        );
        initAnimationManager(spriteLibrary);
        this.selectionCircle = selectionCircle;
        range = Constants.SPRITE_SIZE ;
    }

    @Override
    protected void initAnimationManager(SpriteLibrary spriteLibrary) {
        animationManager = new AnimationManager(spriteLibrary.getSpriteSet("Player"));
        animationManager.setAnimation("stand");
    }

    @Override
    public void update(State state) {
        super.update(state);
        handleTarget(state);
//        System.out.println(Direction.vectorFromDirection(direction, directionVector).getX());
    }

    private void handleTarget(State state) {
//        System.out.println(getPosition().distanceTo(new Position(getPosition().getX() + 20, getPosition().getY() + 30 )));
        Optional<NPC> closetNPC = getClosestNPC(state);
        if(closetNPC.isPresent()){
            NPC npc = closetNPC.get();
            if(!npc.equals(target)){
                selectionCircle.addParent(npc);
                target = npc;
            }
        }
        else{
            selectionCircle.clearParent();
            target = null;
        }
    }

    private Optional<NPC> getClosestNPC(State state) {
        return state.getGameObjectOfClass(NPC.class).stream()
                .filter(npc -> getPosition().distanceTo(npc.getPosition()) <= range)
                .filter(npc -> isFacing(npc.getPosition()))
                .min(Comparator.comparingDouble(npc -> getPosition().distanceTo(npc.getPosition())));
    }


    @Override
    protected void handleCollision(GameObject gameObject) {
        if(!(gameObject instanceof SelectionCircle)) motion.stop(willCollideX(gameObject), willCollideY(gameObject));
    }
}
