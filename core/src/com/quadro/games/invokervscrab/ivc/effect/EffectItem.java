package com.quadro.games.invokervscrab.ivc.effect;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.effect.worker.AbstractEffect;
import com.quadro.games.invokervscrab.ivc.skill.SkillInfo;

/**
 * Накладываемый эффект.
 * Бафы, дебафы, ауры.
 * Created by Quadrowin on 01.12.2015.
 */
public class EffectItem {

    private SkillInfo mInfo;

    private AbstractEffect mWorker;

    public EffectItem(SkillInfo info, AbstractEffect worker) {
        mInfo = info;
        mWorker = worker;
    }

    public SkillInfo getInfo() {
        return mInfo;
    }

    public AbstractEffect getWorker() {
        return mWorker;
    }

    public String getWorkerName() {
        return mWorker.getClass().getName();
    }

    public void tick(IvcProcessor game, float delta) {
        mWorker.tick(game, delta);
    }

}
