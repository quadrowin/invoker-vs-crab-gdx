package com.quadro.games.invokervscrab.ivc.skill.thing;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.ivc.skill.worker.AbstractSkill;

/**
 * Created by Quadrowin on 01.12.2015.
 */
public class BottleSkill extends AbstractSkill {

    private int mCharge = 3;

    /**
     * Бутылка в руках - можно пить.
     * Может быть в осле.
     */
    private boolean mInHand = true;

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        if (!mInHand) {
            return;
        }

    }

}
