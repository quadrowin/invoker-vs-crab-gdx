package com.quadro.games.invokervscrab.ivc;

import com.quadro.games.invokervscrab.ivc.effect.EffectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class GameObjectState {

    /**
     * Базовый реген ХП
     */
    public float mBaseHpRegen;

    /**
     * Базовый реген МП
     */
    public float mBaseMpRegen;

    public float mCurrentHp;

    public float mCurrentMp;

    public float mExperience;

    public int mLevel;

    public float mMaxHp;

    public float mMaxMp;

    /**
     * Реген ХП на текущему тике
     */
    public float mRegenHp;

    /**
     * Реген МП на текущем тике
     */
    public float mRegenMp;

    public List<EffectItem> mEffects = new ArrayList<EffectItem>();

    private GameObjectCallback mOnEffectChange;

    public void addEffect(EffectItem effect) {
        mEffects.add(effect);
        effect.getWorker().start(effect);
        raiseEffectChange();
    }

    public void raiseEffectChange() {
        if (mOnEffectChange != null) {
            mOnEffectChange.run(this);
        }
    }

    public void setOnEffectChange(GameObjectCallback callback) {
        mOnEffectChange = callback;
    }

    public void takeDamage(float damage) {
        mCurrentHp = Math.max(0, mCurrentHp - damage);
    }

}
