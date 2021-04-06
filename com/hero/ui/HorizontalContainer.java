package hero.ui;

import hero.core.Position;
import hero.core.Size;

public class HorizontalContainer extends UIContainer {
    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    public Size calculateContentSize() {
        int tallestHeight = 0;
        int combinedWidth = 0;
        for(UIComponent uiComponent : childrens){
            combinedWidth += uiComponent.size.getWidth() + uiComponent.margin.getHorizontal();
            if(uiComponent.size.getHeight() > tallestHeight) tallestHeight = uiComponent.size.getHeight();
        }
        return new Size(combinedWidth, tallestHeight);
    }

    @Override
    public void calculateContentPosition() {
        int currentX = padding.getLeft();
        for(UIComponent uiComponent : childrens){
            currentX += uiComponent.getMargin().getLeft();
            uiComponent.setPosition(new Position(currentX, padding.getTop()));
            currentX += uiComponent.getSize().getWidth();
            currentX += uiComponent.getMargin().getRight();
        }
    }
}
