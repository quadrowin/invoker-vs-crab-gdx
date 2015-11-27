package com.quadro.games.invokervscrab.ivc;

import com.quadro.games.invokervscrab.ivc.skill.SkillItem;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class BuffSlot {

    private SkillItem mSourceSkill;

    public BuffSlot(SkillItem sourceSkill) {
        mSourceSkill = sourceSkill;
    }

    public SkillItem getSourceSkill() {
        return mSourceSkill;
    }

}
