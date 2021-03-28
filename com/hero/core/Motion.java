package hero.core;

import hero.controller.EntityController;

public class Motion {
    private Vector2d vector;
    private double velocity;

    public Motion(double velocity){
        this.velocity = velocity;
        vector = new Vector2d(0, 0);
    }

    public void update(EntityController entityController){
        int deltaX = 0;
        int deltaY = 0;

        if(entityController.isReqUP()){
            deltaY--;
        }

        if(entityController.isReqDown()){
            deltaY++;
        }

        if(entityController.isReqLeft()){
            deltaX--;
        }

        if(entityController.isReqRight()){
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

    public void multiplySpeed(double speedMul) {
        vector.multiply(speedMul);
    }

    public void stop(boolean stopX, boolean stopY) {
        vector.update(
                stopX ? 0 : vector.getX(),
                stopY ? 0 : vector.getY()
        );
    }
}
