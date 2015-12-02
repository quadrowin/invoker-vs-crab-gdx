package com.quadro.games.invokervscrab.ivc.effect.worker;

import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;

/**
 * Created by Quadrowin on 01.12.2015.
 */
abstract public class AbstractEffect {


    /**
     * По окончании эффекта
     * @param effect
     */
    public void finish(EffectItem effect) {

    }

    /**
     * Сразу при наложении бафа
     * @param effect
     */
    public void start(EffectItem effect) {

    }

    abstract public void tick(GameObjectState target, EffectItem effect, float delta);

}
