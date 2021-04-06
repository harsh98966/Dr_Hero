package hero.ui;

import hero.core.Position;
import hero.core.Size;

public class VerticalContainer extends UIContainer {
    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    public Size calculateContentSize() {
        int widestWidth = 0;
        int combinedHeight = 0;
        for(UIComponent uiComponent : childrens){
            combinedHeight += uiComponent.getSize().getHeight() + margin.getVertical();
            if(uiComponent.getSize().getWidth() > widestWidth) widestWidth = uiComponent.getSize().getWidth();
        }
        return new Size(widestWidth, combinedHeight);
    }

    @Override
    public void calculateContentPosition() {
        int currentY = padding.getTop();
        for(UIComponent uiComponent : childrens){
            currentY += uiComponent.getMargin().getTop();
            uiComponent.setPosition(new Position(padding.getLeft(), currentY));
            currentY += uiComponent.getSize().getHeight();
            currentY += uiComponent.getMargin().getBottom();
        }

    }
}
