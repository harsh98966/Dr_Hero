package sam.gameobject;

import sam.controller.Controller;
import sam.core.Motion;
import sam.misc.Position;
import sam.misc.Size;

public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected Motion motion;

    public MovingEntity(Position position, Size size, Controller controller){
        super(position, size);
        this.controller = controller;
        motion = new Motion(1);
    }

    @Override
    public void update() {
        motion.update(controller);
        position.applyVec(motion.getVector());
    }

}
