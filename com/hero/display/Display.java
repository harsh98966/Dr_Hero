package hero.display;

import hero.misc.Constants;
import hero.game.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;
    private Renderer renderer;

    public Display(int width, int height, KeyListener keyListener){

        setTitle("Sam Gaming");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        pack();
        canvas.createBufferStrategy(3);
        addKeyListener(keyListener);
        setLocationRelativeTo(null);
        setVisible(true);

        renderer = new Renderer();

    }

    public void render(State state){
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        // background
        g.setColor(new Color(Constants.BG_COLOR));
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        renderer.renderMap(state, g);
        renderer.render(state,g);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        bs.show();

    }

}
