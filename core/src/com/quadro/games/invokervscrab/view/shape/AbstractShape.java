package com.quadro.games.invokervscrab.view.shape;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 19.12.2015.
 */
abstract public class AbstractShape {

    private float mRotation;

    private float mX, mY;

    private float mScaleX = 1, mScaleY = 1;

    protected float mSizeX = 1, mSizeY = 1;

    public void act (float delta) {

    }

    public void draw(ShapeRenderer sr) {

    }

    public float getRotation() {
        return mRotation;
    }

    public float getScaleX() {
        return mScaleX;
    }

    public float getScaleY() {
        return mScaleY;
    }

    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }

    public float getWidth() {
        return mSizeX * mScaleX;
    }

    public float getHeight() {
        return mSizeY * mScaleY;
    }

    public void setPosition(float x, float y) {
        mX = x;
        mY = y;
    }

    public void setRotation(float degrees) {
        mRotation = degrees;
    }

    public void setScale(float x, float y) {
        mScaleX = x;
        mScaleY = y;
    }

    public void setSize(float x, float y) {
        mSizeX = x;
        mSizeY = y;
    }

    public void setX(float x) {
        mX = x;
    }

    public void setY(float y) {
        mY = y;
    }

}
