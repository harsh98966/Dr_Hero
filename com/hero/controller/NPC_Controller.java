package hero.controller;

import hero.misc.Position;

public class NPC_Controller implements Controller {

    private boolean up, right, down, left;

    @Override
    public boolean isReqUP() {
        return up;
    }

    @Override
    public boolean isReqRight() {
        return right;
    }

    @Override
    public boolean isReqDown() {
        return down;
    }

    @Override
    public boolean isReqLeft() {
        return left;
    }

    public void moveToTarget(Position current, Position target) {
        double x = target.getX() - current.getX();
        double y = target.getY() - current.getY();
        up = y < 0 && Math.abs(y) > Position.PROXIMITY_RANGE;
        right = x > 0 && Math.abs(x) > Position.PROXIMITY_RANGE;
        down = y > 0 && Math.abs(y) > Position.PROXIMITY_RANGE;
        left = x < 0 && Math.abs(x) > Position.PROXIMITY_RANGE;
    }

    public void stop(){
        up = right = down = left = false;
    }

}
