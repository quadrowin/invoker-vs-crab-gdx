package com.quadro.games.invokervscrab.ivc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class GameObjectState {

    public float mCurrentHp;

    public float mCurrentMp;

    public float mExperience;

    public int mLevel;

    public float mMaxHp;

    public float mMaxMp;

    public float mRegenHp;

    public float mRegenMp;

    public List<BuffSlot> mBuffs = new ArrayList<BuffSlot>();

}
