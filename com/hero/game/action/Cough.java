package hero.game.action;

import hero.core.CollisionBox;
import hero.game.effects.Sick;
import hero.game.state.State;
import hero.gameobject.MovingEntity;
import hero.misc.Constants;
import hero.misc.Position;
import hero.misc.Size;

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
    public void update(State state, MovingEntity entity) {
        if (--lifeSpan <= 0) {
            Position spreadPosition = new Position(
                    entity.getPosition().getX() - spreadArea.getWidth() / 2,
                    entity.getPosition().getY() - spreadArea.getHeight() / 2
            );
            CollisionBox spreadBox = CollisionBox.of(spreadPosition, spreadArea);

            state.getGameObjectOfClass(MovingEntity.class).stream()
                    .filter(movingEntity -> movingEntity.getCollisionBox().collidesWith(spreadBox))
                    .filter(movingEntity -> !movingEntity.isAffected(Sick.class))
                    .forEach(movingEntity -> {
                        if (Math.random() < risk) {
                            movingEntity.addEffect(new Sick());
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
