package hero.display;

import hero.core.Constants;
import hero.game.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;
    private Renderer renderer;
    private DebugRenderer debugRenderer;

    public Display(int width, int height, KeyListener keyListener){

        setTitle("Sam Gaming");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        pack();

        addKeyListener(keyListener);
        setLocationRelativeTo(null);
        setVisible(true);

        renderer = new Renderer();
        debugRenderer = new DebugRenderer();

    }

    public void render(State state, boolean debugMode){
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        // background
        g.setColor(new Color(Constants.BG_COLOR));
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        renderer.render(state,g);
        if(debugMode){
            debugRenderer.render(state, g);
        }

        g.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();

    }

}
