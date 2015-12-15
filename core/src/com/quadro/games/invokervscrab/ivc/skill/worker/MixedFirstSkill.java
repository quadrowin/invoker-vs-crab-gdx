package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.SL;
import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.IvcSounds;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class MixedFirstSkill extends AbstractSkill {

    @Override
    public String getSound() {
        SkillItem skill = SL.getGame().getMixedSkills()[0];
        return skill != null
                ? null
                : IvcSounds.SKILL_USE_FAIL;
    }

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        game.tryUseMixed(0);
    }

}
