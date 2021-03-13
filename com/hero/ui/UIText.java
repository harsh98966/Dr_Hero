package hero.ui;

import hero.game.state.State;
import hero.misc.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIText extends UIComponent {

    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color fontColor;

    private int calculatedFontWidth;
    private int calculatedFontHeight;

    private boolean dropShadow;
    private int dropShadowOffset;
    private Color shadowColor;

    private Font font;

    public UIText(String text, int fontSize){
        this(text);
        this.fontSize = fontSize;
    }



    public UIText(String text){
        this.text = text;
        fontSize = 20;
        fontStyle = Font.PLAIN;
        fontFamily = Font.MONOSPACED;
        fontColor = Color.WHITE;
        dropShadow = false;
        dropShadowOffset = 1;
        shadowColor = new Color(148, 148, 148);
        calculatedFontHeight = calculatedFontWidth = 0;
        font = new Font(fontFamily, fontStyle, fontSize);
    }

    private void calculateSize(){
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        calculatedFontWidth = fontMetrics.stringWidth(text);
        calculatedFontHeight = fontMetrics.getHeight();
        size = new Size(
                calculatedFontWidth,
                 calculatedFontHeight
        );
    }




    @Override
    public BufferedImage getSprite() {
        BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.BITMASK);
        Graphics g = image.createGraphics();

        if(dropShadow){
            g.setColor(shadowColor);
            g.drawString(text, padding.getLeft() + dropShadowOffset,  padding.getTop() + fontSize + dropShadowOffset);
        }

        g.setColor(fontColor);
        g.drawString(text, padding.getLeft(), padding.getTop());
        g.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        createFont();
        calculateSize();
    }

    private void createFont() {
        font = new Font(fontFamily, fontStyle, fontSize);
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setDropShadow(boolean dropShadow) {
        this.dropShadow = dropShadow;
    }

    public void setDropShadowOffset(int dropShadowOffset) {
        this.dropShadowOffset = dropShadowOffset;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }
}
