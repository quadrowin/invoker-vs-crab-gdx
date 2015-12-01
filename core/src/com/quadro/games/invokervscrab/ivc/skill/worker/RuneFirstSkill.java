package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.effect.worker.AbstractEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneQuasEffect;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class RuneFirstSkill extends AbstractRuneSkill {

    public static String SPRITE_NAME = "skill.quas";

    protected AbstractEffect newEffect() {
        return new RuneQuasEffect();
    }

}
