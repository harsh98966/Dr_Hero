package hero.ui;


import hero.game.state.State;

import hero.core.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.InputStream;


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

    public UIText(String text, String fontFamily) {
        this(text);
        this.fontFamily = fontFamily;
    }


    public UIText(String text) {
        this.text = text;

        fontSize = 18;
        fontStyle = Font.BOLD;
        fontFamily = "joystix_monospace";
        fontColor = Color.WHITE;

        dropShadow = true;
        dropShadowOffset = 3;
        shadowColor = new Color(0, 0, 0);

        calculatedFontHeight = calculatedFontWidth = 1;
        createFont();
    }


    @Override
    public BufferedImage getSprite() {
        BufferedImage image = new BufferedImage(calculatedFontWidth + padding.getLeft(), size.getHeight(), BufferedImage.BITMASK);
        Graphics g = image.getGraphics();
        g.setFont(font);

        if (dropShadow) {
            g.setColor(shadowColor);
            g.drawString(text, padding.getLeft() + dropShadowOffset, padding.getTop() + fontSize + dropShadowOffset);
        }

        g.setColor(fontColor);
        g.drawString(text, padding.getLeft(), padding.getTop() + fontSize);
        g.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        calculateSize();
    }

    private void calculateSize(){
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        calculatedFontWidth = fontMetrics.stringWidth(text);
        calculatedFontHeight = fontMetrics.getHeight();
        if(dropShadow) calculatedFontWidth += dropShadowOffset;
        size = new Size(
                calculatedFontWidth + padding.getHorizontal(),
                calculatedFontHeight + padding.getVertical()
        );
    }

    private void createFont() {
        String fName = "/Fonts/" + fontFamily + ".ttf";
        InputStream is = getClass().getResourceAsStream(fName);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(fontStyle, fontSize);
        } catch (Exception e) {
            font = new Font(fontFamily, fontStyle, fontSize);
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        createFont();
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
        createFont();
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        createFont();
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
