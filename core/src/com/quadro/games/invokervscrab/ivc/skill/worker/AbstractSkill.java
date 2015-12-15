package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.IvcSounds;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;

/**
 * Created by Quadrowin on 21.11.2015.
 */
abstract public class AbstractSkill {

    /**
     * @return Звук, проигрываемый при использовании умения
     */
    public String getSound() {
        return IvcSounds.SKILL_USE_FAIL;
    }

    abstract public void useSkill(IvcProcessor game, SkillItem skill);

}
