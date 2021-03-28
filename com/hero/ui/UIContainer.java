package hero.ui;

import hero.game.state.State;
import hero.misc.Size;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent {

    protected Color bgColor;
    protected List<UIComponent> childrens;

    protected Alignment alignment;

    protected Size windowSize;


    public UIContainer(Size windowSize) {
        super();
        childrens = new ArrayList<>();
        bgColor = new Color(0, true);
        calculateSize();
        this.windowSize = windowSize;
        alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
        calculatePosition();
    }

    public abstract void calculateContentPosition();
    public abstract Size calculateContentSize();

    public void calculateSize(){
        Size calculatedSize = calculateContentSize();
        size.update(padding.getHorizontal() + calculatedSize.getWidth(), padding.getVertical() + calculatedSize.getHeight());
    }

    public void calculatePosition(){
        int x = margin.getLeft();
        int y = margin.getTop();

        if(alignment.getHorizontal().equals(Alignment.Position.CENTER)){
            x = windowSize.getWidth() / 2 - size.getWidth() / 2;
        }
        else if(alignment.getHorizontal().equals(Alignment.Position.END)){
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }


        if(alignment.getVertical().equals(Alignment.Position.CENTER)){
            y = windowSize.getHeight() / 2 - size.getHeight() / 2;
        }
        else if(alignment.getVertical().equals(Alignment.Position.END)){
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }

        position.update(x, y);
        calculateContentPosition();
    }


    @Override
    public BufferedImage getSprite() {
        BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.BITMASK);
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
