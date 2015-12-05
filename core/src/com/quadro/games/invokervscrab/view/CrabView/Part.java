package com.quadro.games.invokervscrab.view.CrabView;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
abstract public class Part {

    private float[] mPosition;

    protected float[] mRandom;

    private float mScale = 1;

    protected float[] mSize;

    public Part(float x, float y, float scale) {
        mPosition = new float[] {x, y};
        mScale = scale;
        mRandom = new float[] {0, 0};
    }

    abstract public void draw(ShapeRenderer sr);

    public float getLeft() {
        return mPosition[0] - mSize[0] * 0.5f * mScale;
    }

    public float getBottom() {
        return mPosition[1] - mSize[1] * 0.5f * mScale;
    }

    public float getScale() {
        return mScale;
    }

    public float getX() {
        return mPosition[0];
    }

    public float getY() {
        return mPosition[1];
    }

    public float getWidth() {
        return mSize[0] * mScale;
    }

    public float getHeight() {
        return mSize[1] * mScale;
    }

    public void randomize() {
        mRandom[0] = (float)Math.random();
        mRandom[1] = (float)Math.random();
    }

}
