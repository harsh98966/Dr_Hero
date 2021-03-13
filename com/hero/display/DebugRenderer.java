package hero.display;

import hero.core.CollisionBox;
import hero.game.state.State;
import hero.gameobject.GameObject;

import java.awt.*;

public class DebugRenderer {
    public void render(State state, Graphics g) {
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(camera::isInView)
                .map(GameObject::getCollisionBox)
                .forEach(c -> drawCollisionBox(c, g, camera));
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics g, Camera camera) {
        g.setColor(Color.RED);
        g.drawRect(collisionBox.getBounds().x - camera.getPosition().intX() - collisionBox.getBounds().width / 2,
                collisionBox.getBounds().y - camera.getPosition().intY() - collisionBox.getBounds().height / 2,
                collisionBox.getBounds().width,
                collisionBox.getBounds().height);
    }
}
