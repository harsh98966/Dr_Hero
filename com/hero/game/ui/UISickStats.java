package hero.game.ui;

import hero.gameobject.humanoid.effects.Sick;
import hero.game.state.State;
import hero.core.Size;
import hero.gameobject.humanoid.Humanoid;
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
        long sick = state.getGameObjectOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffected(Sick.class))
                .count();

        long healthy = state.getGameObjectOfClass(Humanoid.class).stream()
                .filter(humanoid -> !humanoid.isAffected(Sick.class))
                .count();

        sickText.setText(String.valueOf(sick));
        healthyText.setText(String.valueOf(healthy));
    }
}
