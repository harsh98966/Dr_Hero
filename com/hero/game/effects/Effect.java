package hero.game.effects;

import hero.game.state.State;
import hero.gameobject.MovingEntity;
import hero.misc.Constants;

public abstract class Effect {

    public enum TYPE{
        FOREVER, // effect will go on forever
        SHORT_LIFE_SPAN // effect has some life span
    }

    private TYPE effectType;

    // lifespan in seconds
    protected int lifeSpan;

    public Effect(TYPE effectType, int lifeSpan){
        this.effectType = effectType;
        this.lifeSpan = lifeSpan * (int)Constants.UPDATES_PER_SECOND;
    }

    public void update(State state, MovingEntity entity){
        if(effectType.equals(TYPE.SHORT_LIFE_SPAN)) lifeSpan--;
    }

    public boolean shouldDelete(){
        if(effectType.equals(TYPE.SHORT_LIFE_SPAN)) return lifeSpan <= 0;
        else return false;
    }

}
