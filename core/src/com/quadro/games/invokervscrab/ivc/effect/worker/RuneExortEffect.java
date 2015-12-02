package com.quadro.games.invokervscrab.ivc.effect.worker;

import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;

/**
 * Created by Quadrowin on 01.12.2015.
 */
public class RuneExortEffect extends RuneAbstractEffect {

    final private static float MP_PER_SEC = 1;

    @Override
    public void tick(GameObjectState target, EffectItem effect, float delta) {
        target.mRegenMp += delta * MP_PER_SEC;
    }

}
