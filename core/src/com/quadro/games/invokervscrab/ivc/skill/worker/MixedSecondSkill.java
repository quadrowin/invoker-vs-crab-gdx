package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class MixedSecondSkill extends AbstractSkill {

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        game.tryUseMixed(1);
    }

}
