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

}
