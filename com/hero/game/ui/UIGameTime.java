package hero.game.ui;

import hero.game.state.State;
import hero.core.Size;
import hero.ui.Alignment;
import hero.ui.UIText;
import hero.ui.VerticalContainer;

public class UIGameTime extends VerticalContainer {

    private UIText gameTime;

    public UIGameTime(Size windowSize) {
        super(windowSize);
        gameTime = new UIText("00:00");
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.START);
        add(gameTime);
    }

    @Override
    public void update(State state) {
        super.update(state);
        gameTime.setText(state.getTime().getFormattedTime());
    }
}
