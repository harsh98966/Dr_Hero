package hero.core;

import hero.controller.Controller;

public class Motion {
    private Vector2d vector;
    private double velocity;

    public Motion(double velocity){
        this.velocity = velocity;
        vector = new Vector2d(0, 0);
    }

    public void update(Controller controller){
        int deltaX = 0;
        int deltaY = 0;

        if(controller.isReqUP()){
            deltaY--;
        }

        if(controller.isReqDown()){
            deltaY++;
        }

        if(controller.isReqLeft()){
            deltaX--;
        }

        if(controller.isReqRight()){
            deltaX++;
        }

        vector.update(deltaX, deltaY);
        vector.normalize();
        vector.multiply(velocity);
    }

    public Vector2d getVector() {
        return vector;
    }

    public boolean isMoving() {
        return vector.length() > 0;
    }
}
