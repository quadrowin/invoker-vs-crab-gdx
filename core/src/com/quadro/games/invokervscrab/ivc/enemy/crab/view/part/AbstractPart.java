package com.quadro.games.invokervscrab.ivc.enemy.crab.view.part;

import com.quadro.games.invokervscrab.view.shape.AbstractShape;

/**
 * Created by Quadrowin on 24.11.2015.
 */
abstract public class AbstractPart extends AbstractShape {

    protected float[] mRandom;

    public AbstractPart () {
        mRandom = new float[] {0, 0};
    }

    public float getLeft() {
        return getX() - mSizeX * 0.5f * getScaleX();
    }

    public float getBottom() {
        return getY() - mSizeY * 0.5f * getScaleY();
    }


    public void randomize() {
        mRandom[0] = (float)Math.random();
        mRandom[1] = (float)Math.random();
    }

}
