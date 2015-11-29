package com.quadro.games.invokervscrab.ivc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class GameObjectState {

    public int mCurrentHp;

    public int mCurrentMp;

    public int mExperience;

    public int mLevel;

    public int mMaxHp;

    public int mMaxMp;

    public float mRegenHp;

    public float mRegenMp;

    public List<BuffSlot> mBuffs = new ArrayList<BuffSlot>();

}
