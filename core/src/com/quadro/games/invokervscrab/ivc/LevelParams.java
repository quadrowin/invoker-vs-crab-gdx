package com.quadro.games.invokervscrab.ivc;

/**
 * Created by Quadrowin on 14.12.2015.
 */
public class LevelParams {

    /**
     * Экспа за юнита
     */
    private int mExpForUnit;

    /**
     * Экспа до апа
     */
    private int mExpToUp;

    private int mStartExp;

    public LevelParams(int expToUp, int expForUnit) {
        mExpForUnit = expForUnit;
        mExpToUp = expToUp;
    }

    public int getExpForUnit() {
        return mExpForUnit;
    }

    public int getExpToUp() {
        return mExpToUp;
    }

    public int getStartExp() {
        return mStartExp;
    }

    public void setStartExp(int exp) {
        mStartExp = exp;
    }

}
