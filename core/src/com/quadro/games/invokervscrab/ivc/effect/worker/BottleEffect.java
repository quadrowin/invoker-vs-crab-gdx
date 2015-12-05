package com.quadro.games.invokervscrab.ivc.effect.worker;

import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;

/**
 * Created by Quadrowin on 01.12.2015.
 */
public class BottleEffect extends AbstractEffect {

    final private static float EFFECT_TIME = 3;

    final private static float HP_PER_SEC = 110 / 3;

    final private static float MP_PER_SEC = 70 / 3;

    public void start(EffectItem effect) {
        effect.setTimeMax(EFFECT_TIME);
    }

    @Override
    public void tick(GameObjectState target, EffectItem effect, float delta) {
        target.mRegenHp += HP_PER_SEC * delta;
        target.mRegenMp += MP_PER_SEC * delta;
    }

}
