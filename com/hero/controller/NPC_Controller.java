package hero.controller;

public class NPC_Controller implements Controller{


    @Override
    public boolean isReqUP() {
        return false;
    }

    @Override
    public boolean isReqLeft() {
        return false;
    }

    @Override
    public boolean isReqRight() {
        return false;
    }

    @Override
    public boolean isReqDown() {
        return false;
    }

}
