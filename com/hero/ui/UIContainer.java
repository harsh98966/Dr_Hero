package hero.ui;

import hero.game.state.State;
import hero.misc.Size;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent {

    protected Color bgColor;
    List<UIComponent> childrens;

    public UIContainer() {
        super();
        bgColor = Color.RED;
        childrens = new ArrayList<>();
        calculateSize();
        calculatePosition();
    }

    public abstract void calculateContentPosition();
    public abstract Size calculateContentSize();

    public void calculateSize(){
        Size calculatedSize = calculateContentSize();
        size.update(padding.getHorizontal() + calculatedSize.getWidth(), padding.getVertical() + calculatedSize.getHeight());
    }

    public void calculatePosition(){
        position.update(margin.getLeft(), margin.getTop());
        calculateContentPosition();
    }


    @Override
    public BufferedImage getSprite() {
        BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(bgColor);
        g.fillRect(0, 0, size.getWidth(), size.getHeight());
        for(UIComponent uiComponent : childrens){
            g.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getPosition().intX(),
                    uiComponent.getPosition().intY(),
                    null
            );
        }
        g.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        childrens.forEach(child -> child.update(state));
        calculateSize();
        calculatePosition();
    }

    public void add(UIComponent child){
        childrens.add(child);
    }

    public void setBackgroundColor(Color color) {
        bgColor = color;
    }
}
