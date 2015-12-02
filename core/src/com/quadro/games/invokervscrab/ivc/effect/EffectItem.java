package com.quadro.games.invokervscrab.ivc.effect;

import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.ivc.effect.worker.AbstractEffect;
import com.quadro.games.invokervscrab.ivc.skill.SkillInfo;

/**
 * Накладываемый эффект.
 * Бафы, дебафы, ауры.
 * Created by Quadrowin on 01.12.2015.
 */
public class EffectItem {

    /**
     * Время существования
     */
    private float mTimeExists = 0;

    /**
     * Максимальное время существования
     */
    private float mTimeMax = 0;

    private SkillInfo mInfo;

    private AbstractEffect mWorker;

    public EffectItem(SkillInfo info, AbstractEffect worker) {
        mInfo = info;
        mWorker = worker;
    }

    public SkillInfo getInfo() {
        return mInfo;
    }

    public float getTimeExists() {
        return mTimeExists;
    }

    public float getTimeMax() {
        return mTimeMax;
    }

    public AbstractEffect getWorker() {
        return mWorker;
    }

    public String getWorkerName() {
        return mWorker.getClass().getName();
    }

    public void incTimeExists(float delta) {
        mTimeExists += delta;
    }

    public void setTimeExists(float time) {
        mTimeExists = time;
    }

    public void setTimeMax(float time) {
        mTimeMax = time;
    }

    public void tick(GameObjectState target, float delta) {
        mWorker.tick(target, this, delta);
    }

}
