package hero.game.ui;

import hero.game.effects.Sick;
import hero.game.state.State;
import hero.gameobject.GameObject;
import hero.gameobject.MovingEntity;
import hero.misc.Size;
import hero.ui.*;

public class UISickStats extends HorizontalContainer {

    private UIText sickText;
    private UIText healthyText;

    public UISickStats(Size windowSize) {
        super(windowSize);
        sickText = new UIText("");
        healthyText = new UIText("");
        padding = new Spacing(0);

        UIContainer sickContainer = new VerticalContainer(windowSize);
        sickContainer.add(new UIText("Sick"));
        sickContainer.add(sickText);

        UIContainer healthyContainer = new VerticalContainer(windowSize);
        healthyContainer.add(new UIText("Healthy"));
        healthyContainer.add(healthyText);

        add(healthyContainer);
        add(sickContainer);

    }

    @Override
    public void update(State state) {
        super.update(state);
        long sick = state.getGameObjects().stream()
                .filter(gameObject -> gameObject instanceof MovingEntity)
                .map(gameObject -> (MovingEntity) gameObject)
                .filter(movingEntity -> movingEntity.isAffected(Sick.class))
                .count();

        long healthy = state.getGameObjects().stream()
                .filter(gameObject -> gameObject instanceof MovingEntity)
                .map(gameObject -> (MovingEntity) gameObject)
                .filter(movingEntity -> !movingEntity.isAffected(Sick.class))
                .count();

        sickText.setText(String.valueOf(sick));
        healthyText.setText(String.valueOf(healthy));
    }
}
