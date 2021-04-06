package hero.gameobject.humanoid;

import hero.controller.EntityController;
import hero.core.Position;
import hero.core.Size;
import hero.game.state.State;
import hero.gameobject.GameObject;
import hero.gameobject.MovingEntity;
import hero.gameobject.humanoid.action.Action;
import hero.gameobject.humanoid.effects.Effect;
import hero.gfx.SpriteLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class Humanoid extends MovingEntity {

    protected List<Effect> effects;
    protected Optional<Action> action;

    public Humanoid(Position position, Size size, EntityController entityController) {
        super(position, size, entityController);
        effects = new ArrayList<>();
        action = Optional.empty();
    }

    @Override
    public void update(State state) {
        super.update(state);
        action.ifPresent(value -> value.update(state, this));
        playEffect(state);
        cleanUp();
    }


    @Override
    protected void handleMotion() {
        if (action.isPresent()) {
            motion.stop(true, true);
        }
    }

    private void cleanUp() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if (action.isPresent() && action.get().isDone()) action = Optional.empty();
    }

    protected String decideAnimation() {
        if (action.isPresent()) return action.get().getAnimationName();
        else if(motion.isMoving()) return "walk";
        return "stand";
    }

    private void playEffect(State state) {
        ListIterator<Effect> effectListIterator = effects.listIterator();
        while (effectListIterator.hasNext()) {
            Effect currEffect = effectListIterator.next();
            if (currEffect.shouldDelete()) effectListIterator.remove();
            else currEffect.update(state, this);
        }
    }


    public void performAction(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public boolean isAffected(Class<?> clazz) {
        return effects.stream().anyMatch(clazz::isInstance);
    }

    @Override
    protected void initAnimationManager(SpriteLibrary spriteLibrary) {}

    @Override
    protected void handleCollision(GameObject gameObject) {}
}
