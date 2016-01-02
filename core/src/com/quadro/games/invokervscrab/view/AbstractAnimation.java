package com.quadro.games.invokervscrab.view;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Quadrowin on 02.01.2016.
 */
abstract public class AbstractAnimation {

    protected static final float STOP_TIME = Float.MAX_VALUE;

    private AnimationCallback mOnFinish;

    /**
     * Анимированный объект
     */
    protected Actor mActor;

    protected float mTime = STOP_TIME;

    protected float mStartX, mStartY, mStartScaleX, mStartScaleY, mStartRotation;

    abstract public void act(float delta);

    public void actorChanged() {
        mTime = 0;
        if (mActor != null) {
            mStartX = mActor.getX();
            mStartY = mActor.getY();
            mStartScaleX = mActor.getScaleX();
            mStartScaleY = mActor.getScaleY();
            mStartRotation = mActor.getRotation();
        }
    }

    protected boolean actTime(float delta) {
        if (mTime > getDuration()) {
            if (mTime < STOP_TIME) {
                mTime = STOP_TIME;
                doOnFinish();
            }
            return false;
        }
        mTime += delta;
        return true;
    }

    protected void doOnFinish() {
        if (mOnFinish != null) {
            mOnFinish.run(this);
        }
    }

    /**
     * @return Продолжительность анимации
     */
    public float getDuration() {
        return 1;
    }

    public void setActor(Actor actor) {
        mActor = actor;
        actorChanged();
    }

    public void setOnFinish(AnimationCallback callback) {
        mOnFinish = callback;
    }

}
