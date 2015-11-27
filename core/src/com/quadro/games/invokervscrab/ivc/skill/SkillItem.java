package com.quadro.games.invokervscrab.ivc.skill;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.skill.worker.AbstractSkill;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class SkillItem {

    private SkillInfo mInfo;

    private AbstractSkill mWorker;

    public SkillItem(SkillInfo info, AbstractSkill worker) {
        mInfo = info;
        mWorker = worker;
    }

    public SkillInfo getInfo() {
        return mInfo;
    }

    public AbstractSkill getWorker() {
        return mWorker;
    }

    public String getWorkerName() {
        return mWorker.getClass().getName();
    }

    public void use(IvcProcessor game) {
        mWorker.useSkill(game, this);
    }

}
