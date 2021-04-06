package hero.gameobject.humanoid.action;

import hero.core.CollisionBox;
import hero.gameobject.humanoid.effects.Sick;
import hero.game.state.State;
import hero.core.Constants;
import hero.core.Position;
import hero.core.Size;
import hero.gameobject.humanoid.Humanoid;

public class Cough extends Action {

    private int lifeSpan;
    private Size spreadArea;
    private double risk;

    public Cough() {
        lifeSpan = (int) Constants.UPDATES_PER_SECOND;
        spreadArea = new Size(2 * Constants.SPRITE_SIZE, 2 * Constants.SPRITE_SIZE);
        risk = 0.1;
    }

    @Override
    public void update(State state, Humanoid performer) {
        if (--lifeSpan <= 0) {
            Position spreadPosition = new Position(
                    performer.getPosition().getX() - spreadArea.getWidth() / 2,
                    performer.getPosition().getY() - spreadArea.getHeight() / 2
            );
            CollisionBox spreadBox = CollisionBox.of(spreadPosition, spreadArea);

            state.getGameObjectOfClass(Humanoid.class).stream()
                    .filter(humanoid -> humanoid.getCollisionBox().collidesWith(spreadBox))
                    .filter(humanoid -> !humanoid.isAffected(Sick.class))
                    .forEach(humanoid -> {
                        if (Math.random() < risk) {
                            humanoid.addEffect(new Sick());
                        }
                    });

        }
    }

    @Override
    public boolean isDone() {
        return lifeSpan <= 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }
}
