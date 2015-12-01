package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.effect.worker.AbstractEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneWexEffect;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class RuneSecondSkill extends AbstractRuneSkill {

    protected AbstractEffect newEffect() {
        return new RuneWexEffect();
    }

}
